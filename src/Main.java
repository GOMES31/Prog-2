public class Main {
    public static void main(String[] args) {
        Student manel = new Student("Manuel","manuel22@gmail.com","Porto",17,23205);
        double[] grades = new double[5];

        for(int i = 0; i < grades.length ; i++){
            grades[i] = Math.round((7 + Math.random() * (20 - 7))*100)/100.0;
            manel.addGrade(grades[i]);
        }
        System.out.println(manel.toString());
        System.out.println("Average Grades: " + manel.getAverageGrade());
        System.out.println("Best Grade: " + manel.bestGrade());
    }
}