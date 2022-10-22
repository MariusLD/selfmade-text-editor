package src;

import java.awt.event.*;

public abstract class Actionneur extends KeyAdapter {

    protected Fenetre fenetre;

    /**
     * Constructeur de la classe Actionneur.
     * @param fenetre la fenêtre.
     */
    public Actionneur(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    /**
     * Permet d'obtenir la fenêtre.
     * @return la fenêtre.
     */
    public Fenetre getFenetre() {
        return fenetre;
    }

    protected void changeActionneur(Actionneur actionneur) {
        fenetre.setActionneur(actionneur);
    }
}
