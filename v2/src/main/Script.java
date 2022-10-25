package main;

import java.util.LinkedList;
import java.util.List;

import commande.Commande;

public class Script {
    private List<Commande> commandes = new LinkedList<Commande>();
    private boolean registering = false;

    public List<Commande> getCommandes() {
        return commandes;
    }

    public boolean isRegistering() {
        return registering;
    }

    public void setRegistering(boolean registering) {
        this.registering = registering;
    }

    public void reset() {
        commandes.clear();
    }

    public void jouer() {
        for (Commande c : commandes) {
            c.execute();
        }
    }

    public void enregistrer(Commande c) {
        commandes.add(c);
    }
}
