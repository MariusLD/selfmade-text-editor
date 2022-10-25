package commande;

import main.Application;
import main.Editeur;
import main.Script;
import utilitaire.Scriptable;
import utilitaire.Direction;
import utilitaire.Sauvegardable;

/**
 * Classe qui gère la suppression.
 */
public class Supprimer_2 extends Supprimer implements Sauvegardable, Scriptable {

    /**
     * Constructeur de la classe Supprimer.
     * 
     * @param application l'application.
     * @param editeur     l'éditeur.
     * @param direction   la direction de la suppression
     */
    public Supprimer_2(Application application, Editeur editeur, Direction direction) {
        super(application, editeur, direction);
        script();
    }

    /**
     * Sauvegarde l'état, puis supprime le caractère avant ou après le curseur,
     * ou supprime le texte sélectionné.
     */
    @Override
    public void execute() {
        super.execute();
        sauvegarde();
    }

    @Override
    public void sauvegarde() {
        application.getMemoire().sauvegarde(editeur.createSnapshot());
    }

    @Override
    public void script() {
        Script s = application.getScript();
        if (s.isRegistering()) {
            s.enregistrer(this);
        }
    }
}
