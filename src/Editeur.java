public class Editeur{
    private StringBuffer buffer;
    private Selection curseur;

    public Editeur(){
        this.buffer = new StringBuffer();
        this.curseur = new Selection(buffer);
    }

    public StringBuffer getBuffer() {
        return this.buffer;
    }

    public void setBuffer(String s) {
        this.buffer.append(s);
        curseur.refresh(buffer);
    }

    public Selection getCurseur() {
        return curseur;
    }

    public String getSelection(){
        return buffer.substring(curseur.getLeft().getIndex(),
        curseur.getRight().getIndex()+1);
    }

    public void supprimerSelection(){
        buffer.delete(curseur.getLeft().getIndex(),
            curseur.getRight().getIndex()+1);
        curseur.refresh(buffer);
    }

    public void modifierSelection(String texte){
        if (curseur.getLeft().getIndex() == curseur.getRight().getIndex()) {
            buffer.replace(curseur.getLeft().getIndex()+1,
            curseur.getRight().getIndex()+1, texte);
        } else {
            buffer.replace(curseur.getLeft().getIndex(),
            curseur.getRight().getIndex(), texte);
        }
    }
}