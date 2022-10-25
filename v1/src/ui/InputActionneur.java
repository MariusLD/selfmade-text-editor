package ui;

import java.awt.event.KeyEvent;

import utilitaire.Direction;

public class InputActionneur extends Actionneur {

    /**
     * Représente l'état de l'actionneur pour écrire, supprimer
     * et déplacer le curseur.
     * 
     * @param fenetre la fenêtre utilisant cet Actionneur.
     */
    public InputActionneur(Fenetre fenetre) {
        super(fenetre);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            fenetre.getApplication().deplaceCurseur(Direction.GAUCHE);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            fenetre.getApplication().deplaceCurseur(Direction.DROITE);
        } else if (keyCode == KeyEvent.VK_UP) {
            fenetre.getApplication().deplaceCurseur(Direction.HAUT);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            fenetre.getApplication().deplaceCurseur(Direction.BAS);
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            changeActionneur(new CommandeActionneur(fenetre));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();

        if (isPrintableChar(keyChar) || keyChar == KeyEvent.VK_ENTER) {
            fenetre.getApplication().ecrit(keyChar);
        } else if (keyChar == KeyEvent.VK_BACK_SPACE) {
            fenetre.getApplication().supprime(Direction.GAUCHE);
        } else if (keyChar == KeyEvent.VK_DELETE) {
            fenetre.getApplication().supprime(Direction.DROITE);
        }
    }

    /**
     * Permet de savoir si un caractère est imprimable.
     * écrit par OscarRyz, disponible sur
     * https://stackoverflow.com/a/418560/17288453
     * 
     * @param c le caractère.
     * @return true si le caractère est imprimable, false sinon.
     */
    private boolean isPrintableChar(char c) {
        Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
        return !Character.isISOControl(c) &&
                c != KeyEvent.CHAR_UNDEFINED &&
                block != null &&
                block != Character.UnicodeBlock.SPECIALS;
    }
}
