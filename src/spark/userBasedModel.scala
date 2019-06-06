package spark

import breeze.numerics.sqrt
import org.apache.spark.{SparkConf, SparkContext}

object userBasedModel {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("userBaseModel").setMaster("local")
    val sc = new SparkContext(conf)
    val path = "./input/test.csv"
    val UserData = sc.textFile(path)
    val header = UserData.first()
    val user_rdd = UserData.filter(row=>row !=header).map{x=>val line = x.split(";");(line(0),line(1))}
    val user_rdd2=user_rdd.map(f => (f._1,f._2)).sortByKey().cache()
    val user_rdd3=user_rdd2.join(user_rdd2)

    val user_rdd4=user_rdd3.map(data=> (data._2,1))

    //  2 物品:物品:频次

    val user_rdd5=user_rdd4.reduceByKey((x,y) => x + y)

    //  3 对角矩阵

    val user_rdd6=user_rdd5.filter(f=> f._1._1 == f._1._2)

    //  4 非对角矩阵

    val user_rdd7=user_rdd5.filter(f=> f._1._1 != f._1._2)

    //  5 计算同现相似度（物品1，物品2，同现频次）

    val user_rdd8=user_rdd7.map(f=> (f._1._1, (f._1._1, f._1._2, f._2))).

      join(user_rdd6.map(f=> (f._1._1, f._2)))

    val user_rdd9=user_rdd8.map(f=> (f._2._1._2, (f._2._1._1,

      f._2._1._2, f._2._1._3, f._2._2)))

    val user_rdd10=user_rdd9.join(user_rdd6.map(f => (f._1._1, f._2)))

    val user_rdd11 = user_rdd10.map(f => (f._2._1._1,f._2._1._2,f._2._1._3,f._2._1._4,f._2._2))

    val user_rdd12=user_rdd11.map(f=> (f._1, f._2, (f._3 / sqrt(f._4 * f._5)) ))
    user_rdd12.foreach(x=>println(x._2,x._1,x._3))
//    val user_rdd = rawRatings.map(x=>(x._1,x._2)).sortByKey().cache()
//    val user_rdd1 = user_rdd.join(user_rdd).map(data=>(data._2,1)).reduceByKey(_+_)
//    val user_rdd2 = user_rdd1.filter(x=>x._1._1==x._1._2)
//    val user_rdd3 = user_rdd1.filter(x=>x._1._1!=x._1._2)
//    val user_rdd4 = user_rdd3.map(x=>(x._1._1,(x._1._1,x._1._2,x._2))).join(user_rdd2.map(x=>(x._1._1,x._2)))
//    val user_rdd5 = user_rdd4.map(x=>(x._2._1._2,(x._2._1._1,x._2._1._2,x._2._1._3,x._2._2)))
//    val ratingsRDD = rawRatings.map{x=>(x._1,x._2)}           //(user,item)
//    val userRDD = rawRatings.map(x=>(x._2,x._1))              //(item.user)
//    val RDD = ratingsRDD.groupByKey().map{x=>(x._1,x._2.toArray)}
//    val UserRDD = userRDD.groupByKey().map(x=>(x._1,x._2.toArray))
//    val user_item = RDD.cartesian(RDD).filter(x=>x._1._2!=x._2._1)
//    RDD.take(3)
  }
}
