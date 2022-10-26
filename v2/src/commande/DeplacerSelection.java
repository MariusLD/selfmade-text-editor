package commande;

import main.Application;
import main.Editeur;
import utilitaire.Direction;

/**
 * Commande qui gère le déplacement de la sélection.
 */
public class DeplacerSelection extends Commande {
    private Direction direction;

    /**
     * Commande qui gère le déplacement de la sélection.
     * 
     * @param application l'application créant cette commande.
     * @param editeur     l'éditeur sur lequel agir.
     * @param direction   la direction dans laquelle déplacer la sélection.
     */
    public DeplacerSelection(Application application, Editeur editeur, Direction direction) {
        super(application, editeur);
        this.direction = direction;
    }

    /**
     * Permet d'obtenir la direction du déplacement.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Déplace la sélection.
     */
    public void execute() {
        editeur.moveSelection(direction);
    }
}
