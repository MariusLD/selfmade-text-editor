package commande;

import main.Application;
import main.Editeur;
import utilitaire.Direction;

/**
 * Classe qui gère la suppression.
 */
public class Supprimer extends Commande {
    private Direction direction;

    /**
     * Constructeur de la classe Supprimer.
     * 
     * @param application l'application.
     * @param editeur     l'éditeur.
     * @param direction   la direction de la suppression.
     */
    public Supprimer(Application application, Editeur editeur, Direction direction) {
        super(application, editeur);
        this.direction = direction;
    }

    /**
     * Permet d'obtenir la direction de suppression.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Supprime le caractère avant ou après le curseur,
     * ou supprime le texte sélectionné.
     */
    @Override
    public void execute() {
        if (!editeur.emptySelection()) {
            editeur.removeSelectedText();
            editeur.resetSelection();
        } else {
            editeur.deleteChar(direction);
        }
    }
}
