package src;

/**
 * Classe qui gère la sélection.
 */
public class Selection {
    private int offset;

    /**
     * Constructeur de la classe Selection.
     * @param offset l'offset de la sélection.
     */
    public Selection(){
        this.offset = 0;
    }

    /**
     * Permer d'obtenir l'offset de la sélection.
     * @return l'offset de la sélection.
     */
    public int getOffset(){
        return offset;
    }

    /**
     * Permet de modifier l'offset de la sélection.
     * @param offset l'offset de la sélection.
     */
    public void setOffset(int offset){
        this.offset = offset;
    }
}
