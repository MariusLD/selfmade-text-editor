package src;

public class Ecrire extends Commande {
    private char c;

    public Ecrire(Application application, Editeur editeur, char c) {
        super(application, editeur);
        this.c = c;
    }

    public void execute() {
        if(!editeur.emptySelection()){
            editeur.removeSelectedText();
            editeur.resetSelection();
        }
        editeur.writeChar(c);
    }
}
