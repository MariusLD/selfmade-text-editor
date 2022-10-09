package src;

/**
 * Classe qui gère la restauration d'un état après l'annulation
 * d'une commande.
 */
public class Refaire extends Commande {

    /**
     * Constructeur de la classe Refaire.
     * @param application l'application.
     * @param editeur l'éditeur.
     */
    public Refaire(Application application, Editeur editeur) {
        super(application, editeur);
    }

    /**
     * Rétabli l'état de la dernière commande annulée,
     * puis sauvegarde l'état.
     */
    @Override
    public void execute() {
        editeur.resetSelection();
        Memento m = application.popFuture();
        if(m != null){
            editeur.setMemento(m);
            application.pushPasse(m);
        }
    }
}
