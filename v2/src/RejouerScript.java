package src;

public class RejouerScript extends Commande{
    
    public RejouerScript(Application application, Editeur editeur){
        super(application, editeur);
    }

    @Override
    public void execute() {
        Script s = application.getScript();
        if(!s.isRegistering()){
            s.jouer();
        }
    }
}
