import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Platform {
    private ArrayList<User> users;
    private String[] startMenu,initialMenu;
    private Scanner scan = new Scanner(System.in);
    private String username,password;

    public Platform(){
        users = new ArrayList<>();
        startMenu = new String[4];
        initialMenu = new String[]
    }

    public void defineStartMenu(){
        startMenu[0] = "----- MENU -----";
        startMenu[1] = "1 - Iniciar Sessão";
        startMenu[2] = "2 - Criar Conta";
        startMenu[3] = "0 - [SAIR]";
    }

    public void defineInitialMenu(){
        initialMenu[]
    }

    public void start(){
        defineStartMenu();
        System.out.println(String.join("\n", startMenu) + "\nEscolha uma opção!");
        int option = -1;
        while(option < 0 || option > 2){
            option = scan.nextInt();
            if(option < 0 || option > 2) System.out.println("Opção Inválida!\n" + String.join("\n", startMenu));
        }
        switch(option){
            case 0:
                System.out.println("Adeus!");
                break;
            case 1:
                login();
                break;
            case 2:
                register();
                break;
        }
        scan.close();
    }

    public void login(){
        System.out.println("----- LOGIN MENU -----");
        System.out.println("Insira o seu username!");
        username = scan.next();
        if(!checkIfUserIsRegistered(username)){
            System.out.println("Utilizador não existe! \nTente criar uma conta ou então fazer login com outro username!");
            start();
        }
        System.out.println("Insira a sua password!");
        password = scan.next();
        if(!checkIfPasswordIsCorrect(password)){
            while(!checkIfPasswordIsCorrect(password)){
                System.out.println("Password errada! Tente novamente");
                password = scan.next();
            }
        }
        initialMenu();
    }

    public void register(){
        System.out.println("----- REGISTER MENU -----");
        System.out.println("Insira o username que pretende registar!");
        username = scan.next();
        if(checkIfUserIsRegistered(username)){
            while(!checkIfUserIsRegistered(password)){
                System.out.println("Username já registado! Tente novamente");
                username = scan.next();
            }
        }
        System.out.println("Insira a password que pretende usar!");
        password = scan.next();
        users.add(new User(username,password));
        saveUserToCsvFile(new User(username,password));
        initialMenu();
    }

    public void initalMenu(){
        defineInitialMenu();
    }
    public boolean checkIfUserIsRegistered(String username){

    }

    public boolean checkIfPasswordIsCorrect(String password){

    }

    public void saveUserToCsvFile(User user){

    }
}
