import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Talent {
    private String name;
    private String country;
    private String mail;
    private double pricePerHour;
    private boolean isPublic;
    private ArrayList<Skill> skills;
    private ArrayList<Experience> experiences;

    public Talent(String name, String country, String mail, double pricePerHour){
        this.name = name;
        this.country = country;
        this.mail = mail;
        this.pricePerHour = pricePerHour;
        this.isPublic = true;
        this.skills = new ArrayList<>();
        this.experiences = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Método utilizado para editar o mail de um utilizador
     * @param scan
     * @param talents
     */
    public void editMail(Scanner scan, Map<Integer, Talent> talents){
        System.out.println("Insira o email que pretende utilizar!");
        try{
            String mail = scan.next();
            checkIfMailIsValid(mail,talents);
            if(checkIfMailIsValid(mail,talents)) setMail(mail);
            else{
                while(!checkIfMailIsValid(mail,talents)){
                    mail = scan.next();
                    System.out.println("Email inválido!");
                }
                System.out.println("Email atualizado com sucesso!");
            }
        }catch(InputMismatchException e){
            System.out.println("Input inválido!");
        }
    }

    /**
     * Método utilizado para ver se o mail que se pretende usar é válido, ou seja, se cumpre os parâmetros normais de um mail e se ninguém já o está a usar
     * @param mail
     * @param talents
     * @return true se o mail for válido
     */
    public boolean checkIfMailIsValid(String mail,Map<Integer, Talent> talents){
        if(talents.containsValue(mail)) return false;

        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    /**
     * Método utilizado para editar o preço que um talento cobra por hora
     * @param scan
     */
    public void editPricePerHour(Scanner scan){
        try{
            System.out.println("Insira o novo preço por hora que pretende cobrar!");
            double pricePerHour = scan.nextDouble();
            setPricePerHour(pricePerHour);

            System.out.println("Preço por hora atualizado com sucesso!");
        }catch(InputMismatchException e){
            System.out.println("Input inválido!");
        }
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(){
        boolean currentPrivacy = this.isPublic;
        this.isPublic = !currentPrivacy;
    }

    /**
     * Método utilizado para mudar a privacidade do perfil do talento (Privado ou Público)
     */
    public void editPrivacy() {
        if(!this.isPublic){
            setIsPublic();
            System.out.println("O perfil estava privado e foi atualizado para público!");
        }
        else{
            setIsPublic();
            System.out.println("O perfil estava público e foi atualizado para privado!");
        }
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Método utilizado para adicionar uma nova skill que este talento possui
     * @param skill
     */
    public void addSkill(Skill skill){
        skills.add(skill);
        System.out.println("Skill adicionada com sucesso a este talento!");
    }
    public ArrayList<Experience> getExperiences() {
        return experiences;
    }

    /**
     * Método utilizazdo para adicionar uma nova experiência que este talento já passou
     * @param experience
     */
    public void addExperience(Experience experience){
        experiences.add(experience);
        System.out.println("Experiência adicionada com sucesso a este talento");
    }
}
