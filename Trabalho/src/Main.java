public class Main {
    public static void main(String[]args){
        Platform IT = new Platform();
        User teste = new Admin("TesteUser","teste");
        System.out.println(IT.checkIfIsRegistered(teste.getUsername()));
        User teste2 = new Admin("TesteUser","teste");
        System.out.println(IT.checkIfIsRegistered(teste2.getUsername()));
    }
}
