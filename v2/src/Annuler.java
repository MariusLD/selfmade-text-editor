package src;

public class Annuler extends Commande {

    public Annuler(Application application, Editeur editeur) {
        super(application, editeur);
    }

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
