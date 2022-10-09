package src;

/**
 * Classe au centre de l'application.
 * Elle gère le buffer, le curseur et la sélection.
 */
public class Editeur {

    private StringBuffer texte;
    private int curseur;
    private Selection selection;

    /**
     * Constructeur de la classe Editeur.
     */
    public Editeur() {
        this.texte = new StringBuffer();
        this.curseur = 0;
        this.selection = new Selection();
    }

    /**
     * Permet d'obtenir le buffer.
     * @return le buffer.
     */
    public StringBuffer getTexte() {
        return texte;
    }

    /**
     * Permet d'obtenir le curseur.
     * @return le curseur.
     */
    public int getCurseur() {
        return curseur;
    }

    /**
     * Permet d'obtenir la sélection.
     * @return la sélection.
     */
    public Selection getSelection() {
        return selection;
    }

    /**
     * Permet d'ajouter un caractère au buffer.
     * L'ajout se fait à la position du curseur.
     * @param c le caractère à ajouter.
     */
    public void writeChar(char c) {
        texte.insert(curseur, c);
        curseur++;
    }

    /**
     * Permet de supprimer un caractère du buffer.
     * @param direction la direction de la suppression.
     * 'a' pour supprimer après le curseur, 'b' pour supprimer avant le curseur.
     */
    public void deleteChar(char direction) {
        if(direction == 'b' && curseur > 0){
            texte.deleteCharAt(curseur-1);
            curseur--;
        }
        else if(direction == 'a' && curseur < texte.length()){
            texte.deleteCharAt(curseur);
        }
    }

    /**
     * Permet de déplacer le curseur.
     * @param direction la direction du déplacement.
     * 'l' pour aller à gauche, 'r' pour aller à droite,
     * 'u' pour aller en haut, 'd' pour aller en bas.
     */
    public void moveCurseur(char direction) {
        if(!emptySelection()){
            resetSelection();
        }
        if(direction == 'l' && curseur > 0){
            curseur--;
        }
        else if(direction == 'r' && curseur < texte.length()){
            curseur++;
        }
        else if(direction == 'u'){
            int i = curseur - 1;
            while(i >= 0 && texte.charAt(i) != '\n'){
                i--;
            }
            if(i >= 0){
                curseur = i;
            }
            else{
                curseur = 0;
            }
        }
        else if(direction == 'd'){
            int i = curseur;
            while(i < texte.length() && texte.charAt(i) != '\n'){
                i++;
            }
            if(i < texte.length()){
                curseur = i+1;
            }
            else{
                curseur = texte.length();
            }
        }
    }

    /**
     * Permet de déplacer la selection.
     * @param direction la direction du déplacement.
     * 'l' pour aller à gauche, 'r' pour aller à droite,
     * 'u' pour aller en haut, 'd' pour aller en bas.
     */
    public void moveSelection(char direction) {
        if(direction == 'l' && curseur + selection.getOffset() > 0){
            selection.setOffset(selection.getOffset()-1);
        }
        else if(direction == 'r' && curseur + selection.getOffset() < texte.length()){
            selection.setOffset(selection.getOffset()+1);
        }
        else if(direction == 'u'){
            int i = selection.getOffset() - 1;
            while(curseur + i >= 0 && texte.charAt(curseur + i) != '\n'){
                i--;
            }
            if(curseur + i >= 0){
                selection.setOffset(i);
            }
            else{
                selection.setOffset(-curseur);
            }
        }
        else if(direction == 'd'){
            int i = selection.getOffset()+1;
            while(curseur + i < texte.length() && texte.charAt(curseur + i) != '\n'){
                i++;
            }
            if(curseur + i < texte.length()){
                selection.setOffset(i+1);
            }
            else{
                selection.setOffset(texte.length()-curseur);
            }
        }
    }

    /**
     * Réinitialise la sélection.
     */
    public void resetSelection() {
        selection.setOffset(0);
    }

    /**
     * Permet de savoir si la sélection est vide.
     * @return true si la sélection est vide, false sinon.
     */
    public boolean emptySelection() {
        return selection.getOffset() == 0;
    }

    /**
     * Permet d'obtenir le texte sélectionné.
     * @return le texte sélectionné.
     */
    public String getSelectedText() {
        int off = selection.getOffset();
        if(off < 0){
            return texte.substring(curseur+off, curseur);
        }
        else{
            return texte.substring(curseur, curseur+off);
        }
    }

    /**
     * Permet de remplacer le texte sélectionné par un autre texte.
     * @param replacement le texte de remplacement.
     */
    public void setSelectedText(String replacement) {
        int off = selection.getOffset();
        if(off < 0){
            this.texte.replace(curseur+off, curseur, replacement);
            curseur += off + replacement.length();
        }
        else{
            this.texte.replace(curseur, curseur+off, replacement);
            curseur += replacement.length();
        }
    }

    /**
     * Permet de supprimer le texte sélectionné.
     */
    public void removeSelectedText() {
        int off = selection.getOffset();
        if(off < 0){
            this.texte.delete(curseur+off, curseur);
            curseur += off;
        }
        else{
            this.texte.delete(curseur, curseur+off);
        }
    }

    /**
     * Permet de créer une sauvegarde de l'état.
     * @return la sauvegarde.
     */
    public Memento getMemento() {
        return new Memento(new StringBuffer(texte), curseur);
    }

    /**
     * Permet de restaurer un état depuis une sauvegarde.
     * @param memento la sauvegarde de l'état à restaurer.
     */
    public void setMemento(Memento m) {
        this.texte = m.getTexte();
        this.curseur = m.getCurseur();
    }
}
