package commande;

import main.Application;
import main.Editeur;
import main.Script;
import utilitaire.Scriptable;
import main.Snapshot;

/**
 * Commande qui gère le rétablissement d'une action et implémente
 * l'interface Scriptable.
 */
public class Retablir extends Commande implements Scriptable {

    /**
     * Commande qui gère le rétablissement d'une action et implémente
     * l'interface Scriptable.
     * 
     * @param application l'application créant cette commande.
     * @param editeur     l'éditeur sur lequel agir.
     */
    public Retablir(Application application, Editeur editeur) {
        super(application, editeur);
        script();
    }

    /**
     * Rétabli l'état de l'éditeur ayant été annulé précédemment.
     */
    @Override
    public void execute() {
        editeur.resetSelection();
        Snapshot snap = application.getMemoire().retourFuture();
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
