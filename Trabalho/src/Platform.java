import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Platform {
    private Repository repository;
    private String[] startMenu,initialMenu,talentMenu,skillsMenu,jobsMenu;

    private String username;

    private Scanner scan;


    public Platform(){
        repository = new Repository();
        startMenu = new String[4];
        initialMenu = new String[5];
        talentMenu = new String[7];
        skillsMenu = new String[7];
        jobsMenu = new String[7];
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
        initialMenu[4] = "0 - [SAIR]";
    }

    /**
     * Define o array do MENU TALENTOS para depois ser mais fácil imprimi-lo
     */
    public void defineTalentMenu(){
        talentMenu[0] = "----- MENU TALENTOS -----";
        talentMenu[1] = "1 - Listar Perfil de Talento";
        talentMenu[2] = "2 - Adicionar Perfil de Talento";
        talentMenu[3] = "3 - Editar Perfil de Talento";
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
        jobsMenu[5] = "5 - VOLTAR A TRÁS!";
        jobsMenu[6] = "0 - [SAIR]";
    }

    /**
     * Define o array do MENU SKILLS para depois ser mais fácil imprimi-lo
     */
    public void defineSkillsMenu(){
        skillsMenu[0] = "----- MENU SKILLS -----";
        skillsMenu[1] = "1 - Listar Skills";
        skillsMenu[2] = "2 - Adicionar Skill";
        skillsMenu[3] = "3 - Eliminar Skill";
        skillsMenu[4] = "4 - Editar Skill";
        skillsMenu[5] = "5 - VOLTAR A TRÁS!";
        skillsMenu[6] = "0 - [SAIR]";
    }

    /**
     * Método que inicializa o programa e o Scanner
     */
    public void start(){
        scan = new Scanner(System.in);
        treatStartMenu();
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
        if (!file.exists()) {
            try {
                file.createNewFile();
                saveUserToCsvFile(new User("admin","admin", Type.ADMIN));
                return false;
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
                return false;
            }
        }
        return true;
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
        initialMenu();
    }

    /**
     * Método utlizado para fazer register na aplicação
     */
    public void register(){
        System.out.println("----- REGISTER MENU -----");
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
                saveUserToCsvFile(new User(username,password,Type.NORMAL));
                System.out.println("Registado com sucesso!");
                initialMenu();
            }
        }
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
                        if(option < 0 || option > 3) System.out.println("Opção Inválida!\n" + String.join("\n", initialMenu));
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
                    initialMenu();
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
                    if(option < 0 || option > 5) System.out.println("Opção Inválida!\n" + String.join("\n", jobsMenu));
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
                    System.out.println("Exiting...!");
                    scan.close();
                    break;
                case 1:
//    TODO -                listJobs();
                    break;
                case 2:
//    TODO -                addJob();
                    break;
                case 3:
//    TODO -                deleteJob();
                    break;
                case 4:
//    TODO -                editJob();
                    break;
                case 5:
                    initialMenu();
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
                    initialMenu();
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
}
