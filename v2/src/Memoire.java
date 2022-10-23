package src;

import java.util.Stack;

public class Memoire {
    private Stack<Snapshot> passe = new Stack<Snapshot>();
    private Stack<Snapshot> future = new Stack<Snapshot>();

    private Snapshot present;

    public Memoire(Snapshot present) {
        this.present = present;
    }

    /**
     * Permet de récupérer la pile des états précédents.
     * 
     * @return la pile des états précédents.
     */
    public Stack<Snapshot> getPasse() {
        return passe;
    }

    /**
     * Permet de récupérer la pile des états suivants.
     * 
     * @return la pile des états suivants.
     */
    public Stack<Snapshot> getFuture() {
        return future;
    }

    /**
     * Permet de récupérer l'état présent.
     * 
     * @return l'état présent.
     */
    public Snapshot getPresent() {
        return present;
    }

    public void sauvegarde(Snapshot snap) {
        passe.push(present.clone());
        present = snap;
        future.clear();
    }

    public Snapshot retourPasse() {
        if (!passe.isEmpty()) {
            future.push(present.clone());
            present = passe.pop();
            return present.clone();
        }
        return null;
    }

    public Snapshot retourFuture() {
        if (!future.isEmpty()) {
            passe.push(present.clone());
            present = future.pop();
            return present.clone();
        }
        return null;
    }
}
