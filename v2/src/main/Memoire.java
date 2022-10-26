package main;

import java.util.Stack;

/**
 * Gère l'ensemble des sauvegardes de l'éditeur.
 */
public class Memoire {
    private Stack<Snapshot> passe = new Stack<Snapshot>();
    private Stack<Snapshot> future = new Stack<Snapshot>();

    private Snapshot present;

    /**
     * Initialise la mémoire.
     * 
     * @param present l'état initial de l'éditeur.
     */
    public Memoire(Snapshot present) {
        this.present = present;
    }

    /**
     * Permet de récupérer la pile des états passés.
     * 
     * @return la pile des états passés.
     */
    public Stack<Snapshot> getPasse() {
        return passe;
    }

    /**
     * Permet de récupérer la pile des états futures.
     * 
     * @return la pile des états futures.
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

    /**
     * Permet de sauvegarder un nouvel état.
     * Réinitialise le future.
     * 
     * @param snap l'état à sauvegarder.
     */
    public void sauvegarde(Snapshot snap) {
        passe.push(present.clone());
        present = snap;
        future.clear();
    }

    /**
     * Permet de retourner dans le passé.
     * 
     * @return l'état présent restauré depuis le passé s'il y en a un,
     *         sinon null.
     */
    public Snapshot retourPasse() {
        if (!passe.isEmpty()) {
            future.push(present.clone());
            present = passe.pop();
            return present.clone();
        }
        return null;
    }

    /**
     * Permet de retourner dans le futur.
     * 
     * @return l'état présent restauré depuis le future s'il y en a un,
     *         sinon null.
     */
    public Snapshot retourFuture() {
        if (!future.isEmpty()) {
            passe.push(present.clone());
            present = future.pop();
            return present.clone();
        }
        return null;
    }
}
