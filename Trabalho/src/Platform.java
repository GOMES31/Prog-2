import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Platform {
    private Repository repository;
    private String[] startMenu,initialMenu,talentMenu,skillsMenu,jobsMenu;

    private String username,password;
    private Scanner scan;


    public Platform(){
        repository = new Repository();
        startMenu = new String[4];
        initialMenu = new String[5];
        talentMenu = new String[6];
        skillsMenu = new String[7];
        jobsMenu = new String[7];
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
        jobsMenu[0] = "---- MENU EMPREGADOR ----";
        jobsMenu[1] = "1 - Listar trabalhos";
        jobsMenu[2] = "2 - Adicionar trabalho";
        jobsMenu[3] = "3 - Eliminar trabalho";
        jobsMenu[4] = "4 - Editar trabalho";
        jobsMenu[5] = "9 - Voltar atrás";
        jobsMenu[6] = "0 - [SAIR]";
    }

    public void defineSkillsMenu(){
        skillsMenu[0] = "---- MENU SKILLS ----";
        skillsMenu[1] = "1 - Listar Skills";
        skillsMenu[2] = "2 - Adicionar Skill";
        skillsMenu[3] = "3 - Editar Skill";
        skillsMenu[4] = "4 - Eliminar Skill";
        skillsMenu[5] = " 9 - VOLTAR A TRÁS!";
        skillsMenu[6] = "0 - [SAIR]";
    }

    public void start(){
        scan = new Scanner(System.in);
        defineStartMenu();
        System.out.println(String.join("\n", startMenu) + "\nEscolha uma opção!");
        int option = -1;
        while(option < 0 || option > 2){
            try {
                option = scan.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Input inválido! Insira um número!");
                scan.next();
                continue;
            }
            if(option < 0 || option > 2) System.out.println("Opção Inválida!\n" + String.join("\n", startMenu));
        }
        switch(option){
            case 0:
                System.out.println("Exiting...!");
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

    public boolean checkIfUsersCsvFileExists(){
        File file = new File("src/users.csv");
        if (!file.exists()) {
            try {
                file.createNewFile();
                return false;
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    public void login(){
        scan = new Scanner(System.in);
        System.out.println("----- LOGIN MENU -----");
        System.out.println("Insira o seu username!");
        username = scan.next();
        if(!checkIfUsersCsvFileExists()){
            System.out.println("Não existem utilizadores registados!");
            start();
        }
        else if(!checkIfUserIsRegistered(username)){
            System.out.println("Utilizador não existe! \nTente criar uma conta ou então fazer login com outro username!");
            start();
        }

        System.out.println("Insira a sua password!");
        password = scan.next();

        while (!checkIfPasswordIsCorrect(username, password)) {
                System.out.println("Password errada! Tente novamente");
                password = scan.next();
        }

        System.out.println("Logado com sucesso!");
        scan.close();
        initialMenu();
    }

    public void register(){
        scan = new Scanner(System.in);
        System.out.println("----- REGISTER MENU -----");
        System.out.println("Insira o username que pretende registar!");
        username = scan.next();
        if(checkIfUsersCsvFileExists()){
            if(checkIfUserIsRegistered(username)){
                System.out.println("Utilizador já está registado! \nTente criar uma conta com outro username ou então fazer login!");
                start();
            }
            System.out.println("Insira a password que pretende usar!");
            password = scan.next();
            saveUserToCsvFile(new User(username,password,Type.NORMAL));
            System.out.println("Registado com sucesso!");
            scan.close();
            initialMenu();
        }
    }

    public void initialMenu(){
        defineInitialMenu();
        System.out.println(String.join("\n", initialMenu) + "\nEscolha uma opção!");
    }
    public boolean checkIfUserIsRegistered(String username){
        try{
            // Le o ficheiro users.csv
            BufferedReader reader = new BufferedReader(new FileReader("src/users.csv"));
            String line;
            while((line = reader.readLine()) != null){
                // Divide as linhas por onde tem virgulas, para separar o username da password e do tipo de utilizador
                String[] parts = line.split(",");
                // parts[0] = username, parts[1] = password, parts[2]
                if(parts[0].equals(username)) return true;
            }
            reader.close();
            return false;
        }catch(IOException e){
            System.out.println("Erro na leitura do ficheiro users.csv: " + e.getMessage());
            return false;
        }
    }

    public boolean checkIfPasswordIsCorrect(String username,String password){
        try{
            String savedPassword="";
            // Le o ficheiro users.csv
            BufferedReader reader = new BufferedReader(new FileReader("src/users.csv"));
            String line;
            while((line = reader.readLine()) != null){
                // Divide as linhas por onde tem virgulas, para separar o username da password e do tipo de utilizador
                String[] parts = line.split(",");
                // parts[0] = username, parts[1] = password, parts[2]
                if(parts[0].equals(username)) {
                    savedPassword = parts[1];
                    break;
                }
            }
            reader.close();
            return savedPassword.equals(password);
        }catch(IOException e){
            System.out.println("Erro na leitura do ficheiro users.csv: " + e.getMessage());
            return false;
        }
    }

    public void saveUserToCsvFile(User user){
        try{
            // Abre o ficheiro em modo anexo
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/users.csv",true));

            // Escreve os dados do utilizador no ficheiro
            writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getType());
            writer.newLine();

            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
