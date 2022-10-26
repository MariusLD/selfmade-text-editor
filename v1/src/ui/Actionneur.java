package ui;

import java.awt.event.KeyAdapter;

/**
 * Classe abstraite qui permet de gérer les évènements clavier.
 */
public abstract class Actionneur extends KeyAdapter {

    protected Fenetre fenetre;

    /**
     * Classe abstraite qui permet de gérer les évènements clavier.
     * 
     * @param fenetre la fenêtre utilisant cet Actionneur.
     */
    public Actionneur(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    /**
     * Permet d'obtenir la fenêtre.
     * 
     * @return la fenêtre.
     */
    public Fenetre getFenetre() {
        return fenetre;
    }

    /**
     * Permet de changer l'etat de l'actionneur.
     * 
     * @param actionneur le nouvel etat de l'actionneur.
     */
    protected void changeActionneur(Actionneur actionneur) {
        fenetre.setActionneur(actionneur);
    }
}
