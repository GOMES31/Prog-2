public class Idosos extends Grupo{
    private int num_idosos;
    private float preco_idosos;

    public Idosos(String n, int num_pessoas, float preco_pessoa,int num_idosos,float preco_idosos){
        super(n,num_pessoas,preco_pessoa);
        this.num_idosos = num_idosos;
        this.preco_idosos = preco_idosos;
    }

    public int getNumIdosos(){
        return num_idosos;
    }
    public float getPrecoIdosos(){
        return preco_idosos;
    }

    public void setNumIdosos(int n){
        this.num_idosos = n;
    }

    public void setPrecoIdosos(float p){
        this.preco_idosos = p;
    }

    @Override
    public float getPrecoTotal(){
        return (getNumPessoas()-getNumIdosos())*getPrecoPessoa() + getNumIdosos()*getPrecoIdosos();
    }
}
