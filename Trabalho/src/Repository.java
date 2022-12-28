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

    /**
     * Método utilizado para adicionar uma skill ao repositório
     * @param skill
     */
    public void addSkill(Skill skill){
        skills.put(skillsKey++,skill);
        System.out.println("Skill adicionada com sucesso!");
    }

    /**
     * Método utilizado para adicionar uma skill ao repositório
     * @param key
     */
    public void removeSkill(int key){
        if (skills.containsKey(key)) {
            Map<Integer, Skill> updatedSkills = new HashMap<>();
            int newKey = 0;
            for (Map.Entry<Integer,Skill> entry : skills.entrySet()) {
                if (entry.getKey() != key) {
                    updatedSkills.put(newKey, entry.getValue());
                    newKey++;
                }
            }

            skills = updatedSkills;
            System.out.print("Skill removida com sucesso!");
        }
    }
    public Map<Integer,Job> getJobs(){
        return jobs;
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
            System.out.print("Oferta de Emprego removida com sucesso!");
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
        if (talents.containsKey(key)) {
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
        try{
            int option = scan.nextInt();
            while(option < 1 || option > 6){
                option = scan.nextInt();
                if(option < 1 || option > 6) System.out.println("Opção Inválida!\n1 - Alterar email\n2 - Alterar preço por hora\n3 - Alterar privacidade!\n4 - Gerir Skills\n5 - Gerir Experiências");
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
                    talent.manageSkills(scan);
                    break;
                case 5:
                    talent.manageExperiences(scan);
                    break;
            }
        }catch(InputMismatchException e){
            System.out.println("Input inválido! Insira um número!");
        }
    }


}
