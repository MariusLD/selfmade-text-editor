public class Editeur{
    private StringBuffer buffer;
    private Selection curseur;

    public Editeur(){
        this.buffer = new StringBuffer();
        this.curseur = new Selection(buffer);
    }

    public String getSelection(){
        return buffer.substring(curseur.getLeft().getIndex(),
        curseur.getRight().getIndex());
    }

    public void supprimerSelection(){
        buffer.delete(curseur.getLeft().getIndex(),
            curseur.getRight().getIndex());
    }

    public void modifierSelection(String texte){
        buffer.replace(curseur.getLeft().getIndex(),
            curseur.getRight().getIndex(), texte);
    }
}