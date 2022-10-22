package src;

import java.awt.event.*;

/**
 * Classe qui permet de gérer les actions de l'utilisateur.
 * C'est elle qui va créer les commandes et les exécuter.
 */
public class InputActionneur extends Actionneur {

    /**
     * Constructeur de la classe InputActionneur.
     * 
     * @param fenetre     la fenêtre.
     */
    public InputActionneur(Fenetre fenetre) {
        super(fenetre);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            fenetre.getApplication().deplaceCurseur('l');
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            fenetre.getApplication().deplaceCurseur('r');
        } else if (keyCode == KeyEvent.VK_UP) {
            fenetre.getApplication().deplaceCurseur('u');
        } else if (keyCode == KeyEvent.VK_DOWN) {
            fenetre.getApplication().deplaceCurseur('d');
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
            fenetre.getApplication().supprime('b');
        } else if (keyChar == KeyEvent.VK_DELETE) {
            fenetre.getApplication().supprime('a');
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
