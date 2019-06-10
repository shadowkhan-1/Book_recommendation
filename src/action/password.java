package action;

import Factory.ServiceFactory;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import MD5.MD5Util;
import table.user;

public class password extends ActionSupport {
    private String todo;
    private String newpassword;
    private String oldpassword;
    private String username;
    private InputStream message;

    public InputStream getMessage() {
        return this.message;
    }

    public void setMessage(final InputStream message) {
        this.message = message;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getTodo() {
        return this.todo;
    }

    public void setTodo(final String todo) {
        this.todo = todo;
    }

    public String getNewpassword() {
        return this.newpassword;
    }

    public void setNewpassword(final String newpassword) {
        this.newpassword = newpassword;
    }

    public String getOldpassword() {
        return this.oldpassword;
    }

    public void setOldpassword(final String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String execute() throws Exception{
        if(getTodo().equals("check_oldpassword")){
                if(MD5Util.md5Encode(getOldpassword()).equals(ServiceFactory.getUserServiceInterface().find(getUsername()).getPassword())){
                    StringBuffer themessage;
                    themessage = new StringBuffer("success");
                    setMessage(new ByteArrayInputStream(themessage.toString().getBytes("utf-8")));
                    return "oldpassword";
                }
        }
        if(getTodo().equals("change_password")){
            user vo = new user();
            vo.setUsername(getUsername());
            vo.setPassword(MD5Util.md5Encode(getNewpassword()));
            if(ServiceFactory.getUserServiceInterface().update(vo)){
                return "newpassword";
            }
            else return "error";
        }
        return "error";
    }
}
