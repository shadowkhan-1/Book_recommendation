package DAO.IDAO.IDAOImplement;

import DAO.IDAO.IBX_BooksDAO;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import table.BX_Books;
import table.BX_Books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BX_BooksDAOImp implements IBX_BooksDAO {
    private Connection conn;
    private PreparedStatement pts;
    public BX_BooksDAOImp(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean Create(BX_Books vo) throws Exception {
        return false;
    }

    @Override
    public BX_Books Find(String key) throws Exception {
        String sql = "select ISBN,Book_Title,Book_Author,Year_Of_Publication,Publisher,Image_URL_S,Image_URL_M,Image_URL_L " +
                "from BX_Books where key = ?";
        pts = conn.prepareStatement(sql);
        pts.setString(1,key);
        BX_Books vo = new BX_Books();
        ResultSet rs = pts.executeQuery();
        if(rs.next()){
            vo.setISBN(rs.getString(1));
            vo.setBook_Title(rs.getString(2));
            vo.setBook_Author(rs.getString(3));
            vo.setYear_Of_Publication(rs.getInt(4));
            vo.setPublisher(rs.getString(5));
            vo.setImage_URL_S(rs.getString(6));
            vo.setImage_URL_M(rs.getString(7));
            vo.setImage_URL_L(rs.getString(8));
        }
       return vo;
    }

    @Override
    public boolean Update(BX_Books vo) throws Exception {
        return false;
    }

    @Override
    public List<BX_Books> FindAll() throws Exception {
        return null;
    }

    @Override
    public BX_Books FindByKey(String column, String key) throws Exception {
        return null;
    }

    @Override
    public List<BX_Books> FindByPage(Integer page) throws Exception {
        List<BX_Books> all = new ArrayList<>();
        String sql = "select ISBN,Book_Title,Book_Author,Year_Of_Publication,Publisher,Image_URL_S,Image_URL_M,Image_URL_L " +
                "from BX_Books limit ?,?";
        pts = conn.prepareStatement(sql);
        pts.setInt(1,(page-1)*BX_Books.Page_Size);
        pts.setInt(2,BX_Books.Page_Size);
        ResultSet rs = pts.executeQuery();
        while(rs.next()){
            BX_Books vo = new BX_Books();
            vo.setISBN(rs.getString(1));
            vo.setBook_Title(rs.getString(2));
            vo.setBook_Author(rs.getString(3));
            vo.setYear_Of_Publication(rs.getInt(4));
            vo.setPublisher(rs.getString(5));
            vo.setImage_URL_S(rs.getString(6));
            vo.setImage_URL_M(rs.getString(7));
            vo.setImage_URL_L(rs.getString(8));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer FindCount() throws Exception {
        Integer count = 0;
        String sql = "select count(*) from BX_Books";
        pts = conn.prepareStatement(sql);
        ResultSet rs = pts.executeQuery();
        if(rs.next()){
            count = rs.getInt(1);
        }
        return count;
    }
}
