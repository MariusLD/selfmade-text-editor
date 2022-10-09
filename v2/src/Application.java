package src;

import java.util.Stack;

public class Application implements Runnable {
    // has a clipboard field
    private String clipboard;

    // has an editeur field
    private Editeur editeur;

    private Fenetre fenetre;

    private String mode;

    // a memento pile
    private Stack<Memento> passe;
    private Stack<Memento> future;

    // constructor
    public Application() {
        this.editeur = new Editeur();
        this.fenetre = new Fenetre(this);
        this.clipboard = "";
        this.mode = "input";
        this.passe = new Stack<Memento>();
        this.future = new Stack<Memento>();
    }

    // has a method to get the clipboard
    public String getClipboard() {
        return clipboard;
    }

    // has a method to get the editeur
    public Editeur getEditeur() {
        return editeur;
    }

    public Fenetre getFenetre() {
        return fenetre;
    }

    public String getMode() {
        return mode;
    }

    public Stack<Memento> getPasse() {
        return passe;
    }

    public Stack<Memento> getFuture() {
        return future;
    }

    // has a method to set the clipboard
    public void setClipboard(String texte) {
        this.clipboard = texte;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Memento popPasse(){
        if(!passe.isEmpty()){
            return passe.pop();
        }
        else{
            return null;
        }
    }

    public Memento popFuture(){
        if(!future.isEmpty()){
            return future.pop();
        }
        else{
            return null;
        }
    }

    public void pushPasse(Memento m){
        passe.push(m);
    }

    public void pushFuture(Memento m){
        future.push(m);
    }

    public void resetFuture(){
        future.clear();
    }

    public boolean pasDePasse(){
        return passe.isEmpty();
    }

    public boolean pasDeFuture(){
        return future.isEmpty();
    }

    public void run(){
        fenetre.show();
    }
}
