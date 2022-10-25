package commande;

import main.Application;
import main.Editeur;
import utilitaire.Direction;

/***
 * Classe qui gère le déplacement de la sélection.
 */
public class DeplacerSelection extends Commande {
    private Direction direction;

    /**
     * Constructeur de la classe DeplacerSelection.
     * 
     * @param application l'application.
     * @param editeur     l'éditeur.
     * @param direction   la direction du déplacement.
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
