public class Student {
    private String name, email, city;
    private int age, cardNumber;
    private double[] grades = new double[5];

    public Student(){}

    public Student(String _name,String _email,String _city,int _age,int _cardNumber){
        this.name = _name;
        this.email = _email;
        this.city = _city;
        this.age = _age;
        this.cardNumber = _cardNumber;
    }

    public void setName(String _name){
        this.name = _name;
    }

    public String getName(){
        return this.name;
    }

    public void setEmail(String _email){
        this.email = _email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setCity(String _city){
        this.city = _city;
    }

    public String getCity(){
        return this.city;
    }

    public void setAge(int _age){
        this.age = _age;
    }

    public int getAge(){
        return this.age;
    }

    public void setCardNumber(int _cardNumber){
        this.cardNumber = _cardNumber;
    }

    public void setGrades(double[] _grades){
        this.grades = _grades;
    }

    public double[] getGrades(){
        return this.grades;
    }

    public void addGrade(double _grade){
        for(int i = 0; i < this.grades.length; i++){
            if(this.grades[i] == 0){
                this.grades[i] = _grade;
                return;
            }
        }
    }

    public double getAverageGrade(){
        double total = 0;
        for(int i = 0; i < this.grades.length; i++){
            total +=this.grades[i];
        }
        return Math.round((total/this.grades.length)*100)/100.0;
    }

    public double bestGrade(){
        double best = this.grades[0];
        for(int i = 0; i < this.grades.length; i++){
            if(this.grades[i] > best){
                    best = this.grades[i];
            }
        }
        return best;
    }

    @Override
    public String toString() {
        String grades="";
        for(int i = 0; i < this.grades.length; i++){
            grades += this.grades[i] + "|";
        }
        return "Student Info:\nName: " + this.name + "\nEmail: " + this.email + "\nCity: " + this.city + "\nAge: " + this.age
                + "\nCard Number: " + this.cardNumber + "\nGrades: " + grades;
    }
}
