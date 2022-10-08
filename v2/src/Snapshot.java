public class Snapshot  {
    private Editeur e;
    private StringBuffer buffer;
    private Selection curseur;

    public Snapshot(Editeur e, StringBuffer buffer, Selection curseur) {
        this.e = e;
        this.buffer = new StringBuffer();
        this.buffer.append(buffer);
        this.curseur = curseur;
    }

    public void getBack() {
        e.setBuffer(this.buffer);
        e.setCurseur(this.curseur);
    }
}