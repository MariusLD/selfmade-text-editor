package src;

/**
 * Classe au centre de l'application.
 * C'est elle qu'il faut run dans le main.
 */
public class Application implements Runnable {
    
    private String clipboard;
    private Editeur editeur;
    private Fenetre fenetre;

    /**
     * Constructeur de la classe Application.
     */
    public Application() {
        this.editeur = new Editeur();
        this.fenetre = new Fenetre(this);
        this.clipboard = "";
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
     * Permet de lancer l'application.
     */
    public void run(){
        fenetre.show();
    }
}
