package src;

/**
 * Classe qui gère l'annulation d'une action.
 */
public class Annuler extends Commande {

    /**
     * Constructeur de la classe Annuler.
     * @param application L'application.
     * @param editeur L'éditeur.
     */
    public Annuler(Application application, Editeur editeur) {
        super(application, editeur);
    }

    /**
     * Remet l'editeur dans son état sauvegardé précédemment.
     */
    @Override
    public void execute() {
        editeur.resetSelection();
        if(application.pasDeFuture()){
            application.pushFuture(editeur.getMemento());
        }
        Memento m = application.popPasse();   
        if(m != null){
            application.pushFuture(m);
            editeur.setMemento(m);
        }
    }
}
