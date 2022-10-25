package commande;

import main.Application;
import main.Editeur;
import main.Script;
import utilitaire.Scriptable;
import utilitaire.Sauvegardable;

/**
 * Classe qui gère l'écriture.
 */
public class Ecrire_2 extends Ecrire implements Sauvegardable, Scriptable {

    /**
     * Constructeur de la classe Ecrire.
     * 
     * @param application l'application.
     * @param editeur     l'éditeur.
     * @param c           le caractère à écrire.
     */
    public Ecrire_2(Application application, Editeur editeur, char c) {
        super(application, editeur, c);
        script();
    }

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
