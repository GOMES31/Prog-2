public class Professor{
    // tipo protected só permite acesso se pertencer à hierarquia
    protected String nome;
    protected String matricula;
    protected int idade;

    public Professor(String n,String m,int i){
        nome = n;
        matricula = m;
        idade = i;
    }

    public String retornaNome(){
        return nome;
    }

    public String retornaMatricula(){
        return matricula;
    }

    public int retornaIdade(){
        return idade;
    }

    public double retornaVencimento(){
        return 0;
    }
}
