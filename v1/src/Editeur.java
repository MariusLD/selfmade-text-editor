package src;
public class Editeur {
    //has a StringBuffer field, a position field, and a selection field
    private StringBuffer texte;
    private int curseur;
    private Selection selection;

    //constructor
    public Editeur() {
        this.texte = new StringBuffer();
        this.curseur = 0;
        this.selection = new Selection();
    }

    //getters
    public StringBuffer getTexte() {
        return texte;
    }

    public int getCurseur() {
        return curseur;
    }

    public Selection getSelection() {
        return selection;
    }

    public void writeChar(char c) {
        texte.insert(curseur, c);
        curseur++;
    }

    public void deleteChar(char position) {
        if(position == 'b' && curseur > 0){
            texte.deleteCharAt(curseur-1);
            curseur--;
        }
        else if(position == 'a' && curseur < texte.length()){
            texte.deleteCharAt(curseur);
        }
    }

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

    public void resetSelection() {
        selection.setOffset(0);
    }

    public boolean emptySelection() {
        return selection.getOffset() == 0;
    }

    //has a method to get the selected part of the buffer
    public String getSelectedText() {
        int off = selection.getOffset();
        if(off < 0){
            return texte.substring(curseur+off, curseur);
        }
        else{
            return texte.substring(curseur, curseur+off);
        }
    }

    //has a method to set the selected part of the buffer
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

    //has a method to remove the selected part of the buffer
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
}
