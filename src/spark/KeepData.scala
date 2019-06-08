package spark
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import java.io.Serializable
import java.sql.{Connection, PreparedStatement, SQLException}

import connection.DatabaseConnection
class KeepData extends Serializable {
      def keepData(userRecomm: RDD[UserRecomm]):Unit = {
            userRecomm.foreachPartition(x=> {
              // foreachPartition把rdd转成iterater,可以进行遍历
              //尽量把成员变量写在map,fitter,foreachPartition,foreach里面，防止不能序列化问题
              val dbc = new DatabaseConnection()
              val conn: Connection = dbc.getConnection()
              val sql = "insert into recommend(User_ID,ISBN,pref) values(?,?,?)"
              val pts: PreparedStatement = conn.prepareStatement(sql)
              try {
                for (info <- x) {
                  pts.setInt(1, info.userid.toInt)
                  pts.setString(2, info.itemid.replaceAll("\"",""))
                  pts.setDouble(3, info.pref)
                  pts.executeUpdate()          //插入数据库要进行pts.executeUpdate()等操作
                }
              }
              catch{
                case e:SQLException => e.printStackTrace()
              }
              finally{
                println("存入结束")
                conn.close()
              }
            }
            )
      }

}
