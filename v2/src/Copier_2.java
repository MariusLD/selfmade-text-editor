package src;

/**
 * Classe qui gère la copie.
 */
public class Copier_2 extends Commande {
    
    /**
     * Constructeur de la classe Copier.
     * @param application l'application.
     * @param editeur l'éditeur.
     */
    public Copier_2(Application application, Editeur editeur) {
        super(application, editeur);
    }

    /**
     * Sauvegarde l'état, puis copie le texte sélectionné dans le presse-papier.
     */
    @Override
    public void execute() {
        application.resetFuture();
        application.pushPasse(editeur.getMemento());
        application.setClipboard(editeur.getSelectedText());
        editeur.resetSelection();
    }
}