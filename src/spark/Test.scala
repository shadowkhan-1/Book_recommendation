package spark

import org.apache.log4j.{ Level, Logger }
import org.apache.spark.{ SparkConf, SparkContext }
import org.apache.spark.rdd.RDD

object ItemCF {
  def main(args: Array[String]) {

    //0 构建Spark对象
    val conf = new SparkConf().setAppName("ItemCF").setMaster("local")
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)

    //1 读取样本数据
    val data_path = "./input/test.csv"
    val data = sc.textFile(data_path)
//
//    //去除首行
    val header = data.first()
    val userdata = data.filter(row=>row!=header).map(_.split(";")).map(f => (ItemPref(f(0), f(1), f(2).replaceAll("\"","").toDouble))).cache()

    //2 建立模型
    val mysimil = new ItemSimilarity()
    val simil_rdd1 = mysimil.Similarity(userdata, "cooccurrence")
    val recommd = new RecommendedItem
    val recommd_rdd1 = recommd.Recommend(simil_rdd1, userdata, 10)

    //3 打印结果
//    println(s"物品相似度矩阵数量: ${simil_rdd1.count()}")
//    simil_rdd1.collect().foreach { ItemSimi =>
//      println(ItemSimi.itemid2 + ", " + ItemSimi.itemid1 + ", " + ItemSimi.similar)
//    }
    val recommd_model:RDD[(String,String,Double)] = sc.objectFile[(String,String,Double)]("./input/modelPath")
    println(s"测试：${recommd_model.count()}")
    val rdd = recommd_model.map(x=>(x._1,x._1,x._3))
    rdd.foreach(x=>println(x._1,x._2,x._3))
//    println(s"用戶推荐列表: ${recommd_rdd1.count()}")
//    recommd_rdd1.collect().foreach { UserRecomm =>
//      println(UserRecomm.userid + ", " + UserRecomm.itemid + ", " + UserRecomm.pref)
//    }

  }
}

