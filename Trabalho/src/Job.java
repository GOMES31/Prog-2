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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }
}
