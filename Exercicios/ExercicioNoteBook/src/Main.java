public class Main {
    public static void main(String[] args) {
        Notebook notebook = new Notebook();
        notebook.addNote("20");
        notebook.addNote("18");
        notebook.addNote("9");
        notebook.addNote("12");
        notebook.addNote("15");

        notebook.listNotesW();

        notebook.removeNote(-1);
        notebook.removeNote(2);
        notebook.removeNote(5);

        notebook.listNotes();

    }
}