import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public void removeSkill(Scanner scan){
        if(skills.isEmpty()){
            System.out.println("Este talento não tem skills registadas!");
            return;
        }
            System.out.println("----- LISTA DE SKILLS -----");
            for (Skill skill : skills) {
                System.out.println("Id: " + skill.getId() + "\tNome: " + skill.getName());
            }
            System.out.println("Insira o id da skill que pretende remover:");
            int id;
            while (true) {
                try {
                    id = scan.nextInt();
                    try {
                        if (id < 0 || !checkIfSkillExists(id)) {
                            System.out.println("Insira um id válido!");
                            scan.next();
                        } else {
                            break;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Insira um id válido!");
                        scan.next();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido!");
                    scan.next();
                }
            }

            Skill removed = new Skill(skills.get(id).getName(),skills.get(id).getField());
            skills.remove(skills.get(id));

            int skillId = 0;
            for (Skill skill : skills) {
                skill.setId(skillId);
                skillId++;
            }
            System.out.println("Skill removida com sucesso do perfil de talento!");

            Repository.removeSkill(removed.getName());
    }

    public void addNewSkill(Scanner scan){
            System.out.println("Nome da skill:");
            String name1st = scan.next();
            String name2nd = scan.nextLine();

            String name = String.join(name1st,"\t",name2nd);
            while(checkIfNameIsInUse(name)){
                System.out.println("Já existe uma skill com esse nome. Insira outro!");
                name1st = scan.next();
                name2nd = scan.nextLine();
                name = String.join(name1st,"\t",name2nd);
            }

            System.out.println("Área da skill:");
            String field1st = scan.next();
            String field2nd = scan.nextLine();

            String field = String.join(field1st,"\t",field2nd);

            System.out.println("Número de anos de experiência com essa skill:");
            double expYears;
            while (true) {
                try{
                    expYears = scan.nextDouble();
                    if (expYears < 0) {
                        System.out.println("Insira um valor superior a 0!");
                        scan.next();
                    }else {
                        break;
                    }
                }catch(InputMismatchException e) {
                    System.out.println("Input inválido! Insira novamente o número de anos de experiência:");
                    scan.next();
                }
            }

            skills.add(new Skill(name,field,expYears));

            int skillId = 0;
                for(Skill skill: skills){
                    skill.setId(skillId);
                    skillId++;
                }

        Repository.addSkill(new Skill(name,field));
    }

    /**
     * Método utilizado para editar o nome de uma skill
     */

    public void editSkillName(Scanner scan){
        if(skills.isEmpty()){
            System.out.println("Não existem skills registadas!");
            return;
        }
        System.out.println("----- LISTA DE SKILLS -----");
        for(Skill skill: skills){
            System.out.println("Id: " + skill.getId() + "\tNome: " + skill.getName());
        }
        System.out.println("Insira o id da skill que pretende editar o nome:");

            int id;
            while (true) {
                try {
                    id = scan.nextInt();
                    try{
                        if (id < 0 || !checkIfSkillExists(id)) {
                            System.out.println("Insira um id válido!");
                            scan.next();
                        } else {
                            break;
                        }
                    }catch(IndexOutOfBoundsException e){
                        System.out.println("Insira um id válido!");
                        scan.next();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido!");
                    scan.next();
                }
            }
            String oldName = skills.get(id).getName();

        System.out.println("Insira o novo nome para a skill:");
            String name1st = scan.next();
            String name2nd = scan.nextLine();

            String name = String.join(name1st,"\t",name2nd);
            while(checkIfNameIsInUse(name)){
                System.out.println("Já existe uma skill com esse nome. Insira outro!");
                name1st = scan.next();
                name2nd = scan.nextLine();
                name = String.join(name1st,"\t",name2nd);
            }

            skills.get(id).setName(name);
            System.out.println("Nome atualizado com sucesso!");
            String newName = name;

        Repository.editSkillName(oldName,newName);
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

    public void editSkillExpYears(Scanner scan){
        if(skills.isEmpty()){
            System.out.println("Não existem skills registadas!");
            return;
        }
        System.out.println("----- LISTA DE SKILLS -----");
        for(Skill skill: skills){
            System.out.println("Id: " + skill.getId() + "\tNome: " + skill.getName() + "\tAnos Experiência: " +  skill.getExpYears());
        }

            System.out.println("Insira o id da skill que pretende editar os anos de experiência:");
            int id;
            while (true) {
                try {
                    id = scan.nextInt();
                    try{
                        if (id < 0 || !checkIfSkillExists(id)) {
                            System.out.println("Insira um id válido!");
                            scan.nextInt();
                        } else {
                            break;
                        }
                    }catch(IndexOutOfBoundsException e){
                        System.out.println("Insira um id válido!");
                        scan.next();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido!");
                    scan.next();
                }
            }
            System.out.println("Insira o número de anos de experiência: ");
            double expYears;
            while (true) {
                try {
                    expYears = scan.nextDouble();
                    if(expYears < skills.get(id).getExpYears() || expYears < 0){
                        System.out.println("O número de anos de experiência novo não pode ser inferior ao número de anos de experiência registado! Nº anos: " + skills.get(id).getExpYears());
                        scan.next();
                    }else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido!");
                    scan.next();

                }
            }
            skills.get(id).setExpYears(expYears);
    }

    public boolean checkIfNameIsInUse(String name){
        for(Skill skill: skills){
            if(skill.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public void editPricePerHour(Scanner scan){
        System.out.println("Insira o novo valor para o preço por hora que o talento cobra!");
        double pricePerHour;
        while (true) {
            try {
                pricePerHour = scan.nextDouble();
                if (pricePerHour < 0) {
                    System.out.println("Insira um valor superior a 0!");
                    scan.next();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input inválido! Insira novamente o preço por hora:");
                scan.next();
            }
        }

        setPricePerHour(pricePerHour);
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
                    System.out.println("Email inválido!");
                    mail = scan.next();
                }
            }
            else{
                setMail(mail.toLowerCase());
            }
        }catch(InputMismatchException e){
            System.out.println("Input inválido!");
        }
    }

    public void removeExperience(Scanner scan){
        if(experiences.isEmpty()){
            System.out.println("Este talento não tem experiências registadas!");
        }
        else{
                System.out.println("----- LISTA DE EXPERIÊNCIAS -----");
                for(Experience experience: experiences){
                    System.out.println("Id: " + experience.getId() + "\tNome: " + experience.getTitle() + "\tEmpresa: "+ experience.getEnterprise() + "\tData Inicio: " + experience.getStartDate() + "\tData Fim: " + experience.getEndDate());
                }
                System.out.println("Insira o id da experiência que pretende remover:");
                int id;
                while (true) {
                    id = scan.nextInt();
                    try {
                        try{
                            if (id < 0 || !checkIfExperienceExists(id)) {
                                System.out.println("Insira um id válido!");
                                scan.next();
                            } else {
                                break;
                            }
                        }catch(IndexOutOfBoundsException e){
                            scan.next();
                            System.out.println("Insira um id válido!");
                        }
                    } catch (InputMismatchException e) {
                        scan.next();
                        System.out.println("Input inválido!");
                    }
                }
                    Experience removedExperience = experiences.get(id);
                    experiences.remove(removedExperience);
                    int expId = 0;
                    for(Experience experience: experiences){
                        experience.setId(expId);
                        expId++;
                    }
                    System.out.println("Experiência removida com sucesso do perfil de talento!");
        }
    }

    public void addNewExperience(Scanner scan){
        // Inicializa um DateTimeFormatter para depois formatar um LocalDate em "dd/MM/yyyy"
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("Insira o título da experiência:");
            String title1st = scan.next();
            String title2nd = scan.nextLine();

            String title = String.join(title1st,"\t",title2nd);

            while(checkIfTitleIsInUse(title)) {
                System.out.println("Já existe uma experiência com esse título. Escolha outro!");
                title1st = scan.next();
                title2nd = scan.nextLine();
                title = String.join(title1st,"\t",title2nd);
            }

            System.out.println("Insira a empresa da experiência:");
            String enterprise1st = scan.next();
            String enterprise2nd = scan.nextLine();

            String enterprise = String.join(enterprise1st,"\t",enterprise2nd);

           LocalDate startDate = verifyStartDate(dateFormatter,scan);
           LocalDate endDate = verifyEndDate(dateFormatter,scan);


            if(endDate.isAfter(startDate) && !checkOverlap(new Experience(title,enterprise,startDate,endDate))){
                addExperience(new Experience(title,enterprise,startDate,endDate));
                return;
            }

            while (!endDate.isAfter(startDate) || checkOverlap(new Experience(title, enterprise, startDate, endDate))) {
                if (!endDate.isAfter(startDate)) {
                    System.out.println("A data de fim da experiência deve ser posterior à data de início.");
                    endDate = verifyEndDate(dateFormatter, scan);
                } else {
                    System.out.println("As datas coincidem com outra experiência. Escolha novas datas.");
                    startDate = verifyStartDate(dateFormatter, scan);
                    endDate = verifyEndDate(dateFormatter, scan);

                }
            }

            addExperience(new Experience(title, enterprise, startDate, endDate));


    }

    public LocalDate verifyStartDate(DateTimeFormatter dateFormatter,Scanner scan){
        System.out.println("Insira a data de início da experiência:");
        String startDateInput = scan.next();

        LocalDate startDate;
        while(true){
            try{
                startDate = LocalDate.parse(startDateInput,dateFormatter);
                break;
            }catch(DateTimeParseException e){
                System.out.println("Data de início inválida! Por favor, insira uma data no formato dd/MM/yyyy.");
                startDateInput = scan.next();
            }
        }

        return startDate;
    }

    public LocalDate verifyEndDate(DateTimeFormatter dateFormatter,Scanner scan){

        System.out.println("Insira a data de fim da experiência ou a data atual se que ainda estiver a trabalhar na empresa (depois pode alterar):");
        String endDateInput = scan.next();

        LocalDate endDate;

        while(true){
            try{
                endDate = LocalDate.parse(endDateInput,dateFormatter);
                break;
            }catch(DateTimeParseException e){
                System.out.println("Data de fim inválida! Por favor, insira uma data no formato dd/MM/yyyy.");
                endDateInput = scan.next();
            }
        }

        return endDate;
    }

    public boolean checkOverlap(Experience experience) {
        for (Experience _experience : experiences) {
            if(experience.getStartDate().isBefore(_experience.getEndDate()) && experience.getEndDate().isAfter(_experience.getStartDate())) {
                return true;
            }
        }
        return false;
    }

    public void editExpTitle(Scanner scan){
        System.out.println("----- LISTA DE EXPERIÊNCIAS -----");
        for(Experience experience: experiences){
            System.out.println("Id: " + experience.getId() + "\tNome: " + experience.getTitle() + "\tEmpresa: " + experience.getEnterprise());
        }
        System.out.println("Insira o id da experiência que pretende editar o título:");
            int id;
            while (true) {
                try {
                    id = scan.nextInt();
                    try{
                        if (id < 0 || !checkIfExperienceExists(id)) {
                            System.out.println("Insira um id válido!");
                            scan.next();
                        } else {
                            break;
                        }
                    }catch(IndexOutOfBoundsException e){
                        System.out.println("Insira um id válido!");
                        scan.next();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido!");
                    scan.next();
                }
            }
            System.out.println("Insira o novo título para a experiência:");
            String title1st = scan.next();
            String title2nd = scan.nextLine();

            String title = String.join(title1st,"\t",title2nd);

            while(checkIfTitleIsInUse(title)) {
                System.out.println("Já existe uma experiência com esse título. Escolha outro!");
                title1st = scan.next();
                title2nd = scan.nextLine();
                title = String.join(title1st,"\t",title2nd);
            }

            experiences.get(id).setTitle(title);

    }

    public void editExpEndDate(Scanner scan){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("----- LISTA DE EXPERIÊNCIAS -----");
        for(Experience experience: experiences){
            System.out.println("Id: " + experience.getId() + "\tNome: " + experience.getTitle() + "\tEmpresa: " + experience.getEnterprise());
        }
        System.out.println("Insira o id da experiência que pretende editar o título:");
            int id;
            while (true) {
                try {
                    id = scan.nextInt();
                    try{
                        if (id < 0 || !checkIfExperienceExists(id)) {
                            System.out.println("Insira um id válido!");
                            scan.next();
                        } else {
                            break;
                        }
                    }catch(IndexOutOfBoundsException e){
                        System.out.println("Insira um id válido!");
                        scan.next();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input inválido!");
                    scan.next();
                }
            }
                LocalDate startDate = experiences.get(id).getStartDate();
                System.out.println("Insira a nova data de fim da experiência:");
                String endDateInput = scan.next();

                LocalDate endDate;

                while (true) {
                    try {
                        endDate = LocalDate.parse(endDateInput, dateFormatter);
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.println("Data de fim inválida! Por favor, insira uma data no formato dd/MM/yyyy.");
                        endDateInput = scan.next();
                    }
                }

                if (endDate.isAfter(startDate)) {
                    experiences.get(id).setEndDate(endDate);
                } else {
                    System.out.println("A data de fim da experiência deve ser posterior à data de início!");
                    while (!endDate.isAfter(startDate)) {
                        endDateInput = scan.next();
                        while (true) {
                            try {
                                endDate = LocalDate.parse(endDateInput, dateFormatter);
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("Data de fim inválida! Por favor, insira uma data no formato dd/MM/yyyy.");
                                endDateInput = scan.next();
                            }
                        }
                        if (!endDate.isAfter(startDate))
                            System.out.println("A data de fim da experiência deve ser posterior à data de início!");
                    }
                }
    }
    public boolean checkIfExperienceExists(int id){
        for(Experience experience: experiences){
            if(experience.getId() == id){
                return true;
            }
        }
        return false;
    }
    public boolean checkIfTitleIsInUse(String title){
        for(Experience experience: experiences){
            if(experience.getTitle().equalsIgnoreCase(title)){
                return true;
            }
        }
        return false;
    }
    /**
     * Método utilizado para ver se o mail que se pretende usar é válido, ou seja, se cumpre os parâmetros normais de um mail e se ninguém já o está a usar
     * @param mail
     * @param talents
     * @return true se o mail for válido
     */
    public boolean checkIfMailIsValid(String mail, Map<Integer, Talent> talents){
        for(Talent _talent: talents.values()){
            if(_talent.getMail().equalsIgnoreCase(mail)){
                System.out.println("O mail já se encontra em uso!");
                return false;
            }
        }
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

}
