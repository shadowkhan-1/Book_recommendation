package spark
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import java.io.Serializable
import java.sql.{Connection, PreparedStatement, SQLException}

import connection.DatabaseConnection
class KeepData extends Serializable {
      def keepData(userRecomm: RDD[UserRecomm]):Unit = {
      @transient
      val dbc = new DatabaseConnection()
      @transient
      val conn:Connection = dbc.getConnection()
      try{
            val sql = "insert into table recommend(User_ID,ISBN,pref) values(?,?,?)"
            userRecomm.foreachPartition(x=> {
                  for (info <- x) {
                        val pts:PreparedStatement = conn.prepareStatement(sql)
                        pts.setInt(1,info.userid.toInt)
                        pts.setString(2,info.itemid.replaceAll("\"",""))
                        pts.setDouble(3,info.pref)
                  }
            }
            )
      }
      catch{
      case e:SQLException => e.printStackTrace()
      }
      finally{
            conn.close()
      }

      }
}
