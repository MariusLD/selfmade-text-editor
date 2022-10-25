package commande;

import main.Application;
import main.Editeur;
import main.Script;
import main.Snapshot;
import utilitaire.Scriptable;

/**
 * Classe qui gère l'annulation d'une action.
 */
public class Annuler extends Commande implements Scriptable {

    /**
     * Constructeur de la classe Annuler.
     * 
     * @param application L'application.
     * @param editeur     L'éditeur.
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
