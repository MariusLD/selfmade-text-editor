package src;
public class Copier extends Commande {
    public Copier(Application application, Editeur editeur) {
        super(application, editeur);
    }
    //sets the clipboard to the selected text
    public void execute() {
        application.setClipboard(editeur.getSelectedText());
        editeur.resetSelection();
    }
}