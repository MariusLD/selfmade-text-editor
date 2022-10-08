public class Editeur{
    private StringBuffer buffer;
    private Selection curseur;
    private Memento memento;

    public Editeur(){
        this.buffer = new StringBuffer();
        this.curseur = new Selection(buffer);
        this.memento = new Memento();
    }

    public StringBuffer getBuffer() {
        return this.buffer;
    }

    public void setBuffer(String s) {
        this.buffer.append(s);
        curseur.refresh(buffer);
    }

    public void setBuffer(StringBuffer s) {
        System.out.println("Voici le contenu du buffer après via le set : " + s.toString());
        this.buffer = s;
        curseur.refresh(buffer);
    }

    public void setCurseur(Selection curseur) {
        this.curseur = curseur;
    }

    public Selection getCurseur() {
        return curseur;
    }

    public String getSelection(){
        return buffer.substring(curseur.getLeft().getIndex(),
        curseur.getRight().getIndex()+1);
    }

    public void supprimerSelection(){
        creerSnapshotPrec();
        System.out.println("Voici le contenu du buffer au moment de créer la snapshot : " + this.buffer.toString());
        buffer.delete(curseur.getLeft().getIndex(),
            curseur.getRight().getIndex()+1);
        curseur.refresh(buffer);
    }

    public void modifierSelection(String texte){
        creerSnapshotPrec();
        if (curseur.getLeft().getIndex() == curseur.getRight().getIndex()) {
            buffer.replace(curseur.getLeft().getIndex()+1,
            curseur.getRight().getIndex()+1, texte);
        } else {
            buffer.replace(curseur.getLeft().getIndex(),
            curseur.getRight().getIndex(), texte);
        }
        curseur.refresh(buffer);
    }

    public void undo() {
        if (!memento.getPilePrec().empty()) {
            creerSnapshotSuiv();
            memento.getPilePrec().pop().getBack();
        } else {
            System.out.println("Pile des précédents vide");
        }
    }

    public void redo() {
        if (!memento.getPileSuiv().empty()) {
            creerSnapshotPrec();
            memento.getPileSuiv().pop().getBack();
        } else {
            System.out.println("Pile des suivants vide");
        }
    }

    public void creerSnapshotPrec() {
        System.out.println("Snapshot créée !");
        Snapshot s = new Snapshot(this, buffer, curseur);
        memento.getPilePrec().push(s);
    }

    public void creerSnapshotSuiv() {
        Snapshot s = new Snapshot(this, buffer, curseur);
        memento.getPileSuiv().push(s);
    }
}