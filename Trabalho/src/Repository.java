import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Repository {
    private Map<Integer,Talent> talents;
    private Map<Integer,Skill> skills;
    private Map<Integer,Job> jobs;

    private ArrayList<String> clients;

    private int talentsKey = 0;
    private int skillsKey = 0;
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
    /**
     * Método utilizado para adicionar uma Oferta de emprego ao repositório
     * @param job
     */
    public void addJob(Job job){
        jobs.put(jobsKey++,job);
        System.out.println("Oferta de Emprego adicionada com sucesso!");
    }

    /**
     * Método utilizado para remover uma Oferta de emprego do repositório
     * @param key
     */
    public void removeJob(int key){
        if (jobs.containsKey(key)) {
            Map<Integer, Job> updatedJobs = new HashMap<>();
            int newKey = 0;
            for (Map.Entry<Integer,Job> entry : jobs.entrySet()) {
                if (entry.getKey() != key) {
                    updatedJobs.put(newKey, entry.getValue());
                    newKey++;
                }
            }

            jobs = updatedJobs;
            System.out.println("Oferta de Emprego removida com sucesso!");
        }
    }

    public ArrayList<String> getClients(){
        return clients;
    }

    /**
     * Método utilizado para adicionar um novo cliente ao repositório
     * @param client
     */
    public void addClient(String client){
        clients.add(client);
    }

    /**
     * Método utilizado para remover um cliente do repositório
     * @param client
     */
    public void removeClient(String client){
        for(String _client: clients){
            if(_client.equals(client)){
                clients.remove(_client);
                System.out.println("Cliente removido com sucesso!");
                break;
            }
        }
        System.out.println("Cliente não encontrado!");
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

    /**
     * Método utilizado para editar informações de um talento
     * @param key
     * @param scan
     */
    public void editTalent(int key, Scanner scan){
        Talent talent = talents.get(key);
        System.out.println("O que pretende editar neste talento?");
        System.out.println("1 - Alterar email\n2 - Alterar preço por hora\n3 - Alterar privacidade\n4 - Gerir Skills\n5 - Gerir Experiências");
        try{
            int option;
            while(true){
                try {
                    option = scan.nextInt();
                    if(option < 1 || option > 6) System.out.println("Opção Inválida!\n1 - Alterar email\n2 - Alterar preço por hora\n3 - Alterar privacidade\n4 - Gerir Skills\n5 - Gerir Experiências");
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
                    // TODO - MANAGE EXPERIENCES
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public boolean checkIfMailIsValid(String mail){
        for(Talent _talent: talents.values()){
            if(_talent.getMail().equals(mail)){
                return false;
            }
        }
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    public void manageSkills(Scanner scan, Talent talent){
        System.out.println("----- MANAGE SKILLS -----\n1 - Apagar Skill\n2 - Adicionar Skill\n3 - Editar nome de uma skill\n4 - Editar anos de experiência de uma skill!");
        try{
            int option;
            while(true){
                try {
                    option = scan.nextInt();
                    if(option < 1 || option > 4) System.out.println("Opção Inválida!\n1 - Apagar Skill\n2 - Adicionar Skill\n3 - Editar nome de uma skill\n4 - Editar anos de experiência de uma skill!");
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
                    updateSkillsRepository("remove",talent.removeSkill(scan));
                    break;
                case 2:
                    talent.addNewSkill(scan);
                    updateSkillsRepository("add",talent.addNewSkill(scan));
                    break;
                case 3:
                    talent.editSkillName(scan);
                    updateSkillsRepository("editName",talent.editSkillName(scan));
                    break;
                case 4:
                    talent.editSkillExpYears(scan);
                    updateSkillsRepository("editYears",talent.editSkillExpYears(scan));
                    break;
            }
        }catch(Exception e){
            System.out.println("Input inválido! Insira um número!");
        }
    }

    public int getSkillMapKey(Skill skill){
        int key = -1;
        for(Map.Entry<Integer,Skill> entry : skills.entrySet()){
            if(entry.getValue().equals(skill)){
                key = entry.getKey();
                return key;
            }
        }
        return key;
    }
    public void removeSkill(Skill skill){
        int key = -1;
        try{
            if(skill == null){
                System.out.println("Nenhuma skill removida pois o parâmetro passado foi nulo!");
            }
            else if(skills.containsValue(skill)) {

                key = getSkillMapKey(skill);
                Map<Integer, Skill> updatedSkills = new HashMap<>();
                int newKey = 0;
                for (Map.Entry<Integer,Skill> entry : skills.entrySet()) {
                    if (entry.getKey() != key) {
                        entry.getValue().setId(newKey);
                        updatedSkills.put(newKey, entry.getValue());
                        newKey++;
                    }
                }
                skills = updatedSkills;
                System.out.println("Skill removida com sucesso!");
            }
            else{
                System.out.println("Skill não existe no repositório principal!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Método utilizado para adicionar uma skill ao repositório
     * @param skill
     */
    public void addSkill(Skill skill){
        try{
            if(skill == null){
                System.out.println("Nenhuma skill adicionada pois o parâmetro passado foi nulo!");
            }
            else if(skills.containsValue(skill)){
                System.out.println("A skill já existe no repositório principal!");
            }
            else{
                skills.put(skillsKey++,skill);
                System.out.println("Skill adicionada com sucesso ao repositório principal!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void editSkillName(Skill skill){
        try{
            if(skill == null){
                System.out.println("Nenhuma editada pois o parâmetro passado foi nulo!");
            }
            else if(!skills.containsValue(skill)) {
                System.out.println("A skill não existe no repositório principal!");
            }
            else{
                for(Skill _skill: skills.values()){
                    if(_skill.equals(skill)){
                        _skill.setName(skill.getName());
                        skills.replace(getSkillMapKey(skill),skill,_skill);
                        break;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void editSkillYears(Skill skill){
        try{
            if(skill == null){
                System.out.println("Nenhuma editada pois o parâmetro passado foi nulo!");
            }
            else if (!skills.containsValue(skill)) {
                System.out.println("A skill não existe no repositório principal!");
            }
            else{
                for(Skill _skill: skills.values()){
                    if(_skill.equals(skill)){
                        _skill.setExpYears(skill.getExpYears());
                        skills.replace(getSkillMapKey(skill),skill,_skill);
                        break;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updateSkillsRepository(String action, Skill skill){
        try{
            switch(action){
                case "remove":
                    removeSkill(skill);
                    break;
                case "add":
                    addSkill(skill);
                    break;
                case "editName":
                    editSkillName(skill);
                    break;
                case "editYears":
                    editSkillYears(skill);
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
