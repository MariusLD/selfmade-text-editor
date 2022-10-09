package src;
public class Coller extends Commande {
    public Coller(Application application, Editeur editeur) {
        super(application, editeur);
    }
    //sets the selected text to the clipboard
    @Override
    public void execute() {
        application.resetFuture();
        application.pushPasse(editeur.getMemento());
        editeur.setSelectedText(application.getClipboard());
        editeur.resetSelection();
    }
}