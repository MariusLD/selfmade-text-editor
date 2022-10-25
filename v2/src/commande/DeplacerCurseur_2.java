package commande;

import main.Application;
import main.Editeur;
import main.Script;
import utilitaire.Scriptable;
import utilitaire.Direction;

public class DeplacerCurseur_2 extends DeplacerCurseur implements Scriptable {
    public DeplacerCurseur_2(Application application, Editeur editeur, Direction direction) {
        super(application, editeur, direction);
        script();
    }

    @Override
    public void script() {
        Script s = application.getScript();
        if (s.isRegistering()) {
            s.enregistrer(this);
        }
    }

}
