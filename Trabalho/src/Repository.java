import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public void addTalent(Talent talent){
        talents.put(talentsKey++,talent);
        System.out.println("Talento adicionado com sucesso!");
    }

    public void removeTalent(int key) {
        if (talents.containsKey(key)) {
            // Cria um mapa novo com as chaves atualizadas
            Map<Integer, Talent> updatedTalents = new HashMap<>();
            int newKey = 0;
            for (Map.Entry<Integer, Talent> entry : talents.entrySet()) {
                if (entry.getKey() != key) {
                    updatedTalents.put(newKey, entry.getValue());
                    newKey++;
                }
            }

            // Atualiza o mapa inicial
            talents = updatedTalents;
            System.out.println("Talento removido com sucesso!");
        }
    }

    public Map<Integer,Skill> getSkills(){
        return skills;
    }
    public void addSkill(Skill skill){
        skills.put(skillsKey++,skill);
        System.out.println("Skill adicionada com sucesso!");
    }

    public void removeSkill(int key){
        if (skills.containsKey(key)) {
            // Cria um mapa novo com as chaves atualizadas
            Map<Integer, Skill> updatedSkills = new HashMap<>();
            int newKey = 0;
            for (Map.Entry<Integer,Skill> entry : skills.entrySet()) {
                if (entry.getKey() != key) {
                    updatedSkills.put(newKey, entry.getValue());
                    newKey++;
                }
            }

            // Atualiza o mapa inicial
            skills = updatedSkills;
            System.out.print("Skill removida com sucesso!");
        }
    }
    public Map<Integer,Job> getJobs(){
        return jobs;
    }

    public void addJob(Job job){
        jobs.put(jobsKey++,job);
        System.out.println("Oferta de Emprego adicionada com sucesso!");
    }
    public void removeJob(int key){
        if (jobs.containsKey(key)) {
            // Cria um mapa novo com as chaves atualizadas
            Map<Integer, Job> updatedJobs = new HashMap<>();
            int newKey = 0;
            for (Map.Entry<Integer,Job> entry : jobs.entrySet()) {
                if (entry.getKey() != key) {
                    updatedJobs.put(newKey, entry.getValue());
                    newKey++;
                }
            }

            // Atualiza o mapa inicial
            jobs = updatedJobs;
            System.out.print("Oferta de Emprego removida com sucesso!");
        }
    }

    public ArrayList<String> getClients(){
        return clients;
    }

    public void addClient(String client){
        clients.add(client);
    }

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
 }
