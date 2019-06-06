package spark

import Factory.ServiceFactory
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import table.BX_Books
import org.apache.spark.sql.hive.HiveContext
object test1 {
  def main(args: Array[String]): Unit = {
      val conf = new SparkConf().setAppName("test").setMaster("local")
      val sc = new SparkContext(conf)
//      val SQLCtx = new SQLContext(sc)
////      val csv = hiveCtx.csvFile("./input/BX-Book-Ratings.csv")
//      val df = SQLCtx.read.format("com.databricks.spark.csv")
//        .option("header","true")
//        .option("inferSchema", "false")
//        .option("delimiter",";")
//        .load("./input/BX-Book-Ratings.csv")
//      df.show(5)
      val file  = sc.textFile("./input/BX-Book-Ratings.csv")
      val header = file.first()
      val rating = file.filter(row=>row != header).map{x=>val line = x.split(";");(line(0),line(1),line(2))}
      rating.take(10).foreach(x=>println(x._1,x._2.replaceAll("\"","").hashCode(),x._3))
  }
}