package src;

/***
 * Classe qui gère le déplacement de la sélection.
 */
public class DeplacerSelection extends Commande {
    private char direction;

    /**
     * Constructeur de la classe DeplacerSelection.
     * @param application l'application.
     * @param editeur l'éditeur.
     * @param direction la direction du déplacement.
     */
    public DeplacerSelection(Application application, Editeur editeur, char direction) {
        super(application, editeur);
        this.direction = direction;
    }

    /**
     * Permet d'obtenir la direction du déplacement.
     */
    public char getDirection() {
        return direction;
    }

    /**
     * Déplace la sélection.
     */
    public void execute() {
        editeur.moveSelection(direction);
    }
}
