package table;

public class BX_Books {
    private String ISBN;
    private String Book_Title;
    private String Book_Author;
    private Integer Year_Of_Publication;
    private String Publisher;
    private String Image_URL_S;
    private String Image_URL_M;
    private String Image_URL_L;
    private Double Grade;
    private Integer Book_Count;

    public Integer getBook_Count() {
        return this.Book_Count;
    }

    public void setBook_Count(final Integer book_Count) {
        this.Book_Count = book_Count;
    }

    public static final Integer Page_Size = 12;
    public Double getGrade() {
        return this.Grade;
    }

    public void setGrade(final double grade) {
        this.Grade = grade;
    }
    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(final String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBook_Title() {
        return this.Book_Title;
    }

    public void setBook_Title(final String book_Title) {
        this.Book_Title = book_Title;
    }

    public String getBook_Author() {
        return this.Book_Author;
    }

    public void setBook_Author(final String book_Author) {
        this.Book_Author = book_Author;
    }

    public Integer getYear_Of_Publication() {
        return this.Year_Of_Publication;
    }

    public void setYear_Of_Publication(final Integer year_Of_Publication) {
        this.Year_Of_Publication = year_Of_Publication;
    }

    public String getPublisher() {
        return this.Publisher;
    }

    public void setPublisher(final String publisher) {
        this.Publisher = publisher;
    }

    public String getImage_URL_S() {
        return this.Image_URL_S;
    }

    public void setImage_URL_S(final String image_URL_S) {
        this.Image_URL_S = image_URL_S;
    }

    public String getImage_URL_M() {
        return this.Image_URL_M;
    }

    public void setImage_URL_M(final String image_URL_M) {
        this.Image_URL_M = image_URL_M;
    }

    public String getImage_URL_L() {
        return this.Image_URL_L;
    }

    public void setImage_URL_L(final String image_URL_L) {
        this.Image_URL_L = image_URL_L;
    }

}
