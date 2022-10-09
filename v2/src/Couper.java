package src;
public class Couper extends Commande {
    public Couper(Application application, Editeur editeur) {
        super(application, editeur);
    }
    //sets the clipboard to the selected text
    // removes the selected text
    @Override
    public void execute() {
        application.resetFuture();
        application.pushPasse(editeur.getMemento());
        application.setClipboard(editeur.getSelectedText());
        editeur.removeSelectedText();
        editeur.resetSelection();
    }
}