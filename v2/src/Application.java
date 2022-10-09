package src;

import java.util.Stack;

/**
 * Classe au centre de l'application.
 * C'est elle qu'il faut run dans le main.
 */
public class Application implements Runnable {

    private String clipboard;
    private Editeur editeur;
    private Fenetre fenetre;
    private String mode;

    private Stack<Memento> passe;
    private Stack<Memento> future;

    /**
     * Constructeur de la classe Application.
     */
    public Application() {
        this.editeur = new Editeur();
        this.fenetre = new Fenetre(this);
        this.clipboard = "";
        this.mode = "input";
        this.passe = new Stack<Memento>();
        this.future = new Stack<Memento>();
    }

    /**
     * Permet de récupérer le contenu du presse-papier.
     * @return le contenu du presse-papier.
     */
    public String getClipboard() {
        return clipboard;
    }

    /**
     * Permet de récupérer l'éditeur.
     * @return l'éditeur.
     */
    public Editeur getEditeur() {
        return editeur;
    }

    /**
     * Permet de récupérer la fenêtre.
     * @return la fenêtre.
     */
    public Fenetre getFenetre() {
        return fenetre;
    }

    /**
     * Permet de récupérer le mode.
     * @return le mode.
     */
    public String getMode() {
        return mode;
    }

    /**
     * Permet de récupérer la pile des états précédents.
     * @return la pile des états précédents.
     */
    public Stack<Memento> getPasse() {
        return passe;
    }

    /**
     * Permet de récupérer la pile des états suivants.
     * @return la pile des états suivants.
     */
    public Stack<Memento> getFuture() {
        return future;
    }

    /**
     * Permet de modifier le contenu du presse-papier.
     * @param clipboard le nouveau contenu du presse-papier.
     */
    public void setClipboard(String clipboard) {
        this.clipboard = clipboard;
    }

    /**
     * Permet de modifier le mode.
     * @param mode le nouvel mode.
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * Permet de récupérer le dernier état sauvegardé.
     * @return le dernier état sauvegardé.
     */
    public Memento popPasse(){
        if(!passe.isEmpty()){
            return passe.pop();
        }
        else{
            return null;
        }
    }

    /**
     * Permet de récupérer le dernier état ayant été annulé.
     * @return le dernier état ayant été annulé.
     */
    public Memento popFuture(){
        if(!future.isEmpty()){
            return future.pop();
        }
        else{
            return null;
        }
    }

    /**
     * Permet de sauvegarder un état.
     * @param memento l'état à sauvegarder.
     */
    public void pushPasse(Memento m){
        passe.push(m);
    }

    /**
     * Permet de sauvegarder un état ayant été annulé.
     * @param memento l'état ayant été annulé.
     */
    public void pushFuture(Memento m){
        future.push(m);
    }

    /**
     * Permet de vider la pile des états ayant été annulés.
     */
    public void resetFuture(){
        future.clear();
    }

    /**
     * Permet de savoir si la pile des états sauvegardés est vide.
     * @return true si la pile des états sauvegardés est vide, false sinon.
     */
    public boolean pasDePasse(){
        return passe.isEmpty();
    }

    /**
     * Permet de savoir si la pile des états ayant été annulés est vide.
     * @return true si la pile des états ayant été annulés est vide, false sinon.
     */
    public boolean pasDeFuture(){
        return future.isEmpty();
    }

    /**
     * Permet de lancer l'application.
     */
    public void run(){
        fenetre.show();
    }
}
