import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Platform {
    private Repository repository;
    private String[] startMenu,initialMenu,talentMenu,skillsMenu,jobsMenu,clientsMenu,adminMenu,managerMenu;

    private String username;

    private Scanner scan;

    private History history;

    private Type userType;


    public Platform(){
        repository = new Repository();
        startMenu = new String[4];
        initialMenu = new String[8];
        talentMenu = new String[7];
        skillsMenu = new String[7];
        jobsMenu = new String[8];
        clientsMenu = new String[6];
        adminMenu = new String[11];
        managerMenu = new String[9];

    }

    /**
     * Define o array do MENU START para depois ser mais fácil imprimi-lo
     */
    public void defineStartMenu(){
        startMenu[0] = "----- MENU -----";
        startMenu[1] = "1 - Iniciar Sessão";
        startMenu[2] = "2 - Criar Conta";
        startMenu[3] = "0 - [SAIR]";
    }

    /**
     * Define o array do MENU INICIAL para depois ser mais fácil imprimi-lo
     */
    public void defineInitialMenu(){
        initialMenu[0] = "----- MENU INICIAL -----";
        initialMenu[1] = "1 - Menu Talentos";
        initialMenu[2] = "2 - Menu Empregador";
        initialMenu[3] = "3 - Menu Skills";
        initialMenu[4] = "4 - Menu Clientes";
        initialMenu[5] = "5 - Obter relatório gastos mensais por país";
        initialMenu[6] = "6 - Obter relatório gastos mensais por skill";
        initialMenu[7] = "0 - [SAIR]";
    }

    public void defineAdminMenu(){
        adminMenu[0] = "----- MENU INICIAL -----";
        adminMenu[1] = "1 - Menu Talentos";
        adminMenu[2] = "2 - Menu Empregador";
        adminMenu[3] = "3 - Menu Skills";
        adminMenu[4] = "4 - Menu Clientes";
        adminMenu[5] = "5 - Obter relatório gastos mensais por país";
        adminMenu[6] = "6 - Obter relatório gastos mensais por skill";
        adminMenu[7] = "7 - Editar permissões";
        adminMenu[8] = "8 - Criar utilizadores";
        adminMenu[9] = "9 - Editar utilizadores";
        adminMenu[10] = "0 - [SAIR]";
    }

    public void defineManagerMenu(){
        managerMenu[0] = "----- MENU INICIAL -----";
        managerMenu[1] = "1 - Menu Talentos";
        managerMenu[2] = "2 - Menu Empregador";
        managerMenu[3] = "3 - Menu Skills";
        managerMenu[4] = "4 - Menu Clientes";
        managerMenu[5] = "5 - Obter relatório gastos mensais por país";
        managerMenu[6] = "6 - Obter relatório gastos mensais por skill";
        managerMenu[7] = "7 - Editar permissões";
        managerMenu[8] = "0 - [SAIR]";
    }
    /**
     * Define o array do MENU TALENTOS para depois ser mais fácil imprimi-lo
     */
    public void defineTalentMenu(){
        talentMenu[0] = "----- MENU TALENTOS -----";
        talentMenu[1] = "1 - Listar perfil de talento";
        talentMenu[2] = "2 - Adicionar perfil de talento";
        talentMenu[3] = "3 - Editar perfil de talento";
        talentMenu[4] = "4 - Procurar talentos";
        talentMenu[5] = "5 - VOLTAR A TRÁS!";
        talentMenu[6] = "0 - [SAIR]";
    }

    /**
     * Define o array do MENU TRABALHOS para depois ser mais fácil imprimi-lo
     */
    public void defineJobsMenu(){
        jobsMenu[0] = "----- MENU EMPREGADOR -----";
        jobsMenu[1] = "1 - Listar trabalhos";
        jobsMenu[2] = "2 - Adicionar trabalho";
        jobsMenu[3] = "3 - Eliminar trabalho";
        jobsMenu[4] = "4 - Editar trabalho";
        jobsMenu[5] = "5 - Filtrar talentos para um trabalho";
        jobsMenu[6] = "6 - VOLTAR A TRÁS!";
        jobsMenu[7] = "0 - [SAIR]";
    }

    /**
     * Define o array do MENU SKILLS para depois ser mais fácil imprimi-lo
     */
    public void defineSkillsMenu(){
        skillsMenu[0] = "----- MENU SKILLS -----";
        skillsMenu[1] = "1 - Listar skills";
        skillsMenu[2] = "2 - Adicionar skill";
        skillsMenu[3] = "3 - Eliminar skill";
        skillsMenu[4] = "4 - Editar skill";
        skillsMenu[5] = "5 - VOLTAR A TRÁS!";
        skillsMenu[6] = "0 - [SAIR]";
    }

    public void defineClientsMenu(){
        clientsMenu[0] = "----- MENU CLIENTES-----";
        clientsMenu[1] = "1 - Listar clientes";
        clientsMenu[2] = "2 - Adicionar cliente";
        clientsMenu[3] = "3 - Eliminar cliente";
        clientsMenu[4] = "4 - VOLTAR A TRÁS!";
        clientsMenu[5] = "0 - [SAIR]";
    }
    /**
     * Método que inicializa o programa e o Scanner
     */
    public void start(){
        history = new History();
        scan = new Scanner(System.in);
        if(!checkIfUsersCsvFileExists()){
            File file = new File("src/users.csv");
            try {
                file.createNewFile();
                saveUserToCsvFile(new User("admin","admin", Type.ADMIN));
                System.out.println("Ficheiro criado pois não existia! Inicie a aplicação denovo!");
                System.exit(0);
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        else{
            treatStartMenu();
        }
    }

    /**
     * Método utilizado para fazer o tratamento do MENU START
     */
    public void treatStartMenu(){
        defineStartMenu();
        System.out.println(String.join("\n", startMenu) + "\nEscolha uma opção!");
        int option;
            while(true){
                try {
                    option = scan.nextInt();
                    if(option < 0 || option > 2) System.out.println("Opção Inválida!\n" + String.join("\n", startMenu));
                    else{
                        break;
                    }
                }catch(InputMismatchException e){
                    System.out.println("Input inválido! Insira um número!");
                    scan.next();
                }
            }
            switch(option){
                case 0:
                    history.setLogoutTime(LocalDateTime.now());
                    System.out.println("Exiting...!");
                    scan.close();
                    break;
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
            }

    }

    /**
     * Método que verifica se ficheiro users.csv existe
     * @return true se o ficheiro existir
     */
    public boolean checkIfUsersCsvFileExists(){
        File file = new File("src/users.csv");
        return file.exists();
    }

    /**
     * Método utilizado para fazer login na aplicação
     */
    public void login(){
        System.out.println("----- LOGIN MENU -----");
        System.out.println("Insira o seu username!");
        username = scan.next();
        if(!checkIfUsersCsvFileExists()){
            System.out.println("Não existem utilizadores registados!");
            treatStartMenu();
        }
        else if(!checkIfUserIsRegistered(username)){
            System.out.println("Utilizador não existe! \nTente criar uma conta ou então fazer login com outro username!");
            treatStartMenu();
        }

        System.out.println("Insira a sua password!");
        String password = scan.next();

        while (!checkIfPasswordIsCorrect(username, password)) {
                System.out.println("Password errada! Tente novamente");
                password = scan.next();
        }

        System.out.println("Logado com sucesso!");

        userType = getUserType(username);

        switch(userType){
            case NORMAL:
                initialMenu();
                break;
            case MANAGER:
                initialMenuManager();
                break;
            case ADMIN:
                initialMenuAdmin();
                break;
        }
    }

    /**
     * Método utlizado para fazer register na aplicação
     */
    public void register(){
        System.out.println("----- REGISTER MENU -----");
        System.out.println("Insira o username que pretende registar!");
        username = scan.next();
            if(checkIfUsersCsvFileExists()){
                if(checkIfUserIsRegistered(username)) {
                    System.out.println("Utilizador já está registado! \nTente criar uma conta com outro username ou então fazer login!");
                    treatStartMenu();
                } else {
                    System.out.println("Insira a password que pretende usar!");
                    String password = scan.next();
                    saveUserToCsvFile(new User(username, password, Type.NORMAL));
                    System.out.println("Registado com sucesso!");
                    initialMenu();
                }
        }
    }

    public Type getUserType(String username){
        try (BufferedReader reader = new BufferedReader(new FileReader("src/users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    // parts[2] = tipo de utilizador
                    return Type.valueOf(parts[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Retorna null se o utilizador n for encontrado
        return null;
    }
    /**
     * Método utilizado para fazer o tratamento do MENU INICIAL
     */
    public void initialMenu(){
        defineInitialMenu();
        System.out.println(String.join("\n", initialMenu) + "\nEscolha uma opção!");
        int option;
            while(true){
                try {
                    option = scan.nextInt();
                        if(option < 0 || option > 6) System.out.println("Opção Inválida!\n" + String.join("\n", initialMenu));
                        else{
                            break;
                        }
                }catch(InputMismatchException e){
                    System.out.println("Input inválido! Insira um número!");
                    scan.next();
                }
            }
            switch(option){
                case 0:
                    history.setLogoutTime(LocalDateTime.now());
                    System.out.println("Exiting...!");
                    scan.close();
                    break;
                case 1:
                    talentMenu();
                    break;
                case 2:
                    jobsMenu();
                    break;
                case 3:
                    skillsMenu();
                    break;
                case 4:
                    clientsMenu();
                    break;
                case 5:
                    getMediumMonthlyWageByCountry();
                    break;
                case 6:
                    getMediumMonthlyWageBySkill();
                    break;
            }
    }

    public void initialMenuManager(){
        defineManagerMenu();
        System.out.println(String.join("\n", managerMenu) + "\nEscolha uma opção!");
        int option;
        while(true){
            try {
                option = scan.nextInt();
                if(option < 0 || option > 7) System.out.println("Opção Inválida!\n" + String.join("\n", managerMenu));
                else{
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Input inválido! Insira um número!");
                scan.next();
            }
        }
        switch(option){
            case 0:
                history.setLogoutTime(LocalDateTime.now());
                System.out.println("Exiting...!");
                scan.close();
                break;
            case 1:
                talentMenu();
                break;
            case 2:
                jobsMenu();
                break;
            case 3:
                skillsMenu();
                break;
            case 4:
                clientsMenu();
                break;
            case 5:
                getMediumMonthlyWageByCountry();
                break;
            case 6:
                getMediumMonthlyWageBySkill();
                break;
            case 7:
                editPermissions(Type.MANAGER);
                break;
        }
    }
    public void initialMenuAdmin(){
        defineAdminMenu();
        System.out.println(String.join("\n", adminMenu) + "\nEscolha uma opção!");
        int option;
        while(true){
            try {
                option = scan.nextInt();
                if(option < 0 || option > 9) System.out.println("Opção Inválida!\n" + String.join("\n", adminMenu));
                else{
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Input inválido! Insira um número!");
                scan.next();
            }
        }
        switch(option){
            case 0:
                history.setLogoutTime(LocalDateTime.now());
                System.out.println("Exiting...!");
                scan.close();
                break;
            case 1:
                talentMenu();
                break;
            case 2:
                jobsMenu();
                break;
            case 3:
                skillsMenu();
                break;
            case 4:
                clientsMenu();
                break;
            case 5:
                getMediumMonthlyWageByCountry();
                break;
            case 6:
                getMediumMonthlyWageBySkill();
                break;
            case 7:
                editPermissions(Type.ADMIN);
                break;
            case 8:
                createNewUser();
                break;
            case 9:
                editUser();
                break;
        }
    }

    public void createNewUser(){
        System.out.println("----- CRIAR NOVO UTILIZADOR -----");
        System.out.println("Insira o username que pretende registar!");
        username = scan.next();
        if(checkIfUsersCsvFileExists()){
            if(checkIfUserIsRegistered(username)){
                System.out.println("Utilizador já está registado! \nTente criar uma conta com outro username ou então fazer login!");
                treatStartMenu();
            }
            else{
                System.out.println("Insira a password que pretende usar!");
                String password = scan.next();
                Type userType;
                while (true) {
                    System.out.println("Insira o tipo para o utilizador(NORMAL,ADMIN,MANAGER)");
                    String type = scan.next().toUpperCase();
                    try {
                        userType = Type.valueOf(Type.class, type);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tipo inválido! Tente novamente.");
                    }
                }
                saveUserToCsvFile(new User(username,password,userType));
                System.out.println("Registado com sucesso!");
            }
        }

        initialMenuAdmin();
    }

    public void editUser(){
        System.out.println("----- EDITAR UTILIZADOR -----");
        System.out.println("> Lista de utilizadores:");
        printUserList();

        System.out.println("Escolha o username que pretende editar:");
        String user = scan.next();

        if(!checkIfUserIsRegistered(user)){
            while(checkIfUserIsRegistered(user)){
                System.out.println("Utilizador não existe! Tente novamente!");
                user = scan.next();
            }
        }


        System.out.println("O que pretende editar neste utilizador?");
        System.out.println("1 - Username\n2 - Password\n3 - Voltar ao menu inicial");
        int option;
        while(true){
            try {
                option = scan.nextInt();
                if(option < 1 || option > 3) System.out.println("Opção Inválida!\n1 - Username\n2 - Password\n3 - Voltar ao menu inicial");
                else{
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Input inválido! Insira um número!");
                scan.next();
            }
        }
        switch(option){
            case 1:
                editUsername(user);
                initialMenuAdmin();
                break;
            case 2:
                editPassword(user,getUserPassword(user));
                initialMenuAdmin();
                break;
            case 3:
                initialMenuAdmin();
                break;
        }
    }
    public void editUsername(String oldUsername) {
            String newUsername;
            while (true) {
                System.out.println("Insira o novo username:");
                newUsername = scan.next();
                if (!checkIfUserIsRegistered(newUsername)) {
                    break;
                }
                System.out.println("O username " + newUsername + " já está registado! Tente outro.");
            }
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/users.csv"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals(oldUsername)) {
                        lines.add(newUsername + "," + parts[1] + "," + parts[2]);
                    } else {
                        lines.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            writeFile(lines);
    }

    public void editPassword(String username,String currentPassword){
            String newPassword;
            while (true) {
                System.out.println("Insira a nova password:");
                newPassword = scan.next();
                if (!newPassword.equals(currentPassword)) {
                    break;
                }
                System.out.println("A nova password não pode ser igual à atual! Tente outra.");
            }

            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/users.csv"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals(username)) {
                        lines.add(parts[0] + "," + newPassword + "," + parts[1] );
                    } else {
                        lines.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            writeFile(lines);
    }

    public String getUserPassword(String username) {
        String password = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    password = parts[1];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;
    }


    public void writeFile(List<String> lines){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/users.csv"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void editPermissions(Type type){
        System.out.println("----- EDITAR PERMISSÕES -----");
        System.out.println("> Lista de utilizadores:");
        printUserList();

        System.out.println("Escolha o username que pretende editar as permissões:");
        String user = scan.next();

        if(!checkIfUserIsRegistered(user)){
            while(!checkIfUserIsRegistered(user)){
                System.out.println("Utilizador não existe! Tente novamente!");
                user = scan.next();
            }
        }


        Type userType = getUserType(user);

        if(type == Type.ADMIN) {
            editPermsAdmin(user);
            initialMenuAdmin();
        }

        if(type == Type.MANAGER){
            if(isAdmin(user)) {
                System.out.println("Esse utilizador é um admin, não pode editar as permissões!");
                editPermissions(type);
            }
            editPermsManager(user);
            initialMenuManager();
        }
    }

    public void editPermsManager(String username){
        System.out.println("Que pretende fazer com esse utilizador?");
        Type temp = getUserType(username);
        if(temp.equals(Type.MANAGER)) {
            System.out.println("1 - Tornar utilizador normal\n2 - Tornar admin");
            int option;
            while (true) {
                try {
                    option = scan.nextInt();
                    if (option < 1 || option > 2)
                        System.out.println("1 - Tornar utilizador normal\n2 - Tornar admin");
                    else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido! Insira um número!");
                    scan.next();
                }
            }
            switch (option) {
                case 1:
                    changeUserType(username, Type.NORMAL);
                    break;
                case 2:
                    changeUserType(username, Type.ADMIN);
                    break;
            }
        }
        else {
            System.out.println("1 - Tornar manager\n2 - Tornar admin");
            int option;
            while (true) {
                try {
                    option = scan.nextInt();
                    if (option < 1 || option > 2)
                        System.out.println("1 - Tornar manager\n2 - Tornar admin");
                    else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido! Insira um número!");
                    scan.next();
                }
            }
            switch (option) {
                case 1:
                    changeUserType(username, Type.MANAGER);
                    break;
                case 2:
                    changeUserType(username, Type.ADMIN);
                    break;
            }
        }
    }

    public void editPermsAdmin(String username){
        System.out.println("Que pretende fazer com esse utilizador?");
        Type temp = getUserType(username);
        if (temp.equals(Type.ADMIN)) {
            System.out.println("1 - Tornar utilizador normal\n2 - Tornar manager");
            int option;
            while (true) {
                try {
                    option = scan.nextInt();
                    if (option < 1 || option > 2)
                        System.out.println("1 - Tornar utilizador normal\n2 - Tornar manager");
                    else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido! Insira um número!");
                    scan.next();
                }
            }
            switch (option) {
                case 1:
                    changeUserType(username, Type.NORMAL);
                    break;
                case 2:
                    changeUserType(username, Type.MANAGER);
                    break;
            }
        }
        else if(temp.equals(Type.MANAGER)) {
            System.out.println("1 - Tornar utilizador normal\n2 - Tornar admin");
            int option;
            while (true) {
                try {
                    option = scan.nextInt();
                    if (option < 1 || option > 2)
                        System.out.println("1 - Tornar utilizador normal\n2 - Tornar admin");
                    else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido! Insira um número!");
                    scan.next();
                }
            }
            switch (option) {
                case 1:
                    changeUserType(username, Type.NORMAL);
                    break;
                case 2:
                    changeUserType(username, Type.ADMIN);
                    break;
            }
        }
        else {
            System.out.println("1 - Tornar manager\n2 - Tornar admin");
            int option;
            while (true) {
                try {
                    option = scan.nextInt();
                    if (option < 1 || option > 2)
                        System.out.println("1 - Tornar manager\n2 - Tornar admin");
                    else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido! Insira um número!");
                    scan.next();
                }
            }
            switch (option) {
                case 1:
                    changeUserType(username, Type.MANAGER);
                    break;
                case 2:
                    changeUserType(username, Type.ADMIN);
                    break;
            }
        }

    }
    public boolean isAdmin(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    Type type = Type.valueOf(parts[2]);
                    return type == Type.ADMIN;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void printUserList(){
        try (BufferedReader reader = new BufferedReader(new FileReader("src/users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                Type type = Type.valueOf(parts[2]);
                System.out.println(username + "," + password + "," + type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeUserType(String username, Type newType) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    // Update the user's type
                    lines.add(parts[0] + "," + parts[1] + "," + newType);
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeFile(lines);
    }


    public void getMediumMonthlyWageByCountry(){
        if(repository.getTalents().isEmpty()){
            System.out.println("Não existem talentos registados para obter informação!");
            switch(userType){
                case NORMAL:
                    initialMenu();
                    break;
                case MANAGER:
                    initialMenuManager();
                    break;
                case ADMIN:
                    initialMenuAdmin();
                    break;
            }
        }
        System.out.println("----- RELATÓRIO MENSAL POR PAÍS -----");
        System.out.println("Insira o nome do país que pretende ter o relatório:");
        String country1st = scan.next();
        String country2nd = scan.nextLine();

        String country = String.join(country1st,"\t",country2nd);
        System.out.println("----- RESULTADO -----");
        repository.getMediumMonthlyWageByCountry(country);

        switch(userType){
            case NORMAL:
                initialMenu();
                break;
            case MANAGER:
                initialMenuManager();
                break;
            case ADMIN:
                initialMenuAdmin();
                break;
        }

    }

    public void getMediumMonthlyWageBySkill(){
        if(repository.getTalents().isEmpty()){
            System.out.println("Não existem talentos registados para obter informação!");
            switch(userType){
                case NORMAL:
                    initialMenu();
                    break;
                case MANAGER:
                    initialMenuManager();
                    break;
                case ADMIN:
                    initialMenuAdmin();
                    break;
            }
        }
        System.out.println("----- RELATÓRIO MENSAL POR SKILL -----");
        System.out.println("Insira o nome da skill que pretende ter o relatório:");
        String name1st = scan.next();
        String name2nd = scan.nextLine();

        String name = String.join(name1st,"\t",name2nd);

        System.out.println("----- RESULTADO -----");
        repository.getMediumMonthlyWageBySkill(name);

        switch(userType){
            case NORMAL:
                initialMenu();
                break;
            case MANAGER:
                initialMenuManager();
                break;
            case ADMIN:
                initialMenuAdmin();
                break;
        }
    }
    /**
     * Método utilizado para fazer o tratamento do MENU DE TALENTOS
     */
    public void talentMenu(){
        defineTalentMenu();
        System.out.println(String.join("\n", talentMenu) + "\nEscolha uma opção!");
        int option;
            while(true){
                try {
                    option = scan.nextInt();
                    if(option < 0 || option > 5) System.out.println("Opção Inválida!\n" + String.join("\n", talentMenu));
                    else{
                        break;
                    }
                }catch(InputMismatchException e){
                    System.out.println("Input inválido! Insira um número!");
                    scan.next();
                }
            }
            switch(option){
                case 0:
                    history.setLogoutTime(LocalDateTime.now());
                    System.out.println("Exiting...!");
                    scan.close();
                    break;
                case 1:
                    listTalents();
                    break;
                case 2:
                    addTalentProfile();
                    break;
                case 3:
                    editTalentProfile();
                    break;
                case 4:
                    searchTalentProfile();
                    talentMenu();
                    break;
                case 5:
                    switch(userType){
                        case NORMAL:
                            initialMenu();
                            break;
                        case MANAGER:
                            initialMenuManager();
                            break;
                        case ADMIN:
                            initialMenuAdmin();
                            break;
                    }
                    break;
            }

    }

    /**
     * Método utilizado para listar os talentos registados no repositório
     */
    public void listTalents(){
        System.out.println("----- LISTA DE TALENTOS -----");
        // Verifica se existem talentos registados no repositório
        if(repository.getTalents().isEmpty()){
            System.out.println("Não existem talentos registados!");
            talentMenu();
        }
        else{
            // Executa um ForEach no Mapa dos Talentos para imprimir as suas respetivas informações
            for(Integer key: repository.getTalents().keySet()){
                Talent talent = repository.getTalents().get(key);
                printTalentProfileDependingOnPrivacy(talent,key);
            }
            System.out.println("Que pretende fazer agora?\n1 - Voltar ao menu de talentos\n2 - Consultar informação detalhada de um talento");
                int option;
                while(true){
                    try {
                        option = scan.nextInt();
                        if(option < 1 || option > 2) System.out.println("Opção Inválida!\n1 - Voltar ao menu de talentos\n2 - Consultar informação detalhada de um talento");
                        else{
                            break;
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Input inválido! Insira um número!");
                        scan.next();
                    }
                }
                switch(option){
                    case 1:
                        talentMenu();
                        break;
                    case 2:
                        for(Integer key: repository.getTalents().keySet()){
                            Talent talent = repository.getTalents().get(key);
                            printTalentProfileDependingOnPrivacy(talent,key);
                        }
                        System.out.println("Insira o id do talento!");
                        int id;
                        while(true){
                            try{
                                id = scan.nextInt();
                                if(id < 0 || !checkIfUserIsInTalentsMap(id)) System.out.println("Insira um id válido!");
                                else{
                                    break;
                                }
                            }catch(InputMismatchException e){
                                System.out.println("Insira um id válido!");
                                scan.next();
                            }
                        }

                        // Consulta informação detalhada de um talento através do seu id
                        detailedInfo(id);
                        break;
                }
        }
    }
    public boolean checkIfUserIsInTalentsMap(int id){
        for(Map.Entry<Integer,Talent> entry : repository.getTalents().entrySet()){
            if(entry.getKey() == id){
                return true;
            }
        }
        return false;
    }
    /**
     * Método utilizado para ver se um perfil é público ou privado e imprimir as informações respetivamente
     * @param talent
     * @param key
     */

    public void printTalentProfileDependingOnPrivacy(Talent talent,Integer key){
        if(talent.isPublic()){
            System.out.println("\tId: " + key + "\tNome: " + talent.getName() + "\tEmail: " + talent.getMail() + "\tPaís: "
                    + talent.getCountry() + "\tPreço por hora: " + talent.getPricePerHour());
            System.out.println("-------------------------------------------------------------------------------------------------");
        }
        else{
            System.out.println("\tId: " + key + "\tNome:"+ talent.getName());
            System.out.println("-------------------------------------------------------------------------------------------------");
        }
    }

    /**
     * Método utilizado para imprimir um sub menu de TALENTOS
     */
    public void listTalentsSubMenu(){
            System.out.println("Este talento tem o perfil privado! Que pretende fazer?");
            System.out.println("1 - Voltar ao menu de talentos!\n2 - Ver informação detalhada de outro talento!");
            int option;
            while(true){
                try {
                    option = scan.nextInt();
                    if(option < 0 || option > 2) System.out.println("Opção Inválida!\n1 - Voltar ao menu de talentos\n2 - Ver informação detalhada de outro talento");
                    else{
                        break;
                    }
                }catch(InputMismatchException e){
                    System.out.println("Input inválido! Insira um número!");
                    scan.next();
                }
            }
            switch(option){
                case 1:
                    talentMenu();
                    break;
                case 2:
                    System.out.println("Insira o id do talento!");
                    int id;
                    while(true){
                        try{
                            id = scan.nextInt();
                            if(id < 0 || !checkIfUserIsInTalentsMap(id)) System.out.println("Insira um id válido!");
                            else{
                                break;
                            }
                        }catch(InputMismatchException e){
                            System.out.println("Insira um id válido!");
                            scan.next();
                        }
                    }

                    // Consulta informação detalhada de um talento através do seu id
                    detailedInfo(id);
                    break;
            }
    }

    /**
     * Método que imprime a informação detalhada de um talento se o seu perfil for público
     * @param id
     */
    public void detailedInfo(int id){
            Talent talent = repository.getTalents().get(id);
            if(!talent.isPublic()){
                listTalentsSubMenu();
            }
            else{
                String lineOne = ("----- INFORMAÇÃO DETALHADA -----");
                String lineTwo = ("Nome: " + talent.getName() + "\nEmail: " + talent.getMail() + "\nPaís: "
                        + talent.getCountry() + "\nPreço por hora: " + talent.getPricePerHour());

                StringBuilder lineThree = new StringBuilder();
                lineThree.append("----- Experiências -----");
                for(Experience experience: talent.getExperiences()){
                    if(talent.getExperiences().isEmpty()){
                        System.out.println("Não existem experiências registadas!");
                        break;
                    }
                    lineThree.append("\nTitulo: ").append(experience.getTitle()).append("\tEmpresa: ").append(experience.getEnterprise()).append("\tData Inicio: ").append(experience.getStartDate()).append("\tData Fim: ").append(experience.getEndDate());
                }

                StringBuilder lineFour = new StringBuilder();
                lineFour.append("----- Skills -----");
                for(Skill skill: talent.getSkills()){
                    if(talent.getSkills().isEmpty()){
                        System.out.println("Não existem skills registadas!");
                        break;
                    }
                    lineFour.append("\nId: ").append(skill.getId()).append("\tNome: ").append(skill.getName()).append("\tÁrea: ").append(skill.getField()).append("\tAnos experiência: ").append(skill.getExpYears());
                }

                String detailedInfo = lineOne + "\n" + lineTwo + "\n" + lineThree + "\n" + lineFour;
                System.out.println(detailedInfo);
                talentMenu();
            }
    }

    /**
     * Método utilizado para adicionar um talento novo ao repositório
     */
    public void addTalentProfile(){
        System.out.println("----- ADICIONAR NOVO TALENTO -----");
            System.out.println("Insira o nome:");
            String firstname = scan.next();
            String surname = scan.nextLine();

            String name = String.join(firstname,"\t",surname);

            System.out.println("Insira o país de origem:");
            String country1st = scan.next();
            String country2nd = scan.nextLine();

            String country = String.join(country1st,"\t",country2nd);

            System.out.println("Insira o mail do talento:");
            String mail = scan.next();

            while(!repository.checkIfMailIsValid(mail)) {
                System.out.println("Mail inválido!");
                mail = scan.next();
            }

            System.out.println("Insira o preço por hora que o talento cobra:");
            double pricePerHour;
            while (true) {
                try{
                    pricePerHour = scan.nextDouble();
                    if (pricePerHour < 0) {
                        System.out.println("Insira um valor superior a 0!");
                        scan.next();
                    }else {
                        break;
                    }
                }catch(InputMismatchException e) {
                    System.out.println("Input inválido! Insira novamente o preço por hora:");
                    scan.next();
                }
            }

            Talent newTalent = new Talent(name, country, mail.toString(), pricePerHour);
            repository.addTalent(newTalent);
            addTalentProfileSubMenu(newTalent);
    }

    /**
     * Método utilizado para imprimir um sub menu de adicionar opções extra a um talento
     * @param talent
     */
    public void addTalentProfileSubMenu(Talent talent){
            System.out.println("Que pretende fazer agora?");
            System.out.println("\n1 - Adicionar uma skill ao talento\n2 - Adicionar uma experiência ao talento\n3 - Voltar ao menu de talentos\n0 - [SAIR]");
            int option;
            while(true){
                try {
                    option = scan.nextInt();
                    if(option < 0 || option > 3) System.out.println("Opção Inválida!\n1 - Adicionar uma skill ao talento\n2 - Adicionar uma experiência ao talento\n3 - Voltar ao menu de talentos\n0 - [SAIR]");
                    else{
                        break;
                    }
                }catch(InputMismatchException e){
                    System.out.println("Input inválido! Insira um número!");
                    scan.next();
                }
            }
            switch(option){
                case 0:
                    history.setLogoutTime(LocalDateTime.now());
                    System.out.println("Exiting...!");
                    scan.close();
                    break;
                case 1:
                    talent.addNewSkill(scan);
                    addTalentProfileSubMenu(talent);
                    break;
                case 2:
                    talent.addNewExperience(scan);
                    addTalentProfileSubMenu(talent);
                    break;
                case 3:
                    talentMenu();
                    break;
            }
    }

    /**
     * Método utilizado para editar um perfil de talento
     */
    public void editTalentProfile(){
        // Verifica se existem talentos registados no repositório
        if(repository.getTalents().isEmpty()){
            System.out.println("Não existem talentos registados!");
            talentMenu();
        }else{
            System.out.println("----- EDITAR PERFIL DE TALENTO -----");
            System.out.println("----- LISTA DE PERFIS -----");
            for(Integer key: repository.getTalents().keySet()){
                Talent talent = repository.getTalents().get(key);
                System.out.println("\tId: " + key + "\tNome: " + talent.getName() + "\tEmail: " + talent.getMail() + "\tPaís: "
                        + talent.getCountry() + "\tPreço por hora: " + talent.getPricePerHour());
                System.out.println("----------------------");
            }
            System.out.println("Insira o id do perfil que pretende editar:");
            int id;
                while(true){
                    try{
                        id = scan.nextInt();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Input inválido!");
                        scan.next();
                    }
                }
                editTalentProfileSubMenu(id);
        }
    }

    /**
     * Método que imprime as opções que o utilizador tem para editar um perfil de talento
     * @param id
     */
    public void editTalentProfileSubMenu(int id) {

            // Verifica se existem talentos registados com o id passado como parâmetro
            if(id < 0){
                System.out.println("Insira um id superior a 0!");
                int newId;
                while(true){
                    try{
                        newId = scan.nextInt();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Input inválido!");
                        scan.next();
                    }
                }
                editTalentProfileSubMenu(newId);
            }
            else if(repository.getTalents().get(id) == null) {
                System.out.println("Não existem talentos com esse id!");
                talentMenu();
            }
            else {
                try {
                System.out.println("Que pretende fazer?");
                System.out.println("1 - Eliminar talento\n2 - Editar atributos do talento\n3 - Voltar ao menu de talentos\n0 - [SAIR]");
                    int option;
                    while(true){
                        try {
                            option = scan.nextInt();
                            if(option < 0 || option > 3)System.out.println("Opção Inválida!\n1 - Eliminar talento\n2 - Editar atributos do talento\n3 - Voltar ao menu de talentos\n0 - [SAIR]");
                            else{
                                break;
                            }
                        }catch(InputMismatchException e){
                            System.out.println("Input inválido! Insira um número!");
                            scan.next();
                        }
                    }
                switch (option) {
                    case 0:
                        history.setLogoutTime(LocalDateTime.now());
                        System.out.println("Exiting...!");
                        scan.close();
                        break;
                    case 1:
                        repository.removeTalent(id);
                        talentMenu();
                        break;
                    case 2:
                        repository.editTalent(id,scan);
                        talentMenu();
                        break;
                    case 3:
                        talentMenu();
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("Insira um número!");
            }
        }

    }

    public void searchTalentProfile(){
        System.out.println("----- PROCURAR TALENTOS -----");
        if(repository.getTalents().isEmpty()){
            System.out.println("Não existem talentos registados!");
            return;
        }
        System.out.println("Insira uma combinação de skills que quer que o talento tenha!\n> Insira numa só linha separado por virgulas. Exemplo: JAVA,PHP,etc.");
        String skillsString;
        String[] skills=null;
        try{
            skillsString = scan.next();
            skills = skillsString.split(",");
        }catch(Exception e){
            System.out.println("Ocorreu um erro! Insira os filtros denovo!");
            scan.next();
        }

        StringBuilder filters= new StringBuilder();
        if(skills!=null){
            for(String skill: skills){
                filters.append(skill).append("\t");
            }
        }

        System.out.println("----- RESULTADO -----");
        System.out.println("Filtros: " + filters.toString().toLowerCase());
        ArrayList<Talent> matchingTalents = repository.searchTalent(skills);
        if (matchingTalents == null) {
            System.out.println("Não existem talentos registados!");
            return;
        }

        if (matchingTalents.isEmpty()) {
            System.out.println("Nenhum talento encontrado com esse combo de skills!");
            return;
        }

        // Imprime os talentos por ordem alfabética
        for (Talent talent : matchingTalents) {
            System.out.println("Nome: " + talent.getName()+ "\tPreço por hora: " + talent.getPricePerHour() + "\tPaís: " + talent.getCountry());
        }
    }


    /**
     * Método utilizado para fazer o tratamento do menu de Ofertas de emprego
     */
    public void jobsMenu(){
        defineJobsMenu();
        System.out.println(String.join("\n", jobsMenu) + "\nEscolha uma opção!");
            int option;
            while(true){
                try {
                    option = scan.nextInt();
                    if(option < 0 || option > 6) System.out.println("Opção Inválida!\n" + String.join("\n", jobsMenu));
                    else{
                        break;
                    }
                }catch(InputMismatchException e){
                    System.out.println("Input inválido! Insira um número!");
                    scan.next();
                }
            }
            switch(option){
                case 0:
                    history.setLogoutTime(LocalDateTime.now());
                    System.out.println("Exiting...!");
                    scan.close();
                    break;
                case 1:
                    repository.listJobs();
                    jobsMenu();
                    break;
                case 2:
                    repository.addJob(scan);
                    jobsMenu();
                    break;
                case 3:
                    repository.deleteJob(scan);
                    jobsMenu();
                    break;
                case 4:
                    editJobMenu();
                    break;
                case 5:
                    filterTalentsForJob();
                    jobsMenu();
                    break;
                case 6:
                    switch(userType){
                        case NORMAL:
                            initialMenu();
                            break;
                        case MANAGER:
                            initialMenuManager();
                            break;
                        case ADMIN:
                            initialMenuAdmin();
                            break;
                    }
                    break;
            }

    }

    public void filterTalentsForJob(){
        System.out.println("----- PROCURAR TALENTOS -----");
        if(repository.getTalents().isEmpty()){
            System.out.println("Não existem talentos registados!");
            return;
        }

        repository.listJobs();
        System.out.println("Insira o id do trabalho que pretende encontrar talentos:");
        int id;
        while (true) {
            try {
                try {
                    id = scan.nextInt();
                    if (id < 0 || !repository.checkIfJobExists(id)) {
                        System.out.println("Insira um id válido!");
                    }
                    else {
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Insira um id válido!");
                    scan.next();
                }
            } catch (InputMismatchException e) {
                System.out.println("Input inválido!");
                scan.next();
            }
        }

        ArrayList<String> requiredSkills = repository.getJobs().get(id).getSkills();
        StringBuilder filters= new StringBuilder();
        if(requiredSkills!=null){
            for(String skill: requiredSkills){
                filters.append(skill).append("\t");
            }
        }

        System.out.println("----- RESULTADO -----");
        System.out.println("Filtros: " + filters.toString().toLowerCase());

        ArrayList<Talent> matchingTalents = repository.filterTalentsForJob(requiredSkills);
        double totalHours = repository.getJobs().get(id).getTotalHours();
        if (matchingTalents == null) {
            System.out.println("Não existem talentos registados!");
            return;
        }

        if (matchingTalents.isEmpty()) {
            System.out.println("Nenhum talento encontrado com esse combo de skills!");
            return;
        }

        // Imprime os talentos por valor total
        for (Talent talent : matchingTalents) {
            double value = talent.getPricePerHour() * totalHours;
            double roundedValue = Math.round(value * 100.0) / 100.0;
            System.out.println("Nome: " + talent.getName()+ "\tPreço total: " + roundedValue + "\tPaís: " + talent.getCountry());
        }

    }

    public void editJobMenu(){
        if(repository.getJobs().isEmpty()){
                System.out.println("Não existem ofertas de emprego registadas!");
                jobsMenu();
        }
        System.out.println("----- EDITAR TRABALHO -----");
        System.out.println("1 - Voltar ao menu de trabalhos\n2 - Editar Titulo\n3 - Editar Categoria\n4 - Editar skills necessárias\n5 - Editar descrição\n6 - Editar número de horas totais");
        int option;
        while(true){
            try {
                option = scan.nextInt();
                if(option < 1 || option > 6) System.out.println("Opção Inválida!\n" + String.join("\n", jobsMenu));
                else{
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Input inválido! Insira um número!");
                scan.next();
            }
        }
        switch(option){
            case 1:
                jobsMenu();
                break;
            case 2:
                repository.editTitle(scan);
                jobsMenu();
                break;
            case 3:
                repository.editCategory(scan);
                jobsMenu();
                break;
            case 4:
                repository.editRequiredSkills(scan);
                jobsMenu();
                break;
            case 5:
                repository.editDescription(scan);
                jobsMenu();
                break;
            case 6:
                repository.editTotalHours(scan);
                jobsMenu();
                break;
        }

    }
    /**
     * Método utilizado para fazer o tratamento do menu de Skills
     */
    public void skillsMenu(){
        defineSkillsMenu();
        System.out.println(String.join("\n", skillsMenu) + "\nEscolha uma opção!");
            int option;
            while(true){
                try {
                    option = scan.nextInt();
                    if(option < 0 || option > 5) System.out.println("Opção Inválida!\n" + String.join("\n", skillsMenu));
                    else{
                        break;
                    }
                }catch(InputMismatchException e){
                    System.out.println("Input inválido! Insira um número!");
                    scan.next();
                }
            }
            switch(option){
                case 0:
                    history.setLogoutTime(LocalDateTime.now());
                    System.out.println("Exiting...!");
                    scan.close();
                    break;
                case 1:
                    repository.listSkills();
                    skillsMenu();
                    break;
                case 2:
                    repository.addNewSkill(scan);
                    skillsMenu();
                    break;
                case 3:
                    repository.deleteSkill(scan);
                    skillsMenu();
                    break;
                case 4:
                    repository.editSkillName(scan);
                    skillsMenu();
                    break;
                case 5:
                    switch(userType){
                        case NORMAL:
                            initialMenu();
                            break;
                        case MANAGER:
                            initialMenuManager();
                            break;
                        case ADMIN:
                            initialMenuAdmin();
                            break;
                    }
                    break;
            }
    }

    public void clientsMenu(){
        defineClientsMenu();
        System.out.println(String.join("\n", clientsMenu) + "\nEscolha uma opção!");
        int option;
        while(true){
            try {
                option = scan.nextInt();
                if(option < 0 || option > 4) System.out.println("Opção Inválida!\n" + String.join("\n", clientsMenu));
                else{
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Input inválido! Insira um número!");
                scan.next();
            }
        }
        switch(option){
            case 0:
                history.setLogoutTime(LocalDateTime.now());
                System.out.println("Exiting...!");
                scan.close();
                break;
            case 1:
                repository.listClients();
                clientsMenu();
                break;
            case 2:
                repository.addClient(scan);
                clientsMenu();
                break;
            case 3:
                repository.removeClient(scan);
                clientsMenu();
                break;
            case 4:
                switch(userType){
                    case NORMAL:
                        initialMenu();
                        break;
                    case MANAGER:
                        initialMenuManager();
                        break;
                    case ADMIN:
                        initialMenuAdmin();
                        break;
                }
                break;
        }
    }
    /**
     * Método utilizado para ver se um utilizador está registado no ficheiro users.csv
     * @param username
     * @return true se o utilizador existir
     */
    public boolean checkIfUserIsRegistered(String username){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/users.csv"));
            String line;
            while((line = reader.readLine()) != null){
                // Divide uma linha do ficheiro users.csv a partir da vírgula
                String[] parts = line.split(",");
                // parts[0] -> username , parts[1] -> password , parts[2] -> tipo de utilizador
                if(parts[0].equalsIgnoreCase(username)) return true;
            }
            reader.close();
            return false;
        }catch(IOException e){
            System.out.println("Erro na leitura do ficheiro users.csv: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método utilizado para ver se a password de um determinado utilizador está correta
     * @param username
     * @param password
     * @return true se a password estiver correta
     */
    public boolean checkIfPasswordIsCorrect(String username,String password){
        try{
            String savedPassword="";
            BufferedReader reader = new BufferedReader(new FileReader("src/users.csv"));
            String line;
            while((line = reader.readLine()) != null){
                // Divide uma linha do ficheiro users.csv a partir da vírgula
                String[] parts = line.split(",");
                // parts[0] -> username , parts[1] -> password , parts[2] -> tipo de utilizador
                if(parts[0].equalsIgnoreCase(username)) {
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

    /**
     * Guarda um utilizador novo no ficheiro users.csv
     * @param user
     */
    public void saveUserToCsvFile(User user){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/users.csv",true));

            writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getType());
            writer.newLine();

            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void saveHistoryToCSV(String username) throws IOException {
        LocalDateTime loginTime = history.getLoginTime();
        LocalDateTime logoutTime = history.getLogoutTime();
        List<String> usedCommands = history.getUsedCommands();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String loginTimeStr = loginTime.format(formatter);
        String logoutTimeStr = logoutTime.format(formatter);

        String filePath = "src/userHistory.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(username).append(",");
            sb.append(loginTimeStr).append(",");
            sb.append(logoutTimeStr).append(",");
            for (String command : usedCommands) {
                sb.append(command).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
            writer.write(sb.toString());
        }
    }

}
