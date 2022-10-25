package commande;

import main.Application;
import main.Editeur;

public abstract class Commande {

    protected Application application;
    protected Editeur editeur;

    /**
     * Classe abstraite représentant une commande.
     * 
     * @param application l'application créant cette Commande.
     * @param editeur     l'éditeur sur lequel agir.
     */
    public Commande(Application application, Editeur editeur) {
        this.application = application;
        this.editeur = editeur;
    }

    /**
     * Permet de récupérer l'application.
     * 
     * @return l'application.
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Permet de récupérer l'éditeur.
     * 
     * @return l'éditeur.
     */
    public Editeur getEditeur() {
        return editeur;
    }

    /**
     * Permet d'exécuter la commande.
     * Doit être implémentée dans les classes filles.
     */
    public abstract void execute();
}