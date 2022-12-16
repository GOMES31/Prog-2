public class Main {
    public static void main(String[]args){
        Platform plat = new Platform();

        User teste = new User("teste","password", Type.NORMAL);
        User teste2 = new User("teste2","password",Type.NORMAL);
        plat.saveUserToCsvFile(teste);
        plat.saveUserToCsvFile(teste2);
    }
}
