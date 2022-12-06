public abstract class Grupo {
    private String nome;
    private int num_pessoas;
    private float preco_pessoa;

    public Grupo(String n, int num, float p){
        nome = n;
        num = num_pessoas;
        preco_pessoa = p;
    }

    public String getNome(){
        return nome;
    }

    public int getNumPessoas(){
        return num_pessoas;
    }

    public float getPrecoPessoa(){
        return preco_pessoa;
    }

    public float getPrecoTotal(){
        return 0;
    }

}
