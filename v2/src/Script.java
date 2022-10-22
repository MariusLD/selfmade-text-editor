package src;

import java.util.LinkedList;
import java.util.List;

public class Script {
    private List<Commande> commandes = new LinkedList<Commande>();
    private boolean registering = false;

    public Script() {
    }

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
