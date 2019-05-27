package spark
import org.apache.spark.{SparkContext,SparkConf}
import org.apache.spark.mllib.recommendation.{MatrixFactorizationModel,ALS,Rating}
import org.apache.spark.rdd.RDD
object ALSModel {
      val conf = new SparkConf().setAppName("ALSModel")
      val sc = new SparkContext(conf)

}
