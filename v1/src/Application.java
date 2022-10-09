package src;

public class Application implements Runnable{
    // has a clipboard field
    private String clipboard;

    // has an editeur field
    private Editeur editeur;

    private Fenetre fenetre;

    private String mode;

    // constructor
    public Application() {
        this.editeur = new Editeur();
        this.fenetre = new Fenetre(this);
        this.clipboard = "";
        this.mode = "input";
    }

    // has a method to get the clipboard
    public String getClipboard() {
        return clipboard;
    }

    // has a method to get the editeur
    public Editeur getEditeur() {
        return editeur;
    }

    public Fenetre getFenetre() {
        return fenetre;
    }

    public String getMode() {
        return mode;
    }

    // has a method to set the clipboard
    public void setClipboard(String clipboard) {
        this.clipboard = clipboard;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void run(){
        fenetre.show();
    }
}
