package commande;

import main.Application;
import main.Editeur;
import main.Script;
import utilitaire.Scriptable;
import utilitaire.Sauvegardable;

/**
 * Commande qui gère l'écriture et implémente les interfaces
 * Scriptable et Sauvegardable.
 */
public class Ecrire_2 extends Ecrire implements Sauvegardable, Scriptable {

    /**
     * Commande qui gère l'écriture et implémente les interfaces
     * Scriptable et Sauvegardable.
     * 
     * @param application l'application créant cette commande.
     * @param editeur     l'éditeur sur lequel agir.
     * @param c           le caractère à écrire.
     */
    public Ecrire_2(Application application, Editeur editeur, char c) {
        super(application, editeur, c);
        script();
    }

    /**
     * Ecrit le caractère, au niveau du curseur,
     * ou remplace le texte sélectionné par le caractère,
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
