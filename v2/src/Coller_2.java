package src;

/**
 * Classe qui gère le collage.
 */
public class Coller_2 extends Coller implements Sauvegarde {

    /**
     * Constructeur de la classe Coller.
     * @param application l'application.
     * @param editeur l'éditeur.
     */
    public Coller_2(Application application, Editeur editeur) {
        super(application, editeur);
    }
    
    /**
     * Sauvegarde l'état, puis remplace le texte sélectionné par le contenu du presse-papier.
     */
    @Override
    public void execute() {
        save();
        super.execute();
    }

    @Override
    public void save() {
        application.resetFuture();
        application.pushPasse(editeur.getMemento());
    }
}