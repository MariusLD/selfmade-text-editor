package src;

public class DeplacerSelection_2 extends DeplacerSelection implements Scriptable {
    public DeplacerSelection_2(Application application, Editeur editeur, Direction direction) {
        super(application, editeur, direction);
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
