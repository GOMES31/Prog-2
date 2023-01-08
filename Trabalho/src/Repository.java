import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Repository {
    private Map<Integer,Talent> talents;
    private static Map<Integer,Skill> skills;
    private Map<Integer,Job> jobs;

    private ArrayList<String> clients;

    private int talentsKey = 0;
    private static int skillsKey = 0;
    private int jobsKey = 0;

    public Repository(){
        talents = new HashMap<>();
        skills = new HashMap<>();
        jobs = new HashMap<>();
        clients = new ArrayList<>();
    }


    public Map<Integer,Talent> getTalents(){
        return talents;
    }

    public void setTalents(Map<Integer,Talent> talents){
        this.talents = talents;
    }
    /**
     * Método utilizado para adicionar um talento ao repositório
     * @param talent
     */
    public void addTalent(Talent talent){
        talents.put(talentsKey++,talent);
        System.out.println("Talento adicionado com sucesso!");
    }


    public Map<Integer,Skill> getSkills(){
        return skills;
    }


    public void setSkills(Map<Integer,Skill> skills){
        this.skills = skills;
    }


    public Map<Integer,Job> getJobs(){
        return jobs;
    }

    public void setJobs(Map<Integer,Job> jobs){
        this.jobs = jobs;
    }


    public void addJob(Scanner scan){
        System.out.println("------ ADICIONAR TRABALHO -----");
        System.out.println("Título do trabalho:");
        String title1st = scan.next();
        String title2nd = scan.nextLine();

        String title = String.join(title1st,"\t",title2nd);
        while(checkIfTitleIsInUse(title)){
            System.out.println("Já existe um trabalho com esse título! Insira outro!");
            title1st = scan.next();
            title2nd = scan.nextLine();
            title = String.join(title1st,"\t",title2nd);
        }

        System.out.println("Categoria do trabalho:");
        String category1st = scan.next();
        String category2nd = scan.nextLine();

        String category = String.join(category1st,"\t",category2nd);

        System.out.println("Insira as skills para este trabalho!\n> Insira numa só linha separado por virgulas. Exemplo: JAVA,PHP,etc.");
        String skillsString;
        String[] skills = null;
        try{
            skillsString = scan.next();
            skills = skillsString.split(",");
        }catch(Exception e){
            System.out.println("Ocorreu um erro! Insira os filtros denovo!");
            scan.next();
        }


        ArrayList<String> requiredSkills = new ArrayList<>(Arrays.asList(skills));

        System.out.println("Insira uma breve descrição do trabalho!");
        String desc1st = scan.next();
        String desc2nd = scan.nextLine();

        String description = String.join(desc1st,"\t",desc2nd);

        System.out.println("Insira o número total de horas do trabalho: ");
        double totalHours;
        while (true) {
            try {
                totalHours = scan.nextDouble();
                if (totalHours < 0) {
                    System.out.println("Insira um valor superior a 0!");
                    scan.next();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input inválido! Insira novamente o número total de horas:");
                scan.next();
            }
        }

        jobs.put(jobsKey++,new Job(title,category,requiredSkills,description,totalHours));
        System.out.println("Trabalho adicionado com sucesso ao repositório!");
    }

    public boolean checkIfTitleIsInUse(String title){
        for(Integer key: jobs.keySet()){
            Job job = jobs.get(key);
            if(job.getTitle().equalsIgnoreCase(title)){
                return true;
            }
        }
        return false;
    }
    public void listJobs(){
        System.out.println("----- LISTA DE OFERTAS DE EMPREGO -----");
        if(jobs.isEmpty()){
            System.out.println("Não existem ofertas de emprego registadas!");
            return;
        }

        for(Integer key: jobs.keySet()) {
            Job job = jobs.get(key);
            System.out.println("Id: " + key + "\nTítulo: " + job.getTitle() + "\nCategoria: " + job.getCategory() + "\nDescrição: " + job.getDescription() + "\nHoras totais: " + job.getTotalHours());
            if (job.getSkills().isEmpty()) {
                System.out.println("Skills necessárias: Nenhuma");
            } else {
                String skillsString = String.join(" ", job.getSkills());
                System.out.print("Skills necessárias: " + skillsString);
            }
            System.out.println("\n-------------------------------");
        }
    }


    public void deleteJob(Scanner scan){
        if(jobs.isEmpty()){
            System.out.println("Não existem ofertas de emprego registadas!");
            return;
        }
        listJobs();
        System.out.println("Insira o id do trabalho que pretende remover:");
        int id;
        while (true) {
            try {
                try {
                    id = scan.nextInt();
                    if (id < 0 || !checkIfJobExists(id)) {
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

        jobs.remove(id,jobs.get(id));

        updateJobsMapKeys();
        System.out.println("Trabalho removido com sucesso do repositório!");
    }

    public boolean checkIfJobExists(int id){
        return jobs.containsValue(jobs.get(id));
    }
    public void updateJobsMapKeys(){
        // Criar um mapa novo para resetar as keys
        Map<Integer, Job> newJobs= new HashMap<>();
        int jobId = 0;
        for (Job job : jobs.values()) {
            newJobs.put(jobId,job);
            jobId++;
        }
        jobs = newJobs;
    }

    public ArrayList<Talent> filterTalentsForJob(ArrayList<String> skills){
            if(talents.isEmpty()){
                return null;
            }
            ArrayList<Talent> matchingTalents = new ArrayList<>();
            for (Talent talent : talents.values()) {
                if(hasMatchingSkills(talent, skills)) {
                    matchingTalents.add(talent);
                }
            }

            // Se só encontrar um talento não faz a ordenação
            if(matchingTalents.size() == 1){
                return matchingTalents;
            }
            // Ordena os talentos encontrados por ordem alfabética
            matchingTalents.sort((t1, t2) -> Double.compare(t2.getPricePerHour(), t1.getPricePerHour()));
            return matchingTalents;
    }

    public void editTitle(Scanner scan){
        listJobs();
        System.out.println("Insira o id do trabalho que pretende editar o título:");
        int id;
        while (true) {
            try {
                try {
                    id = scan.nextInt();
                    if (id < 0 || !checkIfJobExists(id)) {
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

        System.out.println("Insira o título novo:");
        String title1st = scan.next();
        String title2nd = scan.nextLine();

        String title = String.join(title1st,"\t",title2nd);
        while(checkIfTitleIsInUse(title)){
            System.out.println("Já existe um trabalho com esse título! Insira outro!");
            title1st = scan.next();
            title2nd = scan.nextLine();
            title = String.join(title1st,"\t",title2nd);
        }

        if(title.equals(jobs.get(id).getTitle())) {
            while(title.equalsIgnoreCase(jobs.get(id).getTitle())) {
                System.out.println("Esse título é o atual! Insira outro!");
                title1st = scan.next();
                title2nd = scan.nextLine();
                title = String.join(title1st,"\t",title2nd);
            }
        }
        jobs.get(id).setTitle(title);
        System.out.println("Título atualizado com sucesso!");

    }

    public void editCategory(Scanner scan){
        listJobs();
        System.out.println("Insira o id do trabalho que pretende editar a categoria:");
        int id;
        while (true) {
            try {
                try {
                    id = scan.nextInt();
                    if (id < 0 || !checkIfJobExists(id)) {
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

        System.out.println("Insira a categoria nova:");
        String category1st = scan.next();
        String category2nd = scan.nextLine();

        String category = String.join(category1st,"\t",category2nd);

        if(category.equals(jobs.get(id).getCategory())) {
            while(category.equalsIgnoreCase(jobs.get(id).getCategory())) {
                System.out.println("Essa categoria é a atual! Insira outra!");
                category1st = scan.next();
                category2nd = scan.nextLine();
                category = String.join(category1st,"\t",category2nd);
            }
        }

        jobs.get(id).setCategory(category);
        System.out.println("Categoria atualizada com sucesso!");
    }

    public void editDescription(Scanner scan){
        listJobs();
        System.out.println("Insira o id do trabalho que pretende editar a descrição:");
        int id;
        while (true) {
            try {
                try {
                    id = scan.nextInt();
                    if (id < 0 || !checkIfJobExists(id)) {
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

        System.out.println("Insira a descrição nova: ");
        String desc1st = scan.next();
        String desc2nd = scan.nextLine();

        String description = String.join(desc1st,"\t",desc2nd);

        if(description.equals(jobs.get(id).getDescription())) {
            while(description.equalsIgnoreCase(jobs.get(id).getDescription())) {
                System.out.println("Esse descrição é a atual! Insira outra!");
                desc1st = scan.next();
                desc2nd = scan.nextLine();
                description = String.join(desc1st,"\t",desc2nd);
            }
        }

        jobs.get(id).setDescription(description);
        System.out.println("Descrição atualizada com sucesso!");
    }

    public void editTotalHours(Scanner scan){
        listJobs();
        System.out.println("Insira o id do trabalho que pretende editar o total de horas:");
        int id;
        while (true) {
            try {
                try {
                    id = scan.nextInt();
                    if (id < 0 || !checkIfJobExists(id)) {
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
        System.out.println("Insira o número total de horas do trabalho: ");
        double totalHours;
        while (true) {
            try {
                totalHours = scan.nextDouble();
                if (totalHours < 0) {
                    System.out.println("Insira um valor superior a 0!");
                    scan.next();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input inválido! Insira novamente o número total de horas:");
                scan.next();
            }
        }

        if(totalHours == jobs.get(id).getTotalHours()){
            while(totalHours == jobs.get(id).getTotalHours()){
                try {
                    System.out.println("Esse é o número de horas total atual! Insira outro!");
                    totalHours = scan.nextDouble();
                    if (totalHours < 0) {
                        System.out.println("Insira um valor superior a 0!");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido! Insira novamente o número total de horas:");
                    scan.next();
                }
            }
        }

        jobs.get(id).setTotalHours(totalHours);
        System.out.println("Número total de horas atualizado com sucesso!");
    }

    public void editRequiredSkills(Scanner scan){
        listJobs();
        System.out.println("Insira o id do trabalho que pretende editar as skills necessárias:");
        int id;
        while (true) {
            try {
                try {
                    id = scan.nextInt();
                    if (id < 0 || !checkIfJobExists(id)) {
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

        ArrayList<String> currentSkills = jobs.get(id).getSkills();

        System.out.println("Insira as novas skills para este trabalho!\n> Insira numa só linha separado por virgulas. Exemplo: JAVA,PHP,etc.");
        while(true){
            String skillsString;
            String[] skills = null;
            try{
                skillsString = scan.next();
                skills = skillsString.split(",");
            }catch(Exception e){
                System.out.println("Ocorreu um erro! Insira os filtros denovo!");
                scan.next();
            }

            ArrayList<String> requiredSkills = new ArrayList<>(Arrays.asList(skills));

            if(!currentSkills.containsAll(requiredSkills)){
                jobs.get(id).setSkills(requiredSkills);
                break;
            }
            System.out.println("Essas skills necessárias são as atuais do trabalho! Insira outras!\n> Insira numa só linha separado por virgulas. Exemplo: JAVA,PHP,etc.");
        }

        System.out.println("Skills atualizadas com sucesso!");
    }


    /**
     * Método utilizado para adicionar um novo cliente ao repositório
     * @param scan
     */
    public void addClient(Scanner scan){
        System.out.println("----- ADICIONAR CLIENTES -----");
        System.out.println("Insira o nome do cliente que pretende adicionar!");
        String name1st = scan.next();
        String name2nd = scan.nextLine();

        String name = String.join(name1st,"\t",name2nd);

        while(checkIfClientNameIsInUse(name)){
            System.out.println("Cliente já registado com esse nome! Tente outro!");
            name1st = scan.next();
            name2nd = scan.nextLine();

            name = String.join(name1st,"\t",name2nd);
        }
        clients.add(name);

        System.out.println("Cliente adicionado com sucesso!");
    }

    public boolean checkIfClientNameIsInUse(String name){
        for(String _client: clients){
            if(_client.equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public void listClients(){
        System.out.println("----- LISTA DE CLIENTES -----");
        if(clients.isEmpty()){
            System.out.println("Não existem clientes registados!");
            return;
        }
        int i = 0;
        for (String client : clients) {
            System.out.println("Id: " + i + "\tNome:" + client);
            i++;
        }
    }

    /**
     * Método utilizado para remover um cliente do repositório
     * @param scan
     */
    public void removeClient(Scanner scan) {
        System.out.println("----- REMOVER CLIENTES -----");
        if (clients.isEmpty()) {
            System.out.println("Não existem clientes registados!");
            return;
        }

        int i = 0;
        for (String client : clients) {
            System.out.println("Id: " + i + "\tNome: " + client);
            i++;
        }

        System.out.println("Insira o id do cliente que pretende remover!");

        int id;
        while (true) {
            try {
                id = scan.nextInt();
                if (id < 0 || id >= clients.size()) {
                    System.out.println("Insira um id válido!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input inválido!");
                scan.next();
            }
        }

        clients.remove(id);
        System.out.println("Cliente removido com sucesso!");

    }

    /**
     * Método utilizado para remover um talento do repositório
     * @param key
     */
    public void removeTalent(int key) {
        if(talents.containsKey(key)) {
            Map<Integer, Talent> updatedTalents = new HashMap<>();
            int newKey = 0;
            for (Map.Entry<Integer, Talent> entry : talents.entrySet()) {
                if (entry.getKey() != key) {
                    updatedTalents.put(newKey, entry.getValue());
                    newKey++;
                }
            }
            talents = updatedTalents;
            System.out.println("Talento removido com sucesso!");
        }
    }

    public ArrayList<Talent> searchTalent(String[] skills) {
        if(talents.isEmpty()){
            return null;
        }
        ArrayList<Talent> matchingTalents = new ArrayList<>();
        for (Talent talent : talents.values()) {
            if(hasMatchingSkills(talent, skills)) {
                matchingTalents.add(talent);
            }
        }

        // Se só encontrar um talento não faz a ordenação
        if(matchingTalents.size() == 1){
            return matchingTalents;
        }
        // Ordena os talentos encontrados por ordem alfabética
        matchingTalents.sort((t1, t2) -> t1.getName().compareTo(t2.getName()));
        return matchingTalents;
    }

    private boolean hasMatchingSkills(Talent talent, String[] skills) {
        boolean hasAllSkills = true;
        for (String skill : skills) {
            boolean found = false;
            for (Skill _skill : talent.getSkills()) {
                String field = _skill.getField().replace("\t", "");
                if (field.equalsIgnoreCase(skill)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                hasAllSkills = false;
                break;
            }
        }
        return hasAllSkills;
    }
    private boolean hasMatchingSkills(Talent talent, ArrayList<String> skills) {
        boolean hasAllSkills = true;
        for (String skill : skills) {
            boolean found = false;
            for (Skill _skill : talent.getSkills()) {
                String field = _skill.getField().replace("\t", "");
                if (field.equalsIgnoreCase(skill)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                hasAllSkills = false;
                break;
            }
        }
        return hasAllSkills;
    }
    /**
     * Método utilizado para editar informações de um talento
     * @param key
     * @param scan
     */
    public void editTalent(int key, Scanner scan){
        Talent talent = talents.get(key);
        System.out.println("O que pretende editar neste talento?");
        System.out.println("1 - Alterar email\n2 - Alterar preço por hora\n3 - Alterar privacidade\n4 - Gerir Skills\n5 - Gerir Experiências");
            int option;
            while(true){
                try {
                    option = scan.nextInt();
                    if(option < 1 || option > 5) System.out.println("Opção Inválida!\n1 - Alterar email\n2 - Alterar preço por hora\n3 - Alterar privacidade\n4 - Gerir Skills\n5 - Gerir Experiências");
                    else{
                        break;
                    }
                }catch(InputMismatchException e){
                    System.out.println("Input inválido! Insira um número!");
                    scan.next();
                }
            }
            switch(option) {
                case 1:
                    talent.editMail(scan,talents);
                    break;
                case 2:
                    talent.editPricePerHour(scan);
                    break;
                case 3:
                    talent.editPrivacy();
                    break;
                case 4:
                    manageSkills(scan,talent);
                    break;
                case 5:
                    manageExperiences(scan,talent);
                    break;
            }
    }
    public boolean checkIfMailIsValid(String mail){
        for(Talent _talent: talents.values()){
            if(_talent.getMail().equalsIgnoreCase(mail)){
                System.out.println("O mail já se encontra em uso!");
                return false;
            }
        }
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    public void manageSkills(Scanner scan, Talent talent){
        System.out.println("----- MANAGE SKILLS -----\n1 - Apagar Skill\n2 - Adicionar Skill\n3 - Editar nome de uma skill\n4 - Editar anos de experiência de uma skill");
            int option;
            while(true){
                try {
                    option = scan.nextInt();
                    if(option < 1 || option > 4) System.out.println("Opção Inválida!\n1 - Apagar Skill\n2 - Adicionar Skill\n3 - Editar nome de uma skill\n4 - Editar anos de experiência de uma skill");
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
                    talent.removeSkill(scan);
                    break;
                case 2:
                    talent.addNewSkill(scan);
                    break;
                case 3:
                    talent.editSkillName(scan);
                    break;
                case 4:
                    talent.editSkillExpYears(scan);
                    break;
            }
    }


    public void listSkills(){
        if(skills.isEmpty()){
            System.out.println("Não existem skills registadas no repositório");
            return;
        }
        System.out.println("----- LISTA DE SKILLS NO REPOSITÓRIO -----");
            for (Integer key : skills.keySet()) {
                Skill skill = skills.get(key);
                System.out.println("Id: " + key + "\tNome: " + skill.getName() + "\tÁrea: " + skill.getField());
            }
    }
    public static void removeSkill(String name){
        for (Skill skill : skills.values()) {
            if(skill.getName().equalsIgnoreCase(name)) {
                // Obter a chave para o objeto atual
                Integer key = getKeyByValue(skills, skill);
                // Remover a skill do mapa usando a key
                skills.remove(key);
                updateSkillMapKeys();
                System.out.println("Skill removida com sucesso!");
                return;
            }
        }
        System.out.println("Skill não existe no repositório!");
    }

    public static void updateSkillMapKeys(){
        // Criar um mapa novo para resetar as keys
        Map<Integer, Skill> newSkills = new HashMap<>();
        int skillId = 0;
        for (Skill skill : skills.values()) {
            skill.setId(skillId);
            newSkills.put(skillId, skill);
            skillId++;
        }
        skills = newSkills;
    }

    /**
     * Método para obter a chave de um elemento num mapa
     * @param map
     * @param value
     * @return
     * @param <T>
     * @param <E>
     */
    private static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Método utilizado para adicionar uma skill ao repositório
     * @param skill
     */
    public static void addSkill(Skill skill){
        for (Skill _skill : skills.values()) {
            if(_skill.getName().equalsIgnoreCase(skill.getName())) {
                System.out.println("Já existe uma skill com esse nome no repositório principal!");
                return;
            }
        }

        skills.put(skillsKey++,skill);
        System.out.println("Skill adicionada com sucesso ao repositório principal!");
    }

    public static void editSkillName(String oldName, String newName){
        for (Skill skill : skills.values()) {
            if(skill.getName().equalsIgnoreCase(oldName)) {
                // Obter a chave para o objeto atual
                Integer key = getKeyByValue(skills, skill);
                // Atualizar o nome da skill
                skill.setName(newName);
                System.out.println("Skill atualizada com sucesso no repositório!");
                return;
            }
        }
        System.out.println("A skill não existe no repositório!");

    }



    public void addNewSkill(Scanner scan){
        System.out.println("------ ADICIONAR SKILL -----");
        System.out.println("Nome da skill:");
        String name1st = scan.next();
        String name2nd = scan.nextLine();

        String name = String.join(name1st,"\t",name2nd);
        while(checkIfNameIsInUse(name)){
            System.out.println("Já existe uma skill com esse nome. Insira outro!");
            name1st = scan.next();
            name2nd = scan.nextLine();
            name = String.join(name1st,"\t",name2nd);
        }

        System.out.println("Área da skill:");
        String field1st = scan.next();
        String field2nd = scan.nextLine();

        String field = String.join(field1st,"\t",field2nd);

        skills.put(skillsKey++,new Skill(name,field));

        int skillId = 0;
        for(Skill skill: skills.values()){
            skill.setId(skillId);
            skillId++;
        }
        System.out.println("Skill adicionada com sucesso ao repositório!");
    }

    public void deleteSkill(Scanner scan){
        if(skills.isEmpty()){
            System.out.println("Este repositório não tem skills registadas!");
            return;
        }
        System.out.println("----- LISTA DE SKILLS -----");
        for(Integer key: skills.keySet()){
            Skill skill = skills.get(key);
            System.out.println("Id: " + key + "\tNome: " + skill.getName() + "\tAnos Experiência: " + skill.getExpYears());
        }
        System.out.println("Insira o id da skill que pretende remover:");
        int id;
        while (true) {
            try {
                try {
                    id = scan.nextInt();
                    if (id < 0 || !checkIfSkillExists(id)) {
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

        // Ve se algum talento tem aquela skill antes de remover
        if (checkIfAnyTalentHasThisSkill(skills.get(id).getName())) {
            System.out.println("Não pode remover essa Skill pois está associada a um talento!");
            return;
        }

        skills.remove(id,skills.get(id));

        updateSkillMapKeys();
        System.out.println("Skill removida com sucesso do repositório!");
    }



    public void editSkillName(Scanner scan){
        System.out.println("----- LISTA DE SKILLS -----");
        for(Integer key: skills.keySet()){
            Skill skill = skills.get(key);
            System.out.println("Id: " + key + "\tNome: " + skill.getName() + "\tAnos Experiência: " + skill.getExpYears());
        }
        System.out.println("Insira o id da skill que pretende editar o nome:");

        int id;
        while (true) {
            try {
                try{
                    id = scan.nextInt();
                    if (id < 0 || !checkIfSkillExists(id)) {
                        System.out.println("Insira um id válido!");
                    } else {
                        break;
                    }
                }catch(IndexOutOfBoundsException e){
                    System.out.println("Insira um id válido!");
                    scan.next();
                }
            } catch (InputMismatchException e) {
                System.out.println("Input inválido!");
                scan.next();
            }
        }
        System.out.println("Insira o novo nome para a skill:");
        String name1st = scan.next();
        String name2nd = scan.nextLine();

        String name = String.join(name1st,"\t",name2nd);
        while(checkIfNameIsInUse(name)){
            System.out.println("Já existe uma skill com esse nome. Insira outro!");
            name1st = scan.next();
            name2nd = scan.nextLine();
            name = String.join(name1st,"\t",name2nd);
        }

        skills.get(id).setName(name);

    }

    public boolean checkIfAnyTalentHasThisSkill(String name){
            for(Integer key: talents.keySet()){
                Talent talent = talents.get(key);
                for (Skill skill : talent.getSkills()) {
                    if (skill.getName().equalsIgnoreCase(name)) {
                        return true;
                    }
                }
            }
            return false;
    }
    public boolean checkIfSkillExists(int id){
        return skills.containsValue(skills.get(id));
    }
    public boolean checkIfNameIsInUse(String name){
        for(Integer key: skills.keySet()){
            Skill skill = skills.get(key);
            if(skill.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public void getMediumMonthlyWageBySkill(String skill){
        int monthlyHours = 176;
        double monthlyCost = 0;

        ArrayList<Talent> filteredTalents = filterTalentsBySkill(skill);

        System.out.println("Filtro usado: " + skill);

        if(filteredTalents.isEmpty()){
            System.out.println("Não existem dados para essa skill!");
            return;
        }

        int id = 0;
        for(Talent talent: filteredTalents){
            double value = talent.getPricePerHour() * monthlyHours;
            double roundedValue = Math.round(value * 100.0) / 100.0;
            System.out.println("Id: " + id + "\tNome: " + talent.getName() + "\tCusto por mês: " + roundedValue);
            monthlyCost += (talent.getPricePerHour()*monthlyHours);
            id++;
        }

        double totalValue = monthlyCost;
        double roundedTotalValue = Math.round(totalValue * 100.0) / 100.0;
        System.out.println("Custo total por mês: " + roundedTotalValue);
    }

    public ArrayList<Talent> filterTalentsBySkill(String skill){
        if(talents.isEmpty()){
            return null;
        }
        ArrayList<Talent> matchingTalents = new ArrayList<>();
        for (Talent talent : talents.values()) {
            if (talent.getSkills().stream().anyMatch(_skill -> _skill.getField().equals(skill))) {
                matchingTalents.add(talent);
            }
        }

        return matchingTalents;
    }

    public void getMediumMonthlyWageByCountry(String country){
        int monthlyHours = 176;
        double monthlyCost = 0;

        ArrayList<Talent> filteredTalents = filterTalentsByCountry(country);

        System.out.println("Filtro usado: " + country);

        if(filteredTalents.isEmpty()){
            System.out.println("Não existem dados para esse país!");
            return;
        }

        int id = 0;
        for(Talent talent: filteredTalents){
            double value = talent.getPricePerHour() * monthlyHours;
            double roundedValue = Math.round(value * 100.0) / 100.0;
            System.out.println("Id: " + id + "\tNome: " + talent.getName() + "\tCusto por mês: " + roundedValue);
            monthlyCost += (talent.getPricePerHour()*monthlyHours);
            id++;
        }

        double totalValue = monthlyCost;
        double roundedTotalValue = Math.round(totalValue * 100.0) / 100.0;
        System.out.println("Custo total por mês: " + roundedTotalValue);
    }

    public ArrayList<Talent> filterTalentsByCountry(String country){
        if(talents.isEmpty()){
            return null;
        }
        ArrayList<Talent> matchingTalents = new ArrayList<>();
        for (Talent talent : talents.values()) {
            if(talent.getCountry().equalsIgnoreCase(country)){
                matchingTalents.add(talent);
            }
        }

        return matchingTalents;
    }


    public void manageExperiences(Scanner scan,Talent talent){
        System.out.println("----- MANAGE EXPERIENCES -----\n1 - Apagar Experiência\n2 - Adicionar Experiência\n3 - Editar título de uma experiência\n4 - Editar data de fim de uma experiência");
            int option;
            while(true){
                try {
                    option = scan.nextInt();
                    if(option < 1 || option > 4) System.out.println("\n1 - Apagar Experiência\n2 - Adicionar Experiência\n3 - Editar título de uma experiência\n4 - Editar data de fim de uma experiência");
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
                    talent.removeExperience(scan);
                    break;
                case 2:
                    talent.addNewExperience(scan);
                    break;
                case 3:
                    talent.editExpTitle(scan);
                    break;
                case 4:
                    talent.editExpEndDate(scan);
                    break;
            }
    }

}
