package DAO.IDAO.IDAOImplement;

import DAO.IDAO.IUser_FavoriteDAO;
import table.User_Favorite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class User_FavoriteDAOImp implements IUser_FavoriteDAO {
    private Connection conn;
    private PreparedStatement pts;
    public User_FavoriteDAOImp(Connection conn){
        this.conn = conn;
    }
    @Override
    public boolean Create(User_Favorite vo) throws Exception {
        String sql = "insert into User_Favorite(username,ISBN) values(?,?)";
        pts = conn.prepareStatement(sql);
        pts.setString(1,vo.getUsername());
        pts.setString(2,vo.getISBN());
        return pts.executeUpdate()>0;
    }

    @Override
    public User_Favorite Find(String username) throws Exception {
        String sql = "select username,ISBN from User_Favorite where username like ?";
        pts = conn.prepareStatement(sql);
        ResultSet rs = pts.executeQuery();
        User_Favorite vo = new User_Favorite();
        if(rs.next()){
            vo.setUsername(rs.getString(1));
            vo.setISBN(rs.getString(2));
        }
        return  vo;
    }

    @Override
    public boolean Update(User_Favorite vo) throws Exception {
        return false;
    }

    @Override
    public List<User_Favorite> FindAll() throws Exception {
        String sql = "select id,username,ISBN from User_Favorite where username like ?";
        pts = conn.prepareStatement(sql);
        ResultSet rs = pts.executeQuery();
        List<User_Favorite> all = new ArrayList<User_Favorite>();
        if(rs.next()){
            User_Favorite vo = new User_Favorite();
            vo.setId(rs.getInt(1));
            vo.setUsername(rs.getString(2));
            vo.setISBN(rs.getString(3));
            all.add(vo);
        }
        return all;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "delete from User_Favorite where id = ?";
        pts = conn.prepareStatement(sql);
        pts.setInt(1,id);
        return pts.executeUpdate()>0;
    }
}