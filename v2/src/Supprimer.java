package src;

public class Supprimer extends Commande {
    private char position;

    public Supprimer(Application application, Editeur editeur, char position) {
        super(application, editeur);
        this.position = position;
    }

    @Override
    public void execute() {
        application.resetFuture();
        application.pushPasse(editeur.getMemento());
        if(!editeur.emptySelection()){
            editeur.removeSelectedText();
            editeur.resetSelection();
        }
        else{
            editeur.deleteChar(position);
        }
    }
}
