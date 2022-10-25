package commande;

import main.Application;
import main.Editeur;

public class Couper extends Commande {

    /**
     * Commande qui gère le coupage.
     * 
     * @param application l'application créant cette commande.
     * @param editeur     l'éditeur sur lequel agir.
     */
    public Couper(Application application, Editeur editeur) {
        super(application, editeur);
    }

    /**
     * Copie le texte sélectionné dans le presse-papier et le supprime.
     */
    @Override
    public void execute() {
        application.setClipboard(editeur.getSelectedText());
        editeur.removeSelectedText();
        editeur.resetSelection();
    }
}