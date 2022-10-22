package src;

public class Copier_2 extends Copier implements Scriptable {
    public Copier_2(Application application, Editeur editeur) {
        super(application, editeur);
        script();
    }

    @Override
    public void script() {
        Script s = application.getScript();
        if (s.isRegistering()) {
            s.enregistrer(this);
        }
    }
    
}
