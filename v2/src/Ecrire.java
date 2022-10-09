package src;

public class Ecrire extends Commande {
    private char c;

    public Ecrire(Application application, Editeur editeur, char c) {
        super(application, editeur);
        this.c = c;
    }

    @Override
    public void execute() {
        application.resetFuture();
        if(application.pasDePasse() || Character.isWhitespace(c)){
            application.pushPasse(editeur.getMemento());
        }
        if(!editeur.emptySelection()){
            editeur.removeSelectedText();
            editeur.resetSelection();
        }
        editeur.writeChar(c);
    }
}
