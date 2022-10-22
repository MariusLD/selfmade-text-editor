package src;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Classe au centre de l'application.
 * C'est elle qu'il faut run dans le main.
 */
public class Application implements Runnable {

    private String clipboard = "";
    private Editeur editeur = new Editeur();
    private Fenetre fenetre;
    private Map<Character, Callable<Commande>> commandes = new HashMap<Character, Callable<Commande>>();

    /**
     * Constructeur de la classe Application.
     */
    public Application() {
        this.fenetre = new Fenetre(this);
        initCommandes();
    }

    /**
     * Permet de récupérer le contenu du presse-papier.
     * 
     * @return le contenu du presse-papier.
     */
    public String getClipboard() {
        return clipboard;
    }

    /**
     * Permet de récupérer l'éditeur.
     * 
     * @return l'éditeur.
     */
    public Editeur getEditeur() {
        return editeur;
    }

    /**
     * Permet de récupérer la fenêtre.
     * 
     * @return la fenêtre.
     */
    public Fenetre getFenetre() {
        return fenetre;
    }

    /**
     * Permet de récupérer la map des commandes.
     * 
     * @return la map des commandes.
     */
    public Map<Character, Callable<Commande>> getCommandes() {
        return commandes;
    }

    /**
     * Permet de modifier le contenu du presse-papier.
     * 
     * @param clipboard le nouveau contenu du presse-papier.
     */
    public void setClipboard(String clipboard) {
        this.clipboard = clipboard;
    }

    public void deplaceCurseur(Direction direction) {
        new DeplacerCurseur(this, editeur, direction).execute();
        fenetre.refreshSelectionHighlight();
        fenetre.refreshCursorHighlight();
    }

    public void deplaceSelection(Direction direction) {
        new DeplacerSelection(this, editeur, direction).execute();
        fenetre.refreshSelectionHighlight();
    }

    public void ecrit(char c) {
        new Ecrire(this, editeur, c).execute();
        fenetre.refreshText();
    }

    public void supprime(Direction direction) {
        new Supprimer(this, editeur, direction).execute();
        fenetre.refreshText();
    }

    private void initCommandes() {
        commandes.put('c', () -> new Copier(this, editeur));
        commandes.put('v', () -> new Coller(this, editeur));
        commandes.put('x', () -> new Couper(this, editeur));
    }

    public Commande getCommande(char c) {
        try {
            return commandes.get(c).call();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Permet de lancer l'application.
     */
    public void run() {
        fenetre.show();
    }
}
