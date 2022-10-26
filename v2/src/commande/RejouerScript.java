package commande;

import main.Application;
import main.Editeur;
import main.Script;

/**
 * Commande qui gère le replay du script.
 */
public class RejouerScript extends Commande {

    /**
     * Commande qui gère le replay du script.
     * 
     * @param application l'application créant cette commande.
     * @param editeur
     */
    public RejouerScript(Application application, Editeur editeur) {
        super(application, editeur);
    }

    /**
     * Si le script n'enregistre pas, le rejoue.
     */
    @Override
    public void execute() {
        Script s = application.getScript();
        if (!s.isRegistering()) {
            s.jouer();
        }
    }
}
