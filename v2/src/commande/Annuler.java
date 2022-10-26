package commande;

import main.Application;
import main.Editeur;
import main.Script;
import main.Snapshot;
import utilitaire.Scriptable;

/**
 * Commande qui gère l'annulation d'une action et implémente
 * l'interface Scriptable.
 */
public class Annuler extends Commande implements Scriptable {

    /**
     * Commande qui gère l'annulation d'une action et implémente
     * l'interface Scriptable.
     * 
     * @param application l'application créant cette commande.
     * @param editeur     l'éditeur sur lequel agir.
     */
    public Annuler(Application application, Editeur editeur) {
        super(application, editeur);
        script();
    }

    /**
     * Remet l'editeur dans son état sauvegardé précédemment.
     */
    @Override
    public void execute() {
        editeur.resetSelection();
        Snapshot snap = application.getMemoire().retourPasse();
        if (snap != null) {
            editeur.restoreSnapshot(snap);
        }
    }

    @Override
    public void script() {
        Script s = application.getScript();
        if (s.isRegistering()) {
            s.enregistrer(this);
        }
    }
}
