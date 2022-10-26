package utilitaire;

/**
 * Implémenter cette interface pour pouvoir sauvegarder l'état de l'éditeur
 * après l'exécution d'une commande.
 */
public interface Sauvegardable {
    /**
     * Permet de sauvegarder l'état de l'éditeur.
     */
    public void sauvegarde();
}
