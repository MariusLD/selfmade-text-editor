package commande;

import main.Application;
import main.Editeur;
import utilitaire.Direction;

/**
 * Commande qui gère le déplacement du curseur.
 */
public class DeplacerCurseur extends Commande {
    private Direction direction;

    /**
     * Commande qui gère le déplacement du curseur.
     * 
     * @param application l'application créant cette commande.
     * @param editeur     l'éditeur sur lequel agir.
     * @param direction   la direction dans laquelle déplacer le curseur.
     */
    public DeplacerCurseur(Application application, Editeur editeur, Direction direction) {
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
     * Déplace le curseur.
     */
    public void execute() {
        editeur.moveCurseur(direction);
    }
}
