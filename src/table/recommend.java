package table;

public class recommend{
    private Integer User_ID;
    private String ISBN;
    private Double pref;

    public Integer getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(Integer user_ID) {
        User_ID = user_ID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Double getPref() {
        return pref;
    }

    public void setPref(Double pref) {
        this.pref = pref;
    }
}
