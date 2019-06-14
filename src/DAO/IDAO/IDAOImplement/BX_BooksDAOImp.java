package DAO.IDAO.IDAOImplement;

import DAO.IDAO.IBX_BooksDAO;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import table.BX_Books;
import table.BX_Books;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//import java.util.regex.Pattern;
//select BX_Books.ISBN,grade from BX_Books left join (select ISBN,avg(Book_Rating) as grade from BX_Book_Ratings group by ISBN order by grade desc) as t1 on BX_Books.ISBN=t1.ISBN limit 10;

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
    /*
        判断是否为数字型字符串的方法
     */
//    private boolean isInteger(String str) {
//        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
//        return pattern.matcher(str).matches();
//    }

    @Override
    public List<BX_Books> FindByKey(Integer page,List<String> columns, String key) throws Exception {
        List<BX_Books> all = new ArrayList<BX_Books>();
        for(String column:columns) {
            String sql = "select ISBN," +
                    "Book_Title," +
                    "Book_Author," +
                    "Year_Of_Publication," +
                    "Publisher," +
                    "Image_URL_S,Image_URL_M,Image_URL_L from BX_Books "+
                    "where "+column+" like ? limit ?,?";      //列不能用点位符，会有'',要用字符串拼接1,要用1来计数，不能用count来
            pts = conn.prepareStatement(sql);
            pts.setString(1, "%"+key+"%");  //%%要用pts.setString()添加
            pts.setInt(2, (page - 1) * BX_Books.Page_Size);
            pts.setInt(3, BX_Books.Page_Size);
            ResultSet rs = pts.executeQuery();
            while (rs.next()) {
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
        }
        return all;
    }

    @Override
    public List<BX_Books> FindByPage(Integer page) throws Exception {
        List<BX_Books> all = new ArrayList<>();
        String sql = "select " +
                "BX_Books.ISBN," +
                "Book_Title," +
                "Book_Author," +
                "Year_Of_Publication," +
                "Publisher,Image_URL_M,Image_URL_L,grade " +
                "from BX_Books right join " +
                "(select ISBN,avg(Book_Rating) as grade from BX_Book_Ratings group by ISBN order by grade desc limit ?,?) as t1 " +
                "on t1.ISBN=BX_Books.ISBN";
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
//            vo.setImage_URL_S(rs.getString(6));
            vo.setImage_URL_M(rs.getString(6));
            vo.setImage_URL_L(rs.getString(7));
            BigDecimal b = new BigDecimal(rs.getDouble(8)); //四舍五入法
            vo.setGrade(b.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue());
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
    @Override
    public List<BX_Books> FindByCount() throws Exception {
        String sql = "select BX_Books.ISBN,Book_Title,Book_Author,book_count from BX_Books right join " +
                "(select ISBN,count(*) as book_count from BX_Book_Ratings group by ISBN order by book_count desc limit 15) as t1 " +
                "on t1.ISBN=BX_Books.ISBN";
        pts = conn.prepareStatement(sql);
        ResultSet rs = pts.executeQuery();
        List<BX_Books> all = new ArrayList<BX_Books>();
        while(rs.next()){
            BX_Books vo = new BX_Books();
            vo.setISBN(rs.getString(1));
            vo.setBook_Title(rs.getString(2));
            vo.setBook_Author(rs.getString(3));
            vo.setBook_Count(rs.getInt(4));
            all.add(vo);
        }
        return all;
    }

    @Override
    public List<BX_Books> FindRecommend(String username) throws Exception {
        String sql = "select " +
                "ISBN," +
                "Book_Title," +
                "Book_Author," +
                "Year_Of_Publication," +
                "Publisher,Image_URL_S,Image_URL_M,Image_URL_L " +
                "from BX_Books where ISBN " +
                "in(select ISBN from recommend where User_ID = ?)";
        pts = conn.prepareStatement(sql);
        pts.setString(1,username);
        List<BX_Books> all = new ArrayList<BX_Books>();
        ResultSet rs = pts.executeQuery();
        while (rs.next()){
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
}
