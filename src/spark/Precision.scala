package spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object Precision {
  def main(args: Array[String]): Unit = {
  val conf = new SparkConf().setAppName("test").setMaster("local")
  val sc = new SparkContext(conf)
  Logger.getRootLogger.setLevel(Level.WARN)
  val test_data = sc.textFile("./input/test/1_2")
    .map{x=>val line=x.split(";");(line(0),line(1))}
  val data = sc.textFile("./output/test/part-00000")
    .map{x=>val line=x.split(",");(line(0),line(1))}
  val result = test_data.join(data)
    .filter(x=>x._2._1==x._2._2).map(x=>(x._1,1)).reduceByKey(_+_).sortBy(x=>x._1)
//  result.foreach(x=>println("result:"+x._1,x._2))
  val data_count = data.map(x=>(x._1,1)).reduceByKey(_+_).sortBy(x=>x._1)
//      data_count.foreach(x=>println("data_count:"+x._1,x._2))
    val precision = result
      .join(data_count)
      .map{x=>(("precision",(x._2._1/x._2._2.toDouble),1))}  //(precision,pre,1)
        .map(x=>((x._1,x._2),x._3)).reduceByKey(_+_)  //((precision,pre),1)
        .map(x=>((x._1._1,x._2),x._1._2)).reduceByKey(_+_) //((precision,count),sum:pre)
        .map(x=>(x._1,(x._2/x._1._2.toDouble)))
  precision.foreach(x=>println(x._1,x._2))
  }

}
