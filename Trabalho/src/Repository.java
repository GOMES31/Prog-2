import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Repository {
    private Map<Integer,Talent> talents;
    private Map<Integer,Skill> skills;
    private Map<Integer,Job> jobs;

    private int talentsKey = 0;
    private int skillsKey = 0;
    private int jobsKey = 0;

    public Repository(){
        talents = new HashMap<>();
        skills = new HashMap<>();
        jobs = new HashMap<>();
    }

    public Map<Integer,Talent> getTalents(){
        return talents;
    }

    public void addTalent(Talent talent){
        talents.put(talentsKey++,talent);
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
        }
    }

    public Map<Integer,Skill> getSkills(){
        return skills;
    }
    public void addSkill(Skill skill){
        skills.put(skillsKey++,skill);
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
        }
    }
    public Map<Integer,Job> getJobs(){
        return jobs;
    }

    public void addJob(Job job){
        jobs.put(jobsKey++,job);
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
        }
    }

    // TODO - RESTO DOS METODOS
 }
