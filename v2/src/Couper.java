package src;

/**
 * Classe qui gère le coupage.
 */
public class Couper extends Commande {

    /**
     * Constructeur de la classe Couper.
     * @param application l'application.
     * @param editeur l'éditeur.
     */
    public Couper(Application application, Editeur editeur) {
        super(application, editeur);
    }

    /**
     * Sauvegarde l'état, puis copie le texte sélectionné dans le presse-papier et le supprime.
     */
    @Override
    public void execute() {
        application.resetFuture();
        application.pushPasse(editeur.getMemento());
        application.setClipboard(editeur.getSelectedText());
        editeur.removeSelectedText();
        editeur.resetSelection();
    }
}