import java.util.Stack;

public class Memento {
    private Stack<Snapshot> prec;
    private Stack<Snapshot> suiv;

    public Memento() {
        this.prec = new Stack<Snapshot>();
        this.suiv = new Stack<Snapshot>();
    }

    public Stack<Snapshot> getPilePrec() {
        return prec;
    }

    public Stack<Snapshot> getPileSuiv() {
        return suiv;
    }
}
