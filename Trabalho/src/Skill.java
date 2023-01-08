public class Skill {
    private String name;
    private int id;
    private static int currentId = 0;
    private String field;
    private double expYears;

    public Skill(String name,String field,double expYears){
        this.name = name;
        this.id = currentId++;
        this.field = field;
        this.expYears = expYears;
    }

    public Skill(String name,String field){
        this.name = name;
        this.field = field;
        this.id = currentId++;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getField() {
        return field;
    }

    public double getExpYears() {
        return expYears;
    }

    public void setExpYears(double expYears) {
        this.expYears = expYears;
        System.out.println("Anos de experiência atualizados com sucesso!");
    }

}
