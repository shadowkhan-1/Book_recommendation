package spark

import scala.math._
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext._
import org.apache.spark.mllib.linalg._
import org.apache.spark.mllib.linalg.distributed._

/**
  * 用户评分.
  * @param userid 用户
  * @param itemid 评分物品
  * @param pref 评分
  */
case class ItemPref(
                     val userid: String,
                     val itemid: String,
                     val pref: Double) extends Serializable
/**
  * 用户推荐.
  * @param userid 用户
  * @param itemid 推荐物品
  * @param pref 评分
  */
case class UserRecomm(
                       val userid: String,
                       val itemid: String,
                       val pref: Double) extends Serializable
/**
  * 相似度.
  * @param itemid1 物品
  * @param itemid2 物品
  * @param similar 相似度
  */

case class ItemSimi(
                     val itemid1: String,
                     val itemid2: String,
                     val similar: Double) extends Serializable

/**
  * 相似度计算.
  * 支持：同现相似度、欧氏距离相似度、余弦相似度
  *
  */
class ItemSimilarity extends Serializable {

  /**
    * 相似度计算.
    * @param user_rdd 用户评分
    * @param stype 计算相似度公式
    * @param RDD[ItemSimi] 返回物品相似度
    *
    */
  def Similarity(user_rdd: RDD[ItemPref], stype: String): (RDD[ItemSimi]) = {
    val simil_rdd = stype match {
      case "cooccurrence" =>
        ItemSimilarity.CooccurrenceSimilarity(user_rdd)
      case "cosine" =>
        ItemSimilarity.CosineSimilarity(user_rdd)
      case "euclidean" =>
        ItemSimilarity.EuclideanDistanceSimilarity(user_rdd)
      case _ =>
        ItemSimilarity.CooccurrenceSimilarity(user_rdd)
    }
    simil_rdd
  }

}

object ItemSimilarity {

  /**
    * 同现相似度矩阵计算.
    * w(i,j) = N(i)∩N(j)/sqrt(N(i)*N(j))
    * @param user_rdd 用户评分
    * @param RDD[ItemSimi] 返回物品相似度
    *
    */
  def CooccurrenceSimilarity(user_rdd: RDD[ItemPref]): (RDD[ItemSimi]) = {
    // 0 数据做准备
    val user_rdd1 = user_rdd.map(f => (f.userid, f.itemid, f.pref))
    val user_rdd2 = user_rdd1.map(f => (f._1, f._2))
    // 1(用户：物品) 笛卡尔积 (用户：物品) => 物品:物品组合
    val user_rdd3 = user_rdd2.join(user_rdd2)
    val user_rdd4 = user_rdd3.map(f => (f._2, 1))
    // 2 物品:物品:频次
    val user_rdd5 = user_rdd4.reduceByKey((x, y) => x + y)
    // 3 对角矩阵=一个物品的频率
    val user_rdd6 = user_rdd5.filter(f => f._1._1 == f._1._2)
    // 4 非对角矩阵=不同的物品的频率
    val user_rdd7 = user_rdd5.filter(f => f._1._1 != f._1._2)
    // 5 计算同现相似度（物品1，物品2，同现频次）
    val user_rdd8 = user_rdd7.map(f => (f._1._1, (f._1._1, f._1._2, f._2))).
      join(user_rdd6.map(f => (f._1._1, f._2)))
    //rdd9 = (物品２,(物品1,物品2,nij,ni))
    val user_rdd9 = user_rdd8.map(f => (f._2._1._2, (f._2._1._1,
      f._2._1._2, f._2._1._3, f._2._2)))
    //rdd10 = (物品２,((物品1,物品2,nij,ni),nj))
    val user_rdd10 = user_rdd9.join(user_rdd6.map(f => (f._1._1, f._2)))
    //rdd11 = (物品１,物品２,nij,ni,nj)
    val user_rdd11 = user_rdd10.map(f => (f._2._1._1, f._2._1._2, f._2._1._3, f._2._1._4, f._2._2))
    //rdd12 = (物品1,物品2,n1/sqrt(n2*n3))
    val user_rdd12 = user_rdd11.map(f => (f._1, f._2, (f._3 / sqrt(f._4 * f._5))))
    // 6 结果返回
    user_rdd12.map(f => ItemSimi(f._1, f._2, f._3))
  }

  /**
    * 余弦相似度矩阵计算.
    * T(x,y) = ∑x(i)y(i) / sqrt(∑(x(i)*x(i)) * ∑(y(i)*y(i)))
    * @param user_rdd 用户评分
    * @param RDD[ItemSimi] 返回物品相似度
    *
    */
  def CosineSimilarity(user_rdd: RDD[ItemPref]): (RDD[ItemSimi]) = {
    // 0 数据做准备
    val user_rdd1 = user_rdd.map(f => (f.userid, f.itemid, f.pref))
    val user_rdd2 = user_rdd1.map(f => (f._1, (f._2, f._3)))
    // 1 (用户,物品,评分) 笛卡尔积 (用户,物品,评分) => （物品1,物品2,评分1,评分2）组合
    val user_rdd3 = user_rdd2.join(user_rdd2)
    val user_rdd4 = user_rdd3.map(f => ((f._2._1._1, f._2._2._1), (f._2._1._2, f._2._2._2)))
    // 2 （物品1,物品2,评分1,评分2）组合 => （物品1,物品2,评分1*评分2） 组合 并累加
    val user_rdd5 = user_rdd4.map(f => (f._1, f._2._1 * f._2._2)).reduceByKey(_ + _)
    // 3 对角矩阵
    val user_rdd6 = user_rdd5.filter(f => f._1._1 == f._1._2)
    // 4 非对角矩阵
    val user_rdd7 = user_rdd5.filter(f => f._1._1 != f._1._2)
    // 5 计算相似度
    val user_rdd8 = user_rdd7.map(f => (f._1._1, (f._1._1, f._1._2, f._2))).
      join(user_rdd6.map(f => (f._1._1, f._2)))
    val user_rdd9 = user_rdd8.map(f => (f._2._1._2, (f._2._1._1,
      f._2._1._2, f._2._1._3, f._2._2)))
    val user_rdd10 = user_rdd9.join(user_rdd6.map(f => (f._1._1, f._2)))
    val user_rdd11 = user_rdd10.map(f => (f._2._1._1, f._2._1._2, f._2._1._3, f._2._1._4, f._2._2))
    val user_rdd12 = user_rdd11.map(f => (f._1, f._2, (f._3 / sqrt(f._4 * f._5))))
    // 6 结果返回
    user_rdd12.map(f => ItemSimi(f._1, f._2, f._3))
  }

  /**
    * 欧氏距离相似度矩阵计算.
    * d(x, y) = sqrt(∑((x(i)-y(i)) * (x(i)-y(i))))
    * sim(x, y) = n / (1_5000 + d(x, y))
    * @param user_rdd 用户评分
    * @param RDD[ItemSimi] 返回物品相似度
    *
    */
  def EuclideanDistanceSimilarity(user_rdd: RDD[ItemPref]): (RDD[ItemSimi]) = {
    // 0 数据做准备
    val user_rdd1 = user_rdd.map(f => (f.userid, f.itemid, f.pref))
    val user_rdd2 = user_rdd1.map(f => (f._1, (f._2, f._3)))
    // 1_5000 (用户,物品,评分) 笛卡尔积 (用户,物品,评分) => （物品1,物品2,评分1,评分2）组合
    val user_rdd3 = user_rdd2 join user_rdd2
    val user_rdd4 = user_rdd3.map(f => ((f._2._1._1, f._2._2._1), (f._2._1._2, f._2._2._2)))
    // 2 （物品1,物品2,评分1,评分2）组合 => （物品1,物品2,评分1-评分2） 组合 并累加
    val user_rdd5 = user_rdd4.map(f => (f._1, (f._2._1 - f._2._2) * (f._2._1 - f._2._2))).reduceByKey(_ + _)
    // 3 （物品1,物品2,评分1,评分2）组合 => （物品1,物品2,1_5000） 组合 并累加    计算重叠数
    val user_rdd6 = user_rdd4.map(f => (f._1, 1)).reduceByKey(_ + _)
    // 4 非对角矩阵
    val user_rdd7 = user_rdd5.filter(f => f._1._1 != f._1._2)
    // 5 计算相似度
    val user_rdd8 = user_rdd7.join(user_rdd6)
    val user_rdd9 = user_rdd8.map(f => (f._1._1, f._1._2, f._2._2 / (1 + sqrt(f._2._1))))
    // 6 结果返回
    user_rdd9.map(f => ItemSimi(f._1, f._2, f._3))
  }
//  def MLlibSimilarity(user_rdd:RDD[ItemPref]):(RDD[ItemSimi])={
//        val user_rdd1 = user_rdd.map(f=>(f.userid,f.itemid,f.pref)).map(line=>Vectors.dense(line)).map()
//        val matrix = new IndexedRowMatrix(user_rdd1)
//  }

}


