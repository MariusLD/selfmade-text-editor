package main;

import java.util.LinkedList;
import java.util.List;

import commande.Commande;

/**
 * Gère une suite de commande, pour pouvoir les rejouer.
 */
public class Script {
    private List<Commande> commandes = new LinkedList<Commande>();
    private boolean registering = false;

    /**
     * Permet d'obtenir la suite de commande.
     * 
     * @return la suite de commande.
     */
    public List<Commande> getCommandes() {
        return commandes;
    }

    /**
     * Indique si le script est en train d'enregistrer les commandes.
     */
    public boolean isRegistering() {
        return registering;
    }

    /**
     * Permet d'indiquer s'il faut enregistrer les commandes.
     * 
     * @param registering true si on doit enregistrer les commandes, false sinon.
     */
    public void setRegistering(boolean registering) {
        this.registering = registering;
    }

    /**
     * Réinitialise la suite de commande.
     */
    public void reset() {
        commandes.clear();
    }

    /**
     * Rejoue la suite de commande.
     */
    public void jouer() {
        for (Commande c : commandes) {
            c.execute();
        }
    }

    /**
     * Ajoute une commande à la suite de commande.
     * 
     * @param c la commande à ajouter.
     */
    public void enregistrer(Commande c) {
        commandes.add(c);
    }
}
