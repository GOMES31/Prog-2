import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Platform {
    private ArrayList<User> users;
    private String[] startMenu,initialMenu,talentMenu,skillsMenu,jobsMenu;
    private Scanner scan = new Scanner(System.in);
    private String username,password;

    public Platform(){
        users = new ArrayList<>();
        startMenu = new String[4];
        initialMenu = new String[5];
        talentMenu = new String[6];
        skillsMenu = new String[6];
        jobsMenu = new String[6];
    }

    public void defineStartMenu(){
        startMenu[0] = "----- MENU -----";
        startMenu[1] = "1 - Iniciar Sessão";
        startMenu[2] = "2 - Criar Conta";
        startMenu[3] = "0 - [SAIR]";
    }

    public void defineInitialMenu(){
        initialMenu[0] = "----- MENU INICIAL -----";
        initialMenu[1] = "1 - Menu Talentos";
        initialMenu[2] = "2 - Menu Empregador";
        initialMenu[3] = "3 - Menu Skills";
        initialMenu[4] = "0 - [SAIR]";
    }

    public void defineTalentMenu(){
        talentMenu[0] = "---- MENU TALENTOS ----";
        talentMenu[1] = "1 - Listar Perfil de Talento";
        talentMenu[2] = "2 - Adicionar Perfil de Talento";
        talentMenu[3] = "3 - Editar Perfil de Talento";
        talentMenu[4] = "9 - VOLTAR A TRÁS!";
        talentMenu[5] = "0 - [SAIR]";
    }

    public void defineJobsMenu(){

    }

    public void defineSkillsMenu(){

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
        if(!checkIfPasswordIsCorrect(username,password)){
            while(!checkIfPasswordIsCorrect(username,password)){
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
            while(checkIfUserIsRegistered(username)){
                System.out.println("Username já registado! Tente novamente!");
                username = scan.next();
            }
        }
        System.out.println("Insira a password que pretende usar!");
        password = scan.next();
        users.add(new User(username,password,Type.NORMAL));
        saveUserToCsvFile(new User(username,password,Type.NORMAL));
        initialMenu();
    }

    public void initialMenu(){
        defineInitialMenu();
        System.out.println(String.join("\n", initialMenu) + "\nEscolha uma opção!");
    }
    public boolean checkIfUserIsRegistered(String username){
        return false;
    }

    public boolean checkIfPasswordIsCorrect(String username,String password){
        return false;
    }

    public void saveUserToCsvFile(User user){
        try{
            // Obter o caminho da pasta src
            String srcPath = Main.class.getResource("/").getPath();

            // Adicionar o nome do ficheiro ao caminho
            String filePath = srcPath + "/users.csv";

            // Abre o ficheiro em modo anexo
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true));

            // Escreve os dados do utilizador no ficheiro
            writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getType());
            writer.newLine();

            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
