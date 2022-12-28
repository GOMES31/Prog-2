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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("Nome atualizado com sucesso!");
    }

    public int getId() {
        return id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public double getExpYears() {
        return expYears;
    }

    public void setExpYears(double expYears) {
        this.expYears = expYears;
        System.out.println("Anos de experiência atualizados com sucesso!");
    }
}
