package DAO.IDAO.IDAOImplement;

import DAO.IDAO.IUser_FavoriteDAO;
import table.BX_Books;
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
        String sql = "select id,username,ISBN from User_Favorite where username like ? limit ?,?";
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
    public boolean Delete(Integer id) throws Exception {
        String sql = "delete from User_Favorite where id = ?";
        pts = conn.prepareStatement(sql);
        pts.setInt(1,id);
        return pts.executeUpdate()>0;
    }

    @Override
    public boolean FindExist(User_Favorite vo) throws Exception {
        String sql = "select username from User_Favorite where ISBN like ?";
        pts = conn.prepareStatement(sql);
        pts.setString(1,vo.getISBN());
        ResultSet rs = pts.executeQuery();
        if(rs.next()){
            if(rs.getString(1).equals(vo.getUsername())){
                return true;
            }
            else {return false;}
        }
        return false;
    }

    @Override
    public List<BX_Books> FindByPage(String username,Integer pages) throws Exception {
        String sql = "select " +
                "ISBN," +
                "Book_Title," +
                "Book_Author," +
                "Year_Of_Publication," +
                "Publisher,Image_URL_M,Image_URL_L " +
                "from BX_Books where ISBN in " +
                "(select ISBN from User_Favorite where username like ?) limit ?,?";
        pts = conn.prepareStatement(sql);
        pts.setString(1,username);
        pts.setInt(2,(pages-1)* BX_Books.Page_Size);
        pts.setInt(3,BX_Books.Page_Size);
        ResultSet rs = pts.executeQuery();
        List<BX_Books> all = new ArrayList<BX_Books>();
        if(rs.next()){
            BX_Books vo = new BX_Books();
            vo.setISBN(rs.getString(1));
            vo.setBook_Title(rs.getString(2));
            vo.setBook_Author(rs.getString(3));;
            vo.setYear_Of_Publication(rs.getInt(4));
            vo.setPublisher(rs.getString(5));
            vo.setImage_URL_M(rs.getString(6));
            vo.setImage_URL_L(rs.getString(7));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer FindCount() throws Exception {
        Integer count = 0;
        String sql = "select count(*) from User_Favorite";
        pts = conn.prepareStatement(sql);
        ResultSet rs = pts.executeQuery();
        if(rs.next()){
            count = rs.getInt(1);
        }
        return count;
    }
}
