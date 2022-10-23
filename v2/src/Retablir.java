package src;

/**
 * Classe qui gère la restauration d'un état après l'annulation
 * d'une commande.
 */
public class Retablir extends Commande implements Scriptable {

    /**
     * Constructeur de la classe Refaire.
     * 
     * @param application l'application.
     * @param editeur     l'éditeur.
     */
    public Retablir(Application application, Editeur editeur) {
        super(application, editeur);
        script();
    }

    /**
     * Rétabli l'état de la dernière commande annulée,
     * puis sauvegarde l'état.
     */
    @Override
    public void execute() {
        editeur.resetSelection();
        Snapshot snap = application.getMemoire().retourFuture();
        if (snap != null) {
            editeur.restoreSnapshot(snap);
        }
    }

    @Override
    public void script() {
        Script s = application.getScript();
        if (s.isRegistering()) {
            s.enregistrer(this);
        }
    }
}
