package spark

import org.apache.spark.{SparkConf,SparkContext}

object userBasedModel {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("userBaseModel").setMaster("local")
    val sc = new SparkContext(conf)
    val path = "./input/BX-Book-Ratings.csv"
    val UserData = sc.textFile(path)
    val header = UserData.first()
    val rawRatings = UserData.filter(row=>row !=header).map{x=>val line = x.split(";");(line(0),line(1))}
    val ratingsRDD = rawRatings.map{x=>(x._1,x._2)}           //(user,item)
    val userRDD = rawRatings.map(x=>(x._2,x._1))              //(item.user)
    val RDD = ratingsRDD.groupByKey().map{x=>(x._1,x._2.toArray)}
    val UserRDD = userRDD.groupByKey().map(x=>(x._1,x._2.toArray))
    val user_item = RDD.cartesian(RDD).filter(x=>x._1._2!=x._2._1)
    RDD.take(3).foreach(x=>println(x._2.))
  }
    
}
