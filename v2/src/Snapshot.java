package src;

/**
 * Classe qui gère les sauvegardes.
 */
public class Snapshot implements Memento {

    private Editeur originator;
    private StringBuffer texte;
    private int curseur;

    /**
     * Constructeur de la classe Memento.
     * @param texte le buffer.
     * @param curseur la position du curseur.
     */
    public Snapshot(Editeur originator, StringBuffer texte, int curseur) {
        this.originator = originator;
        this.texte = texte;
        this.curseur = curseur;
    }

    public Editeur getOriginator() {
        return originator;
    }

    /**
     * Permet d'obtenir le buffer.
     * @return le buffer.
     */
    public StringBuffer getTexte() {
        return texte;
    }

    /**
     * Permet d'obtenir la position du curseur.
     * @return la position du curseur.
     */
    public int getCurseur() {
        return curseur;
    }

    @Override
    public void restore() {
        originator.setCurseur(curseur);
        originator.setTexte(texte);
    }
}
