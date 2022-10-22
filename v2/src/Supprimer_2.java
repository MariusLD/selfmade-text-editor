package src;

/**
 * Classe qui gère la suppression.
 */
public class Supprimer_2 extends Commande {
    private Direction direction;

    /**
     * Constructeur de la classe Supprimer.
     * @param application l'application.
     * @param editeur l'éditeur.
     * @param direction la direction de la suppression ('a' pour after, 'b' pour before).
     */
    public Supprimer_2(Application application, Editeur editeur, Direction direction) {
        super(application, editeur);
        this.direction = direction;
    }

    /**
     * Sauvegarde l'état, puis supprime le caractère avant ou après le curseur,
     * ou supprime le texte sélectionné.
     */
    @Override
    public void execute() {
        application.resetFuture();
        application.pushPasse(editeur.getMemento());
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
    public Direction getDirection() {
        return direction;
    }
}
