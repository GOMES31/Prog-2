
import java.util.ArrayList;

public class Notebook {
    private ArrayList<String> notes;
    public Notebook(){
        notes = new ArrayList<>();
    }

    public void addNote(String note){
        notes.add(note);
    }
    public void removeNote(int index){
        try{
            this.notes.remove(index);
            System.out.println("Nota Removida:" + index+1);
        }catch(Exception e){
                System.out.println("Index Inválido");
        }
    }

    public void listNotes(){
        for(String n : notes){
            int index = notes.indexOf(n);
            System.out.println("Nota: " + n + ", Index: " + (index+1));
        }
    }

    public void listNotesW(){
        int i = 0;
        while(i!=notes.size()){
            System.out.println("Nota: " + notes.get(i) + ", Index: " + (i+1));
            i++;
        }
    }



}
