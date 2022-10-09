package src;

/**
 * Classe qui gère le déplacement du curseur.
 */
public class DeplacerCurseur extends Commande {
    private char direction;

    /**
     * Constructeur de la classe DeplacerCurseur.
     * @param application l'application.
     * @param editeur l'éditeur.
     * @param direction la direction du déplacement.
     */
    public DeplacerCurseur(Application application, Editeur editeur, char direction) {
        super(application, editeur);
        this.direction = direction;
    }

    /**
     * Déplace le curseur.
     */
    public void execute() {
        editeur.moveCurseur(direction);
    }

    /**
     * Permet d'obtenir la direction du déplacement.
     */
    public char getDirection() {
        return direction;
    }
}
