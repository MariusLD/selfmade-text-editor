package commande;

import main.Application;
import main.Editeur;
import utilitaire.Direction;

/**
 * Commande qui gère la suppression.
 */
public class Supprimer extends Commande {
    private Direction direction;

    /**
     * Commande qui gère la suppression.
     * 
     * @param application l'application créant cette commande.
     * @param editeur     l'éditeur sur lequel agir.
     * @param direction   la direction dans laquelle supprimer.
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
        if (!editeur.emptySelection()) { // si la sélection n'est pas vide
            editeur.removeSelectedText(); // on supprime le texte sélectionné
            editeur.resetSelection();
        } else {
            editeur.deleteChar(direction);
        }
    }
}
