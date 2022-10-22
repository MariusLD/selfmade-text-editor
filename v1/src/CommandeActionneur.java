package src;

import java.awt.event.*;

public class CommandeActionneur extends Actionneur {

    public CommandeActionneur(Fenetre fenetre) {
        super(fenetre);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_C) {
            fenetre.getApplication().copie();
        } else if (keyCode == KeyEvent.VK_X) {
            fenetre.getApplication().coupe();
        } else if (keyCode == KeyEvent.VK_V) {
            fenetre.getApplication().colle();
        } else if (keyCode == KeyEvent.VK_LEFT) {
            fenetre.getApplication().deplaceSelection('l');
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            fenetre.getApplication().deplaceSelection('r');
        } else if (keyCode == KeyEvent.VK_UP) {
            fenetre.getApplication().deplaceSelection('u');
        } else if (keyCode == KeyEvent.VK_DOWN) {
            fenetre.getApplication().deplaceSelection('d');
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            changeActionneur(new InputActionneur(fenetre));
        }
    }
}
