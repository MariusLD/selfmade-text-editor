package commande;

import main.Application;
import main.Editeur;

public class Copier extends Commande {

    /**
     * Commande qui gère la copie.
     * 
     * @param application l'application créant cette commande.
     * @param editeur     l'éditeur sur lequel agir.
     */
    public Copier(Application application, Editeur editeur) {
        super(application, editeur);
    }

    /**
     * Copie le texte sélectionné dans le presse-papier.
     */
    @Override
    public void execute() {
        application.setClipboard(editeur.getSelectedText());
        editeur.resetSelection();
    }
}