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


    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
        System.out.println("Preço por hora atualizado com sucesso!");
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

    /**
     * Método que imprime um sub menu de gestão das skills de um talento
     * @param scan
     */
    public void manageSkills(Scanner scan){
        System.out.println("----- MANAGE SKILLS -----\n1 - Apagar Skill\2 - Adicionar Skill\n3 - Editar nome de uma skill\4 - Editar anos de experiência de uma skill!");
        try{
            int option = scan.nextInt();
            while(option < 1 || option > 4){
                option = scan.nextInt();
                if(option < 1 || option > 4) System.out.println("Opção Inválida!\n1 - Apagar Skill\2 - Adicionar Skill\n3 - Editar nome de uma skill\4 - Editar anos de experiência de uma skill!");
            }
            switch(option){
                case 1:
                    removeSkill();
                    break;
                case 2:
                    addNewSkill(scan);
                    break;
                case 3:
                    editSkillName(scan);
                    break;
                case 4:
                    editSkillExpYears(scan);
                    break;
            }
        }catch(InputMismatchException e){
            System.out.println("Input inválido! Insira um número!");
        }
    }

    /**
     * Método utilizado para remover uma skill de um talento
     */

    public void removeSkill(){
        if(skills == null) System.out.println("Este talento não tem skills registadas!");
    }

    /**
     * Método utilizado para obter os detalhes de uma skill nova a adicionar ao talento
     */

    public void addNewSkill(Scanner scan){
        try{
            System.out.println("Nome da skill:");
            String name = scan.next();

            System.out.println("Área da skill:");
            String field = scan.next();

            System.out.println("Número de anos de experiência com essa skill:");
            double expYears = scan.nextDouble();
            while(expYears < 0){
                expYears = scan.nextDouble();
                if(expYears < 0) System.out.println("Insira um valor superior a 0!");
            }
            skills.add(new Skill(name,field,expYears));
        }catch(InputMismatchException e) {
            System.out.println("Input inválido!");
        }
    }

    /**
     * Método utilizado para editar o nome de uma skill
     */

    public void editSkillName(Scanner scan){
        System.out.println("----- LISTA DE SKILLS -----");
        for(Skill skill: skills){
            System.out.println(skill.getId() + "\tNome: " + skill.getName());
        }
        System.out.println("Insira o id da skill que pretende editar o nome:");
        try{
            int id = scan.nextInt();
            while(id < 0 || !skills.contains(id)){
                id = scan.nextInt();
                if(id < 0 || !skills.contains(id)) System.out.println("Insira um id válido!");
            }
            System.out.println("Insira o novo nome para a skill:");
            String name = scan.next();
            skills.get(id).setName(name);
        }catch(InputMismatchException e){
            System.out.println("Input inválido! Insira um número!");
        }
    }

    /**
     * Método utilizado para editar os anos de experiência de uma skill
     */

    public void editSkillExpYears(Scanner scan){
        System.out.println("----- LISTA DE SKILLS -----");
        for(Skill skill: skills){
            System.out.println(skill.getId() + "\tNome: " + skill.getName() + ",\tAnos Experiência: " +  skill.getExpYears());
        }
        System.out.println("Insira o id da skill que pretende editar os anos de experiência:");
        try{
            int id = scan.nextInt();
            while(id < 0 || !skills.contains(id)){
                id = scan.nextInt();
                if(id < 0 || !skills.contains(id)) System.out.println("Insira um id válido!");
            }
            System.out.println("Insira o número de anos de experiência: ");
            double expYears = scan.nextDouble();
            while(expYears < skills.get(id).getExpYears() || expYears < 0){
                expYears = scan.nextDouble();
                if(expYears < skills.get(id).getExpYears() || expYears < 0){
                    System.out.println("O número de anos de experiência novo não pode ser inferior ao número de anos de experiência registado! Nº anos: " + skills.get(id).getExpYears());
                }
            }
            skills.get(id).setExpYears(expYears);
        }catch(InputMismatchException e){
            System.out.println("Input inválido! Insira um número!");
        }
    }

    /**
     * Método que imprime um sub menu de gestão das experiências de um talento
     * @param scan
     */
    public void manageExperiences(Scanner scan){
        System.out.println("----- MANAGE SKILLS -----\n1 - Apagar Experiência\2 - Adicionar Skill\n3 - Editar nome de uma skill\4 - Editar anos de experiência de uma skill");
        try{
            int option = scan.nextInt();
            while(option < 1 || option > 3){
                option = scan.nextInt();
                if(option < 1 || option > 3) System.out.println("Opção Inválida!\n1 - Apagar Experiência\2 - Adicionar nova experiência\n3 - Editar nome de experiência");
            }
            switch(option){
                case 1:
                    removeExperience();
                    break;
                case 2:
                    addNewExperience(scan);
                    break;
                case 3:
                    editExperienceTitle(scan);
                    break;
            }
        }catch(InputMismatchException e){
            System.out.println("Input inválido! Insira um número!");
        }
    }

    /**
     * Método utilizado para editar o preço que um talento cobra por hora
     * @param scan
     */
    public void editPricePerHour(Scanner scan){
        try{
            System.out.println("Insira o novo preço por hora que pretende cobrar:");
            double pricePerHour = scan.nextDouble();
            while(pricePerHour < 0){
                pricePerHour = scan.nextDouble();
                if(pricePerHour < 0) System.out.println("Insira um valor superior a 0!");
            }
            setPricePerHour(pricePerHour);
        }catch(InputMismatchException e){
            System.out.println("Input inválido!");
        }
    }

    /**
     * Método utilizado para editar o mail de um utilizador
     *
     * @param scan
     * @param talents
     */
    public void editMail(Scanner scan, Map<Integer, Talent> talents){
        System.out.println("Insira o email que pretende utilizar:");
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
    public boolean checkIfMailIsValid(String mail, Map<Integer, Talent> talents){
        if(talents.containsValue(mail)) return false;

        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

}
