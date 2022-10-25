package commande;

import main.Application;
import main.Editeur;
import main.Script;

public class EnregistrerScript extends Commande {

    public EnregistrerScript(Application application, Editeur editeur) {
        super(application, editeur);
    }

    @Override
    public void execute() {
        Script s = application.getScript();
        if (!s.isRegistering()) {
            s.reset();
            editeur.resetSelection();
            s.setRegistering(true);
            application.getFenetre().setTeinteRouge();
        } else {
            s.setRegistering(false);
            application.getFenetre().setTeinteGrise();
        }
    }
}
