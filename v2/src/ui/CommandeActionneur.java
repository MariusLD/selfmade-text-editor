package ui;

import java.awt.event.KeyEvent;

import commande.Commande;
import utilitaire.Direction;

/**
 * Représente l'état de l'actionneur pour appeler les commandes
 * et déplacer la sélection.
 */
public class CommandeActionneur extends Actionneur {

    /**
     * Représente l'état de l'actionneur pour appeler des commandes
     * et déplacer la sélection.
     * 
     * @param fenetre la fenêtre utilisant cet Actionneur.
     */
    public CommandeActionneur(Fenetre fenetre) {
        super(fenetre);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            fenetre.getApplication().deplaceSelection(Direction.GAUCHE);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            fenetre.getApplication().deplaceSelection(Direction.DROITE);
        } else if (keyCode == KeyEvent.VK_UP) {
            fenetre.getApplication().deplaceSelection(Direction.HAUT);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            fenetre.getApplication().deplaceSelection(Direction.BAS);
        } else {
            Commande com = fenetre.getApplication().getCommande(e.getKeyChar());
            if (com != null) {
                com.execute();
                fenetre.refreshText();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            changeActionneur(new InputActionneur(fenetre));
        }
    }
}
