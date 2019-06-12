package spark

import org.apache.log4j.{ Level, Logger }
import org.apache.spark.{ SparkConf, SparkContext }
import org.apache.spark.rdd.RDD
import java.sql.{DriverManager, PreparedStatement, Connection}

object ItemCF {
  def main(args: Array[String]) {

    //0 构建Spark对象
    val conf = new SparkConf().setAppName("ItemCF").setMaster("local")
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)
//    var conn:Connection = null
//    var pts:PreparedStatement = null
    //1_5000 读取样本数据
    val data_path = "./input/train/1_20000"
    val output = "./output/result_k_5"
//    val output1 = "./output/similarity/"
//
//    //去除首行
//    val header = data.first()
//    var i =1_5000
//    for (i <- 1_5000 to 21){
    val data = sc.textFile(data_path)
      println("正在读取："+data_path)
    val userdata = data.map(_.split(";")).map(f => (ItemPref(f(0), f(1), f(2).toDouble))).cache()
    //2 建立模型
    val mysimil = new ItemSimilarity()
    val simil_rdd1 = mysimil.Similarity(userdata, "cooccurrence")
    val recommd = new RecommendedItem
    val recommd_rdd1 = recommd.Recommend(simil_rdd1, userdata,1)
//  打印结果
    println(s"物品相似度矩阵数量: ${simil_rdd1.count()}")
//    simil_rdd1.collect().foreach { ItemSimi =>
//      println(ItemSimi.itemid2 + ", " + ItemSimi.itemid1 + ", " + ItemSimi.similar)
//    }
//      println("正在保存相似度")
//      simil_rdd1.map{ItemSimi=>(ItemSimi.itemid2,ItemSimi.itemid1,ItemSimi.similar)
//          .toString()
//          .replace(")","")
//          .replace("(","")
//      }.saveAsTextFile(output1+2)
    println(s"用戶推荐列表: ${recommd_rdd1.count()}")
//    recommd_rdd1.collect().foreach { UserRecomm =>
//      println(UserRecomm.userid + ", " + UserRecomm.itemid + ", " + UserRecomm.pref)
//    }
      println("保存文件数据")
      recommd_rdd1.map{data=>(data.userid,data.itemid,data.pref)
        .toString()
        .replace(")","")
        .replace("(","")
      }.saveAsTextFile(output)
    println("保存文件成功")
//    println("写入数据库")
//    new KeepData().keepData(recommd_rdd1)
//    println("写入数据库成功")
    }
//  }
}


