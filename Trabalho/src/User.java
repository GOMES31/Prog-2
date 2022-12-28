import java.util.ArrayList;

public class User {
    private String username;
    private String password;

    private Type type;

    private ArrayList<History> history;

    public User(String username, String password,Type type){
            this.username = username;
            this.password = password;
            this.type = type;
            history = new ArrayList<>();
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

    public ArrayList<History> getHistory(){
        return history;
    }

    public void addNewHistory(History newHistory){
        history.add(newHistory);
    }

}
