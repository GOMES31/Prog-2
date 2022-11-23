import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Platform {
    public ArrayList<User> users;

    public Platform(){
        users = new ArrayList<>();
    }

    public void registerUser(User user){
        if(checkIfIsRegistered(user.getUsername())) System.out.println("Utilizador já registado!");
        users.add(user);
//        try{
//            BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
//            writer.write("Username: " + user.getUsername() + "\tPassword: " + user.getPassword());
//            writer.close();
//        }catch(IOException e){
//            e.printStackTrace();
//        }
    }


    public boolean checkIfIsRegistered(String username){
        for(User u: users){
            if(u.getUsername().equals(username)) return true;
            break;
        }
        return false;
    }

    public void startMenu(){
        System.out.println("-- MENU --");
        System.out.println("1 - Criar Conta");
        System.out.println("2 - Iniciar Sessão");
        System.out.println("0 - [SAIR]");
    }

    public void mainMenu(){
        System.out.println("-- MENU --");
        System.out.println("1 - Skills");
        System.out.println("2 - Talento");
        System.out.println("2 - Propostas de Trabalho");
        System.out.println("0 - [SAIR]");
    }
}
