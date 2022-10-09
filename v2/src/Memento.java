package src;

public class Memento {
    private StringBuffer texte;
    private int curseur;

    public Memento(StringBuffer texte, int curseur) {
        this.texte = texte;
        this.curseur = curseur;
    }

    public StringBuffer getTexte() {
        return texte;
    }

    public int getCurseur() {
        return curseur;
    }
}
