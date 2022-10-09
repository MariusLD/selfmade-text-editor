package src;

public class Refaire extends Commande {

    public Refaire(Application application, Editeur editeur) {
        super(application, editeur);
    }

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
