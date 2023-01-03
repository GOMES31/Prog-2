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
     * Método utilizado para remover uma skill de um talento e dar update ao repositório principal
     */

    public Skill removeSkill(Scanner scan){
        Skill removedSkill;
        if(skills == null){
            System.out.println("Este talento não tem skills registadas!");
        }
        else{
            try{
                System.out.println("----- LISTA DE SKILLS -----");
                for(Skill skill: skills){
                    System.out.println(skill.getId() + "\tNome: " + skill.getName());
                }
                System.out.println("Insira o id da skill que pretende remover:");
                try{
                    int id = scan.nextInt();
                    while(id < 0 || !skills.contains(skills.get(id))){
                        id = scan.nextInt();
                        if(id < 0 || !skills.contains(skills.get(id))) System.out.println("Insira um id válido!");
                    }

                    removedSkill = skills.get(id);
                    skills.remove(id);
                    int skillId = 0;
                    for(Skill skill: skills){
                        skill.setId(skillId);
                        skillId++;
                    }
                    System.out.println("Skill removida com sucesso do perfil de talento!");
                    return removedSkill;
                }catch(InputMismatchException e){
                    System.out.println("Input inválido! Insira um número!");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public Skill addNewSkill(Scanner scan){
        Skill addedSkill;
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
            addedSkill = new Skill(name,field,expYears);
            if(skills.contains(addedSkill)){
                System.out.println("Essa skill já existe no repositório de skills do talento!");
                return null;
            }
            skills.add(addedSkill);
            return addedSkill;
        }catch(InputMismatchException e) {
            System.out.println("Input inválido!");
        }
        return null;
    }

    /**
     * Método utilizado para editar o nome de uma skill
     */

    public Skill editSkillName(Scanner scan){
        Skill editedSkill;
        System.out.println("----- LISTA DE SKILLS -----");
        for(Skill skill: skills){
            System.out.println(skill.getId() + "\tNome: " + skill.getName());
        }
        System.out.println("Insira o id da skill que pretende editar o nome:");
        try{
            int id;
            while (true) {
                try {
                    id = scan.nextInt();
                    if (id < 0 || !checkIfSkillExists(id)) {
                        System.out.println("Insira um id válido!");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido!");
                    scan.nextDouble();
                }
            }
            System.out.println("Insira o novo nome para a skill:");
            String name = scan.next();
            skills.get(id).setName(name);

            editedSkill = skills.get(id);
            return editedSkill;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkIfSkillExists(int id){
        for(Skill skill: skills){
            if(skill.getId() == id){
                return true;
            }
        }
        return false;
    }
    /**
     * Método utilizado para editar os anos de experiência de uma skill
     */

    public Skill editSkillExpYears(Scanner scan){
        Skill editedSkill;
        System.out.println("----- LISTA DE SKILLS -----");
        for(Skill skill: skills){
            System.out.println(skill.getId() + "\tNome: " + skill.getName() + ",\tAnos Experiência: " +  skill.getExpYears());
        }
        System.out.println("Insira o id da skill que pretende editar os anos de experiência:");
        try{
            int id;
            while (true) {
                try {
                    id = scan.nextInt();
                    if (id < 0 || !checkIfSkillExists(id)) {
                        System.out.println("Insira um id válido!");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido!");
                    scan.nextDouble();
                }
            }
            System.out.println("Insira o número de anos de experiência: ");
            double expYears;
            while (true) {
                try {
                    expYears = scan.nextDouble();
                    if(expYears < skills.get(id).getExpYears() || expYears < 0){
                        System.out.println("O número de anos de experiência novo não pode ser inferior ao número de anos de experiência registado! Nº anos: " + skills.get(id).getExpYears());
                    }else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido!");
                    scan.nextDouble();

                }
            }
            skills.get(id).setExpYears(expYears);

            editedSkill = skills.get(id);
            return editedSkill;
        }catch(Exception e){
            System.out.println("Input inválido! Insira um número!");
        }
        return null;
    }

    public void editPricePerHour(Scanner scan){
        while (true) {
            try {
                pricePerHour = scan.nextDouble();
                if (pricePerHour < 0) {
                    System.out.println("Insira um valor superior a 0!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input inválido! Insira novamente o preço por hora:");
                scan.nextDouble();
            }
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
            if(!checkIfMailIsValid(mail,talents)){
                while(!checkIfMailIsValid(mail,talents)){
                    mail = scan.next();
                    System.out.println("Email inválido!");
                }
            }
            else{
                setMail(mail);
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
        for(Talent _talent: talents.values()){
            if(_talent.getMail().equals(mail)){
                return false;
            }
        }
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

}
