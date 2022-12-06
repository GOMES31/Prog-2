public class Familia extends Grupo{
    private int num_menores;
    private int num_bebes;
    private float preco_menor;

    public Familia(String n, int num_pessoas, float preco_pessoa,int num_menores,int num_bebes,float preco_menor){
        super(n,num_pessoas,preco_pessoa);
        this.num_menores = num_menores;
        this.num_bebes = num_bebes;
        this.preco_menor = preco_menor;
    }

    public int getNumMenores(){
        return num_menores;
    }
    public int getNumBebes(){
        return num_bebes;
    }
    public float getPrecoMenor(){
        return preco_menor;
    }

    public void setNumMenores(int n){
        this.num_menores = n;
    }
    public void setNumBebes(int n){
        this.num_bebes = n;

    }public void setPrecoMenores(float p){
        this.preco_menor = p;
    }

    @Override
    public float getPrecoTotal(){
        return (getNumPessoas()-getNumMenores())*getPrecoPessoa() + getNumMenores()*getPrecoMenor();
    }


}
