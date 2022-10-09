package src;

/**
 * Classe qui gère les sauvegardes.
 */
public class Memento {

    private StringBuffer texte;
    private int curseur;

    /**
     * Constructeur de la classe Memento.
     * @param texte le buffer.
     * @param curseur la position du curseur.
     */
    public Memento(StringBuffer texte, int curseur) {
        this.texte = texte;
        this.curseur = curseur;
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
}
