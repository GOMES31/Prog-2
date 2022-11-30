import netscape.javascript.JSObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class User {
    private String username;
    private String password;

    public User(){}

    public User(String username, String password){
            this.username = username;
            this.password = password;
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}