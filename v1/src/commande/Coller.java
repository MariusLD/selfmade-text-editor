package commande;

import main.Application;
import main.Editeur;

public class Coller extends Commande {

    /**
     * Commande qui gère le collage.
     * 
     * @param application l'application créant cette commande.
     * @param editeur     l'éditeur sur lequel agir.
     */
    public Coller(Application application, Editeur editeur) {
        super(application, editeur);
    }

    /**
     * Remplace le texte sélectionné par le contenu du presse-papier.
     */
    @Override
    public void execute() {
        editeur.setSelectedText(application.getClipboard());
        editeur.resetSelection();
    }
}