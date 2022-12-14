import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;

    private Type type;


    public User(String username, String password,Type type){
            this.username = username;
            this.password = password;
            this.type = type;
    }


    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    public Type getType(){ return type;}

    public void setType(Type type){
        this.type = type;
    }


}
