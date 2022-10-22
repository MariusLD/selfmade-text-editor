package src;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.Stack;

/**
 * Classe au centre de l'application.
 * C'est elle qu'il faut run dans le main.
 */
public class Application implements Runnable {

    private String clipboard = "";
    private Editeur editeur = new Editeur();
    private Fenetre fenetre;
    private Map<Character, Callable<Commande>> commandes = new HashMap<Character, Callable<Commande>>();

    private Stack<Memento> passe = new Stack<Memento>();
    private Stack<Memento> future = new Stack<Memento>();
    
    private Script script = new Script();

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
     * Permet de récupérer la pile des états précédents.
     * 
     * @return la pile des états précédents.
     */
    public Stack<Memento> getPasse() {
        return passe;
    }

    /**
     * Permet de récupérer la pile des états suivants.
     * 
     * @return la pile des états suivants.
     */
    public Stack<Memento> getFuture() {
        return future;
    }

    /**
     * Permet de récupérer le script.
     * 
     * @return le script.
     */
    public Script getScript() {
        return script;
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
        new DeplacerCurseur_2(this, editeur, direction).execute();
        fenetre.refreshSelectionHighlight();
        fenetre.refreshCursorHighlight();
    }

    public void deplaceSelection(Direction direction) {
        new DeplacerSelection_2(this, editeur, direction).execute();
        fenetre.refreshSelectionHighlight();
    }

    public void ecrit(char c) {
        new Ecrire_2(this, editeur, c).execute();
        fenetre.refreshText();
    }

    public void supprime(Direction direction) {
        new Supprimer_2(this, editeur, direction).execute();
        fenetre.refreshText();
    }

    private void initCommandes() {
        commandes.put('c', () -> new Copier_2(this, editeur));
        commandes.put('v', () -> new Coller_2(this, editeur));
        commandes.put('x', () -> new Couper_2(this, editeur));
        commandes.put('z', () -> new Annuler(this, editeur));
        commandes.put('y', () -> new Retablir(this, editeur));
        commandes.put('e', () -> new EnregistrerScript(this, editeur));
        commandes.put('r', () -> new RejouerScript(this, editeur));
    }

    public Commande getCommande(char c) {
        try {
            return commandes.get(c).call();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Permet de récupérer le dernier état sauvegardé.
     * 
     * @return le dernier état sauvegardé.
     */
    public Memento popPasse() {
        if (!passe.isEmpty()) {
            return passe.pop();
        } else {
            return null;
        }
    }

    /**
     * Permet de récupérer le dernier état ayant été annulé.
     * 
     * @return le dernier état ayant été annulé.
     */
    public Memento popFuture() {
        if (!future.isEmpty()) {
            return future.pop();
        } else {
            return null;
        }
    }

    /**
     * Permet de sauvegarder un état.
     * 
     * @param memento l'état à sauvegarder.
     */
    public void pushPasse(Memento m) {
        passe.push(m);
    }

    /**
     * Permet de sauvegarder un état ayant été annulé.
     * 
     * @param memento l'état ayant été annulé.
     */
    public void pushFuture(Memento m) {
        future.push(m);
    }

    /**
     * Permet de vider la pile des états ayant été annulés.
     */
    public void resetFuture() {
        future.clear();
    }

    /**
     * Permet de savoir si la pile des états sauvegardés est vide.
     * 
     * @return true si la pile des états sauvegardés est vide, false sinon.
     */
    public boolean pasDePasse() {
        return passe.isEmpty();
    }

    /**
     * Permet de savoir si la pile des états ayant été annulés est vide.
     * 
     * @return true si la pile des états ayant été annulés est vide, false sinon.
     */
    public boolean pasDeFuture() {
        return future.isEmpty();
    }

    /**
     * Permet de lancer l'application.
     */
    public void run() {
        fenetre.show();
    }
}
