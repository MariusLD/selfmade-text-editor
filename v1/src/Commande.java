package src;
public abstract class Commande {
    // two fields, an application and an editeur
    protected Application application;
    protected Editeur editeur;

    public Commande(Application application, Editeur editeur) {
        this.application = application;
        this.editeur = editeur;
    }

    // getters
    public Application getApplication() {
        return application;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public abstract void execute();
}