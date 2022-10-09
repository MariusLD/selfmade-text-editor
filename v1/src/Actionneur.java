package src;

import java.awt.event.*;


public class Actionneur extends KeyAdapter {

    //has two fields, application and fenetre

    private Application application;
    private Fenetre fenetre;

    public Actionneur(Application application, Fenetre fenetre) {
        this.application = application;
        this.fenetre = fenetre;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(application.getMode() == "input"){
            if(keyCode == KeyEvent.VK_LEFT){
                new DeplacerCurseur(application, application.getEditeur(), 'l').execute();
                fenetre.refreshSelectionHighlight();
                fenetre.refreshCursorHighlight();
            }
            else if(keyCode == KeyEvent.VK_RIGHT){
                new DeplacerCurseur(application, application.getEditeur(), 'r').execute();
                fenetre.refreshSelectionHighlight();
                fenetre.refreshCursorHighlight();
            }
            else if(keyCode == KeyEvent.VK_UP){
                new DeplacerCurseur(application, application.getEditeur(), 'u').execute();
                fenetre.refreshSelectionHighlight();
                fenetre.refreshCursorHighlight();
            }
            else if(keyCode == KeyEvent.VK_DOWN){
                new DeplacerCurseur(application, application.getEditeur(), 'd').execute();
                fenetre.refreshSelectionHighlight();
                fenetre.refreshCursorHighlight();
            }
            else if(keyCode == KeyEvent.VK_ESCAPE){
                application.setMode("commande");
            }
        }
        else if(application.getMode() == "commande"){
            if(keyCode == KeyEvent.VK_C){
                new Copier(application, this.application.getEditeur()).execute();
                fenetre.refreshText();
                fenetre.refreshCursorHighlight();
            }
            else if(keyCode == KeyEvent.VK_X){
                new Couper(application, this.application.getEditeur()).execute();
                fenetre.refreshText();
                fenetre.refreshCursorHighlight();
            }
            else if(keyCode == KeyEvent.VK_V){
                new Coller(application, this.application.getEditeur()).execute();
                fenetre.refreshText();
                fenetre.refreshCursorHighlight();
            }
            else if(keyCode == KeyEvent.VK_LEFT){
                new DeplacerSelection(application, application.getEditeur(), 'l').execute();
                fenetre.refreshSelectionHighlight();
            }
            else if(keyCode == KeyEvent.VK_RIGHT){
                new DeplacerSelection(application, application.getEditeur(), 'r').execute();
                fenetre.refreshSelectionHighlight();
            }
            else if(keyCode == KeyEvent.VK_UP){
                new DeplacerSelection(application, application.getEditeur(), 'u').execute();
                fenetre.refreshSelectionHighlight();
            }
            else if(keyCode == KeyEvent.VK_DOWN){
                new DeplacerSelection(application, application.getEditeur(), 'd').execute();
                fenetre.refreshSelectionHighlight();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(application.getMode() == "commande" && e.getKeyCode() == KeyEvent.VK_ESCAPE){
            application.setMode("input");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if(application.getMode() == "input"){
            if(isPrintableChar(keyChar) || keyChar == KeyEvent.VK_ENTER){
                new Ecrire(application, application.getEditeur(), keyChar).execute();
                fenetre.refreshText();
                fenetre.refreshCursorHighlight();
            }
            else if(keyChar == KeyEvent.VK_BACK_SPACE){
                new Supprimer(application, application.getEditeur(), 'b').execute();
                fenetre.refreshText();
                fenetre.refreshCursorHighlight();
            }
            else if(keyChar == KeyEvent.VK_DELETE){
                new Supprimer(application, application.getEditeur(), 'a').execute();
                fenetre.refreshText();
                fenetre.refreshCursorHighlight();
            }
        }
    }

    // https://stackoverflow.com/a/418560/17288453
    private boolean isPrintableChar(char c) {
        Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
        return !Character.isISOControl(c) &&
            c != KeyEvent.CHAR_UNDEFINED &&
            block != null &&
            block != Character.UnicodeBlock.SPECIALS;
    }
}
