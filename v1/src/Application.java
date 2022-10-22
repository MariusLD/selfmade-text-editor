package src;

/**
 * Classe au centre de l'application.
 * C'est elle qu'il faut run dans le main.
 */
public class Application implements Runnable {
    
    private String clipboard = "";
    private Editeur editeur = new Editeur();
    private Fenetre fenetre;

    /**
     * Constructeur de la classe Application.
     */
    public Application() {
        this.fenetre = new Fenetre(this);
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
     * Permet de modifier le contenu du presse-papier.
     * @param clipboard le nouveau contenu du presse-papier.
     */
    public void setClipboard(String clipboard) {
        this.clipboard = clipboard;
    }

    public void deplaceCurseur(char direction) {
        new DeplacerCurseur(this, editeur, direction).execute();
        fenetre.refreshSelectionHighlight();
        fenetre.refreshCursorHighlight();
    }

    public void deplaceSelection(char direction) {
        new DeplacerSelection(this, editeur, direction).execute();
        fenetre.refreshSelectionHighlight();
    }

    public void ecrit(char c) {
        new Ecrire(this, editeur, c).execute();
        fenetre.refreshText();
    }

    public void supprime(char direction) {
        new Supprimer(this, editeur, direction).execute();
        fenetre.refreshText();
    }

    public void copie() {
        new Copier(this, editeur).execute();
        fenetre.refreshSelectionHighlight();
    }

    public void coupe() {
        new Couper(this, editeur).execute();
        fenetre.refreshText();
    }

    public void colle() {
        new Coller(this, editeur).execute();
        fenetre.refreshText();
    }


    /**
     * Permet de lancer l'application.
     */
    public void run(){
        fenetre.show();
    }
}
