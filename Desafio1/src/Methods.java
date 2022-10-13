public class Methods {

    public Methods(){}
    public void sliceEmail(String _email){
        String first,second;

        // Splits the email in 2 parts
        String[] splitted_email = _email.split("@");

        // Splits the first part of the email to find the student number if it exists
        first = splitted_email[0];

        // Splits the second part of the email to find which school the student belongs
        second = splitted_email[1];
        String[] secondArr = second.split("\\.");

        // Prints the school name depending on which school the student belongs
        if(secondArr[0].equals("estg")){
            System.out.println(secondArr[0].toUpperCase());
            System.out.println("Escola Superior de Tecnologia e Gestão");
        }
        else if(secondArr[0].equals("ese")){
            System.out.println(secondArr[0].toUpperCase());
            System.out.println("Escola Superior de Educação");
        }
    }
}
