package src;

/**
 * Classe abstraite représentant une commande.
 */
public abstract class Commande {

    protected Application application;
    protected Editeur editeur;

    /**
     * Constructeur de la classe Commande.
     * @param application l'application.
     * @param editeur l'éditeur.
     */
    public Commande(Application application, Editeur editeur) {
        this.application = application;
        this.editeur = editeur;
    }

    /**
     * Permet de récupérer l'application.
     * @return l'application.
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Permet de récupérer l'éditeur.
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