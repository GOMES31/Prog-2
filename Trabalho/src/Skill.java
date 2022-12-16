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

    // TODO - GETTERS E SETTERS
}
