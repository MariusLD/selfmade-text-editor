package src;

public class Copier_2 extends Copier implements Sauvegardable, Scriptable {
    public Copier_2(Application application, Editeur editeur) {
        super(application, editeur);
        script();
    }

    @Override
    public void execute() {
        super.execute();
        save();
    }

    @Override
    public void save() {
        application.getMemoire().sauvegarde(editeur.createSnapshot());
    }

    @Override
    public void script() {
        Script s = application.getScript();
        if (s.isRegistering()) {
            s.enregistrer(this);
        }
    }

}
