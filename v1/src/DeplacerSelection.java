package src;

public class DeplacerSelection extends Commande {
    private char direction;

    public DeplacerSelection(Application application, Editeur editeur, char direction) {
        super(application, editeur);
        this.direction = direction;
    }

    public void execute() {
        editeur.moveSelection(direction);
    }
}
