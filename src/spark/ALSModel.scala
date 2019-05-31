package spark
import org.apache.spark.{SparkContext,SparkConf}
import org.apache.spark.mllib.recommendation.{MatrixFactorizationModel,ALS,Rating}
object ALSModel {

      def main(args: Array[String]): Unit = {
            val conf = new SparkConf().setAppName("ALSModel").setMaster("local")
            val sc = new SparkContext(conf)
            val file = sc.textFile("./input/BX-Book-Ratings.csv")
            val test_file = sc.textFile("./input/test.csv")
            val test_header = test_file.first()
            val test_ratingRDD = test_file.filter(row=>row != test_header).map{_.split(";") match {
                  case Array(user,item,rate) =>Rating(user.replaceAll("\"","").toInt,item.replaceAll("\"","").hashCode(),rate.replaceAll("\"","").toDouble)
            }}
            val header = file.first()
            val Train_ratingRDD = file.filter(row=>row != header).map{_.split(";") match {
                  case Array(user,item,rate) =>Rating(user.replaceAll("\"","").toInt,item.replaceAll("\"","").hashCode(),rate.replaceAll("\"","").toDouble)
            }}
            val rank = 10
            val numIterations = 10
            val alsModel = ALS.train(Train_ratingRDD,rank,numIterations,0.01)
            val user_product = Train_ratingRDD.map{case Rating(user,product,rate)=>(user,product)}
            val predictions = alsModel.predict(user_product).map{case Rating(user,product,rate)=>((user,product),rate)}
            val ratesAndPredictions = Train_ratingRDD.map{case Rating(user,product,rate)=>((user,product),rate)}.join(predictions)
            val MSE = ratesAndPredictions.map{case ((user,product),(r1,r2))=>
                  val err = (r1 - r2)
                  err * err
            }.mean()
            println("Mean Squared Error = "+MSE)
            println("User"+"\t"+"Products"+"\t"+"Rate"+"\t"+"Prediction")
            ratesAndPredictions.take(10).foreach(
                  rating => {
                        println(rating._1._1+"\t"+rating._1._2+"\t"+rating._2._1+"\t"+rating._2._2)
                  }
            )
        alsModel.recommendProducts(276729,5).foreach(x=>println(x.product+","+x.rating))
      }
}
