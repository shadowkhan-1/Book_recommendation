package spark

import Factory.ServiceFactory
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import table.BX_Books
import org.apache.spark.sql.hive.HiveContext
object recommend {
  def main(args: Array[String]): Unit = {
      val conf = new SparkConf().setAppName("test").setMaster("local")
      val sc = new SparkContext(conf)
      Logger.getRootLogger.setLevel(Level.WARN)
//      val SQLCtx = new SQLContext(sc)
////      val csv = hiveCtx.csvFile("./input/BX-Book-Ratings.csv")
//      val df = SQLCtx.read.format("com.databricks.spark.csv")
//        .option("header","true")
//        .option("inferSchema", "false")
//        .option("delimiter",";")
//        .load("./input/BX-Book-Ratings.csv")
//      df.show(5)
      val file  = sc.textFile("./output/recommend/recommend.csv")
//      val header = file.first()
      val userRecommend = file.map{x=>val line = x.split(",");((UserRecomm(line(0),line(1).replaceAll("\"",""),line(2).toDouble)))}
      userRecommend.take(10).foreach(x=>println(x.userid,x.itemid,x.pref))
      new KeepData().keepData(userRecommend)
  }
}
