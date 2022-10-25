package commande;

import main.Application;
import main.Editeur;
import utilitaire.Direction;

/**
 * Classe qui gère le déplacement du curseur.
 */
public class DeplacerCurseur extends Commande {
    private Direction direction;

    /**
     * Constructeur de la classe DeplacerCurseur.
     * 
     * @param application l'application.
     * @param editeur     l'éditeur.
     * @param direction   la direction du déplacement.
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
