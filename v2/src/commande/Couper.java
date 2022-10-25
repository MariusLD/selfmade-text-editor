package commande;

import main.Application;
import main.Editeur;

/**
 * Classe qui gère le coupage.
 */
public class Couper extends Commande {

    /**
     * Constructeur de la classe Couper.
     * 
     * @param application l'application.
     * @param editeur     l'éditeur.
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