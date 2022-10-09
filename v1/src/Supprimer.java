package src;

/**
 * Classe qui gère la suppression.
 */
public class Supprimer extends Commande {
    private char direction;

    /**
     * Constructeur de la classe Supprimer.
     * @param application l'application.
     * @param editeur l'éditeur.
     * @param direction la direction de la suppression ('a' pour after, 'b' pour before).
     */
    public Supprimer(Application application, Editeur editeur, char direction) {
        super(application, editeur);
        this.direction = direction;
    }

    /**
     * Supprime le caractère avant ou après le curseur,
     * ou supprime le texte sélectionné.
     */
    @Override
    public void execute() {
        if(!editeur.emptySelection()){
            editeur.removeSelectedText();
            editeur.resetSelection();
        }
        else{
            editeur.deleteChar(direction);
        }
    }

    /**
     * Permet d'obtenir la direction de suppression.
     */
    public char getDirection() {
        return direction;
    }
}
