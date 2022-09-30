public abstract class Command {
    private Application a;
    private Editeur e;

    public Command(Application a, Editeur e){
        this.e = e;
        this.a = a;
    }

    public Application getApplication() {
        return a;
    }

    public Editeur getEditeur() {
        return e;
    }

    public abstract void execute();
}