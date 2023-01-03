import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Experience {
    private String title;
    private String enterprise;
    private LocalDate startDate;
    private LocalDate endDate;

    private int id;

    private static int currentId = 0;


    public Experience(String title, String enterprise, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.enterprise = enterprise;
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = currentId++;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
