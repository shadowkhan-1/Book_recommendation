package spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object Precision_recall {
  def main(args: Array[String]): Unit = {
  val conf = new SparkConf().setAppName("test").setMaster("local")
  val sc = new SparkContext(conf)
  Logger.getRootLogger.setLevel(Level.WARN)
  val test_data = sc.textFile("./input/test/1_30000_15000")
    .map{x=>val line=x.split(";");(line(0),line(1))}
  val data = sc.textFile("./output/cosine_k_3_30000/part-00000")
    .map{x=>val line=x.split(",");(line(0),line(1))}
    val test_data_join = test_data.join(data)
  val result = test_data_join.filter(x=>x._2._1==x._2._2).map(x=>(x._1,1)).reduceByKey(_+_).sortByKey()       //取出item相等的，即TP
//  result.foreach(x=>println("result:"+x._1,x._2))
    val test_count = test_data_join.map(x=>(x._1,1)).reduceByKey(_+_).sortByKey()   //取出用户所有的item 即 TP+FN
//    test_count.foreach(x=>println("test_count:"+x._1,x._2))
  val data_count = data.map(x=>(x._1,1)).reduceByKey(_+_).sortByKey()           //取出userid所有的item,即TP+FN
//      data_count.foreach(x=>println("data_count:"+x._1,x._2))
    val precision = result
      .join(data_count)
      .map{x=>(("precision",(x._2._1/x._2._2.toDouble)))}  //(precision,pre,1_5000)
        .groupByKey().map(x=>(x._1,x._2.reduce(_+_)/x._2.count(x=>true)))
  precision.foreach(x=>println(x._1,x._2))
    val recall  = result.join(test_count).map{x=>(("recall",(x._2._1/x._2._2.toDouble)))}
      .groupByKey().map(x=>(x._1,x._2.reduce(_+_)/x._2.count(x=>true)))
    recall.foreach(x=>println(x._1,x._2))
    val F = precision.map(x=>("F",x._2)).join(recall.map(x=>("F",x._2))).map(x=>(x._1,x._2._1*x._2._2*2/(x._2._1+x._2._2)))
    F.foreach(x=>println(x._1,x._2))
  }

}
