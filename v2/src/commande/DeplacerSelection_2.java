package commande;

import main.Application;
import main.Editeur;
import main.Script;
import utilitaire.Scriptable;
import utilitaire.Direction;

/**
 * Commande qui gère le déplacement de la sélection et implémente
 * l'interface Scriptable.
 */
public class DeplacerSelection_2 extends DeplacerSelection implements Scriptable {

    /**
     * Commande qui gère le déplacement de la sélection et implémente
     * l'interface Scriptable.
     * 
     * @param application l'application créant cette commande.
     * @param editeur     l'éditeur sur lequel agir.
     * @param direction   la direction dans laquelle déplacer la sélection.
     */
    public DeplacerSelection_2(Application application, Editeur editeur, Direction direction) {
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
