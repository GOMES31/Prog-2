import java.lang.reflect.Array;
import java.util.ArrayList;

public class Talent {
    private String name;
    private String country;
    private String mail;
    private double pricePerHour;
    private boolean isPublic;
    private ArrayList<Skill> skills;
    private ArrayList<Experience> experiences;

    public Talent(String name, String country, String mail, double pricePerHour){
        this.name = name;
        this.country = country;
        this.mail = mail;
        this.pricePerHour = pricePerHour;
        this.isPublic = true;
        this.skills = new ArrayList<>();
        this.experiences = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public ArrayList<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(ArrayList<Experience> experiences) {
        this.experiences = experiences;
    }

}
