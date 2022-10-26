package commande;

import main.Application;
import main.Editeur;
import main.Script;
import utilitaire.Scriptable;
import utilitaire.Sauvegardable;

/**
 * Commande qui gère le collage et implémente les interfaces
 * Scriptable et Sauvegardable.
 */
public class Coller_2 extends Coller implements Sauvegardable, Scriptable {

    /**
     * Commande qui gère le collage et implémente les interfaces
     * Scriptable et Sauvegardable.
     * 
     * @param application l'application créant cette commande.
     * @param editeur     l'éditeur sur lequel agir.
     */
    public Coller_2(Application application, Editeur editeur) {
        super(application, editeur);
        script();
    }

    /**
     * Remplace le texte sélectionné par le contenu du presse-papier,
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