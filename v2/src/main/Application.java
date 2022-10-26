package main;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import commande.Annuler;
import commande.Coller_2;
import commande.Commande;
import commande.Copier_2;
import commande.Couper_2;
import commande.DeplacerCurseur_2;
import commande.DeplacerSelection_2;
import commande.Ecrire_2;
import commande.EnregistrerScript;
import commande.RejouerScript;
import commande.Retablir;
import commande.Supprimer_2;
import ui.Fenetre;
import utilitaire.Direction;

/**
 * Application à run pour lancer l'éditeur.
 */
public class Application implements Runnable {

    private String clipboard = "";
    private Editeur editeur = new Editeur();
    private Fenetre fenetre;
    private Map<Character, Callable<Commande>> commandes = new HashMap<Character, Callable<Commande>>();

    private Script script = new Script();
    private Memoire memoire = new Memoire(editeur.createSnapshot());

    /**
     * Application à run pour lancer l'éditeur.
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
     * Permet de récupérer le script.
     * 
     * @return le script.
     */
    public Script getScript() {
        return script;
    }

    /**
     * Permet de récupérer la mémoire.
     * 
     * @return la mémoire.
     */
    public Memoire getMemoire() {
        return memoire;
    }

    /**
     * Permet de modifier le contenu du presse-papier.
     * 
     * @param clipboard le nouveau contenu du presse-papier.
     */
    public void setClipboard(String clipboard) {
        this.clipboard = clipboard;
    }

    /**
     * Initialise et execute une commande DeplacerCurseur.
     * 
     * @param direction la direction dans laquelle le curseur doit se déplacer.
     */
    public void deplaceCurseur(Direction direction) {
        new DeplacerCurseur_2(this, editeur, direction).execute();
        fenetre.refreshSelectionHighlight();
        fenetre.refreshCursorHighlight();
    }

    /**
     * Initialise et execute une commande DeplacerSelection.
     * 
     * @param direction la direction dans laquelle la sélection doit se déplacer.
     */
    public void deplaceSelection(Direction direction) {
        new DeplacerSelection_2(this, editeur, direction).execute();
        fenetre.refreshSelectionHighlight();
    }

    /**
     * Initialise et execute une commande Ecrire.
     * 
     * @param caractere le caractère à écrire.
     */
    public void ecrit(char c) {
        new Ecrire_2(this, editeur, c).execute();
        fenetre.refreshText();
    }

    /**
     * Initialise et execute une commande Supprimer.
     * 
     * @param direction la direction dans laquelle le caractère doit être supprimé.
     */
    public void supprime(Direction direction) {
        new Supprimer_2(this, editeur, direction).execute();
        fenetre.refreshText();
    }

    /**
     * Permet de récupérer la commande associée à un caractère.
     * 
     * @param c le caractère.
     * @return la commande associée s'il y en a une,
     *         null sinon.
     */
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

    /**
     * Initialise la map des commandes.
     */
    private void initCommandes() {
        commandes.put('c', () -> new Copier_2(this, editeur));
        commandes.put('v', () -> new Coller_2(this, editeur));
        commandes.put('x', () -> new Couper_2(this, editeur));
        commandes.put('z', () -> new Annuler(this, editeur));
        commandes.put('y', () -> new Retablir(this, editeur));
        commandes.put('e', () -> new EnregistrerScript(this, editeur));
        commandes.put('r', () -> new RejouerScript(this, editeur));
    }
}
