package src;

/**
 * Classe qui gère l'écriture.
 */
public class Ecrire extends Commande {
    private char c;

    /**
     * Constructeur de la classe Ecrire.
     * 
     * @param application l'application.
     * @param editeur     l'éditeur.
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
