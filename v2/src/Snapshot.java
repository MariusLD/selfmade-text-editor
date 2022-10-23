package src;

/**
 * Classe qui gère les sauvegardes.
 */
public class Snapshot implements Cloneable {

    private StringBuffer texte;
    private int curseur;

    /**
     * Constructeur de la classe Snapshot.
     * 
     * @param texte   le buffer.
     * @param curseur la position du curseur.
     */
    public Snapshot(StringBuffer texte, int curseur) {
        this.texte = texte;
        this.curseur = curseur;
    }

    /**
     * Permet d'obtenir le buffer.
     * 
     * @return le buffer.
     */
    public StringBuffer getTexte() {
        return texte;
    }

    /**
     * Permet d'obtenir la position du curseur.
     * 
     * @return la position du curseur.
     */
    public int getCurseur() {
        return curseur;
    }

    @Override
    public Snapshot clone() {
        return new Snapshot(new StringBuffer(texte), curseur);
    }
}
