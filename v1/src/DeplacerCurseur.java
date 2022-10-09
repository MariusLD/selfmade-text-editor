package src;

public class DeplacerCurseur extends Commande {
    private char direction;

    public DeplacerCurseur(Application application, Editeur editeur, char direction) {
        super(application, editeur);
        this.direction = direction;
    }

    public void execute() {
        editeur.moveCurseur(direction);
    }
    
}
