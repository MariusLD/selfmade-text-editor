package src;

/**
 * Classe qui gère l'écriture.
 */
public class Ecrire_2 extends Commande {
    private char c;

    /**
     * Constructeur de la classe Ecrire.
     * @param application l'application.
     * @param editeur l'éditeur.
     * @param c le caractère à écrire.
     */
    public Ecrire_2(Application application, Editeur editeur, char c) {
        super(application, editeur);
        this.c = c;
    }

    /**
     * Sauvegarde l'état si le caractère est un espace/saut de ligne
     * (pour ne pas faire trop de sauvegardes),
     * puis l'écrit au niveau du curseur,
     * ou remplace le texte sélectionné par le caractère.
     */
    @Override
    public void execute() {
        application.resetFuture();
        if(application.pasDePasse() || Character.isWhitespace(c)){
            application.pushPasse(editeur.getMemento());
        }
        if(!editeur.emptySelection()){
            editeur.removeSelectedText();
            editeur.resetSelection();
        }
        editeur.writeChar(c);
    }

    /**
     * Permet d'obtenir le caractère à écrire.
     */
    public char getChar() {
        return c;
    }
}
