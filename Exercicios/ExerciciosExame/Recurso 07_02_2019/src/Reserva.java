import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private int numero;
    private Date data;
    private List<Grupo> grupos;

    public Reserva(int n,Date d){
        numero = n;
        data = d;
        grupos = new ArrayList<>();
    }

    public int getNumero(){
        return numero;
    }

    public Date getData(){
        return data;
    }

    public List getGrupos(){
        return grupos;
    }


    public void setData(Date d) {
        this.data = d;
    }

    public void acrescentarGrupo(Grupo g) throws ReservaException{
        for(Grupo grupos: grupos){
            if(grupos.getNome().equals(g.getNome())) throw new ReservaException("Já existe esse grupo na reserva");
        }
        grupos.add(g);
    }

    public int getTotalPessoas(){
        int total = 0;
        for(Grupo g: grupos){
            total+= g.getNumPessoas();
        }
        return total;
    }
    
}
