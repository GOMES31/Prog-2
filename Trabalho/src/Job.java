import java.util.ArrayList;

public class Job {
    private String title;
    private String category;
    private ArrayList<Skill> skills;
    private String description;
    private int totalHours = 0;

    public Job(String title, String category,ArrayList<Skill> skills,String description,int totalHours){
        this.title = title;
        this.category = category;
        this.skills = skills;
        this.description = description;
        this.totalHours = totalHours;
    }

    // TODO - GETTERS E SETTERS
}
