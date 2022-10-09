package src;
public class Copier extends Commande {
    public Copier(Application application, Editeur editeur) {
        super(application, editeur);
    }
    //sets the clipboard to the selected text
    @Override
    public void execute() {
        application.resetFuture();
        application.pushPasse(editeur.getMemento());
        application.setClipboard(editeur.getSelectedText());
        editeur.resetSelection();
    }
}