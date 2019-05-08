package DAO.IDAO.IDAOImplement;

import DAO.IDAO.IUserDAO;
import table.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserIDAOImp implements IUserDAO {
    private Connection conn;
    private PreparedStatement pts;
    public UserIDAOImp(Connection conn){
        this.conn = conn;
    }
    public boolean Create(user vo) throws Exception {
        String sql = "insert into user(username,name,password) valuse(?,?,?)";
        pts = conn.prepareStatement(sql);
        pts.setString(1,vo.getUsername());
        pts.setString(2,vo.getName());
        pts.setString(3,vo.getPassword());
        return pts.executeUpdate()>0;
    }

    public user Find(String username) throws Exception {
        String sql = "select username,name,password from user where username like ?";
        pts = conn.prepareStatement(sql);
        user vo = new user();
        ResultSet rs = pts.executeQuery();
        if(rs.next()){
            vo.setUsername(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setPassword(rs.getString(3));
        }
        return vo;
    }

    public boolean Update(user vo) throws Exception {
        return false;
    }

    public List<user> FindAll() throws Exception {
        return null;
    }
}
