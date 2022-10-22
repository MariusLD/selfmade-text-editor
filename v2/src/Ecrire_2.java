package src;

/**
 * Classe qui gère l'écriture.
 */
public class Ecrire_2 extends Ecrire implements Sauvegarde {
    private char c;

    /**
     * Constructeur de la classe Ecrire.
     * @param application l'application.
     * @param editeur l'éditeur.
     * @param c le caractère à écrire.
     */
    public Ecrire_2(Application application, Editeur editeur, char c) {
        super(application, editeur, c);
    }

    /**
     * Sauvegarde l'état si le caractère est un espace/saut de ligne
     * (pour ne pas faire trop de sauvegardes),
     * puis l'écrit au niveau du curseur,
     * ou remplace le texte sélectionné par le caractère.
     */
    @Override
    public void execute() {
        save();
        super.execute();
    }

    @Override
    public void save() {
        application.resetFuture();
        if(application.pasDePasse() || Character.isWhitespace(c)){
            application.pushPasse(editeur.getMemento());
        }
    }
}
