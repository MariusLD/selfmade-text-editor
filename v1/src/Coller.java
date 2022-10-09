package src;

/**
 * Classe qui gère le collage.
 */
public class Coller extends Commande {

    /**
     * Constructeur de la classe Coller.
     * @param application l'application.
     * @param editeur l'éditeur.
     */
    public Coller(Application application, Editeur editeur) {
        super(application, editeur);
    }
    
    /**
     * Remplace le texte sélectionné par le contenu du presse-papier.
     */
    @Override
    public void execute() {
        editeur.setSelectedText(application.getClipboard());
        editeur.resetSelection();
    }
}