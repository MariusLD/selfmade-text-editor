package commande;

import main.Application;
import main.Editeur;
import main.Script;
import utilitaire.Scriptable;
import utilitaire.Direction;
import utilitaire.Sauvegardable;

/**
 * Commande qui gère la suppression et implémente les interfaces
 * Scriptable et Sauvegardable.
 */
public class Supprimer_2 extends Supprimer implements Sauvegardable, Scriptable {

    /**
     * Commande qui gère la suppression et implémente les interfaces
     * Scriptable et Sauvegardable.
     * 
     * @param application l'application créant cette commande.
     * @param editeur     l'éditeur sur lequel agir.
     * @param direction   la direction dans laquelle supprimer.
     */
    public Supprimer_2(Application application, Editeur editeur, Direction direction) {
        super(application, editeur, direction);
        script();
    }

    /**
     * Supprime le caractère avant ou après le curseur,
     * ou supprime le texte sélectionné,
     * puis sauvegarde l'état de l'éditeur.
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
