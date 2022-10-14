public class Main {
    public static void main(String[] args) {

        // Some students just for testing
        Student vitor = new Student("Vitor Costa","vitor.costa@gmail.com","Braga",20,10000);
        Student fernando = new Student("Fernando Dias","nandinhopt@gmail.com","Porto",21,10500);
        Student ana = new Student("Ana Vitória","anavitoria99@hotmail.com","Lisboa",19,9202);

        // Random subject just for testing
        Subject math = new Subject();

        // Adding the students to the subject
//        math.addStudent(vitor);
//        math.addStudent(fernando);
//        math.addStudent(ana);

        // Giving the students random grades just for testing
        for(Student student: math.getStudents()){
            if(student==null){
                break;
            }
            student.setGrades(student.randomGrades());
        }

        System.out.println("--------------------------------------");

        // Prints the students registered in a subject
        System.out.println(math.toString());

        System.out.println("--------------------------------------");

        // Test if student is already registered or not
        math.addStudent(vitor);

        System.out.println("--------------------------------------");

        // Print the student info
        System.out.println(fernando.toString());

        System.out.println("--------------------------------------");

        // Prints the student giving the number as an input
        // Prints the student named Ana Vitória
        System.out.println(math.returnStudent(9202));

        System.out.println("--------------------------------------");

        // Prints student not found
        System.out.println(math.returnStudent(8000));

        System.out.println("--------------------------------------");

        // Prints the student with the best average
        System.out.println(math.bestStudent());


    }
}