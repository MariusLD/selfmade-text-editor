package commande;

import main.Application;
import main.Editeur;
import main.Script;

/**
 * Commande qui gère l'enregistrement du script.
 */
public class EnregistrerScript extends Commande {

    /**
     * Commande qui gère l'enregistrement du script.
     * 
     * @param application l'application créant cette commande.
     * @param editeur
     */
    public EnregistrerScript(Application application, Editeur editeur) {
        super(application, editeur);
    }

    /**
     * Si le script n'est pas en cours d'enregistrement, il commence à l'être
     * (réinitialise le script).
     * Sinon, il s'arrête.
     */
    @Override
    public void execute() {
        Script s = application.getScript();
        if (!s.isRegistering()) {
            s.reset();
            editeur.resetSelection();
            s.setRegistering(true);
            // on met la teinte rouge pour indiquer que l'enregistrement est en cours
            application.getFenetre().setTeinteRouge();
        } else {
            s.setRegistering(false);
            // on met la teinte grise pour indiquer que l'enregistrement est terminé
            application.getFenetre().setTeinteGrise();
        }
    }
}
