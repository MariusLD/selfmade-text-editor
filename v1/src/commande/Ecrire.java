package commande;

import main.Application;
import main.Editeur;

public class Ecrire extends Commande {
    private char c;

    /**
     * Commande qui gère l'écriture.
     * 
     * @param application l'application créant cette commande.
     * @param editeur     l'éditeur sur lequel agir.
     * @param c           le caractère à écrire.
     */
    public Ecrire(Application application, Editeur editeur, char c) {
        super(application, editeur);
        this.c = c;
    }

    /**
     * Permet d'obtenir le caractère à écrire.
     */
    public char getChar() {
        return c;
    }

    /**
     * Ecrit le caractère, au niveau du curseur,
     * ou remplace le texte sélectionné par le caractère.
     */
    @Override
    public void execute() {
        if (!editeur.emptySelection()) {
            editeur.removeSelectedText();
            editeur.resetSelection();
        }
        editeur.writeChar(c);
    }
}
