public class Subject {
    Student[] students;

    public Subject(){
        students = new Student[20];
    }

    public void setStudents(Student[] _students){
        this.students = _students;
    }

    public Student[] getStudents(){
        return students;
    }

    public void addStudent(Student _student){
        for(int i = 0; i < this.students.length; i++){
            if(this.students[i] == null){
                this.students[i] = _student;
                return;
            }
            else if(this.students[i].getCardNumber() == _student.getCardNumber()){
                System.out.println("That student is already registered!");
                return;
            }
        }
    }

    public String returnStudent(int _cardNumber){
        for(int i = 0; i < this.students.length; i++){
            if(this.students[i] == null){
                return "Student not found!";
            }
            else if(this.students[i].getCardNumber() == _cardNumber){
                return "Student Found!\n Name: " + this.students[i].getName() + " & Card Number: " + this.students[i].getCardNumber();
            }
        }
        return "Student not found!";
    }

    public String bestStudent(){
        Student bestStudent = this.students[0];
        for(int i = 0; i < this.students.length; i++){
            if(bestStudent == null){
                return "No students found!";
            }
            else if(this.students[i] == null){
                return "The student with the best average grade is: " +  bestStudent.getName() + "!";
            }
            else if(this.students[i].getAverageGrade() > bestStudent.getAverageGrade()){
                bestStudent = this.students[i];
            }
            else if(this.students[i].getAverageGrade() == bestStudent.getAverageGrade()){
                if(this.students[i].getCardNumber() < bestStudent.getCardNumber()){
                    bestStudent = this.students[i];
                }
            }
        }
        return "The student with the best average grade is: " +  bestStudent.getName() + "!";
    }

    @Override
    public String toString(){
        String firstline = "Students in this subject:";
        String students = "";
        for(int i = 0; i < this.students.length; i++){
            if(this.students[i]==null){
                break;
            }
            students += "\nName: " + this.students[i].getName() + " & Best Grade: " + this.students[i].bestGrade() +
                        " & Average of Grades: " + this.students[i].getAverageGrade();
        }
        return firstline + students;
    }
}
