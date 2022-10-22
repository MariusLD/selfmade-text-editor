package src;

/**
 * Classe qui gère le coupage.
 */
public class Couper_2 extends Couper implements Sauvegardable, Scriptable {

    /**
     * Constructeur de la classe Couper.
     * @param application l'application.
     * @param editeur l'éditeur.
     */
    public Couper_2(Application application, Editeur editeur) {
        super(application, editeur);
        script();
    }

    /**
     * Sauvegarde l'état, puis copie le texte sélectionné dans le presse-papier et le supprime.
     */
    @Override
    public void execute() {
        save();
        super.execute();
    }

    @Override
    public void save() {
        application.resetFuture();
        application.pushPasse(editeur.createMemento());
    }

    @Override
    public void script() {
        Script s = application.getScript();
        if(s.isRegistering()) {
            s.enregistrer(this);
        }
    }
}