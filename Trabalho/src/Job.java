import java.util.ArrayList;

public class Job {
    private String title;
    private String category;
    private ArrayList<String> skills;
    private String description;
    private double totalHours = 0;

    public Job(String title, String category,ArrayList<String> skills,String description,double totalHours){
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

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }
}
