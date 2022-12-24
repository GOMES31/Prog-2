import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Experience {
    private String title;
    private String enterprise;
    private LocalDate startDate;
    private LocalDate endDate;



    public Experience(String title, String enterprise, String startDate, String endDate,DateTimeFormatter dateTimeFormatter) {
        this.title = title;
        this.enterprise = enterprise;
        this.startDate = LocalDate.parse(startDate,dateTimeFormatter);
        this.endDate = LocalDate.parse(endDate,dateTimeFormatter);
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
