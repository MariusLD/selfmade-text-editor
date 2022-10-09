package src;

public class Coller extends Commande {
    public Coller(Application application, Editeur editeur) {
        super(application, editeur);
    }
    //sets the selected text to the clipboard
    public void execute() {
        editeur.setSelectedText(application.getClipboard());
        editeur.resetSelection();
    }
}