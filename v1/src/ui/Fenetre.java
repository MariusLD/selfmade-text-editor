package ui;

import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.text.Highlighter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;

import main.Application;
import main.Editeur;

/**
 * Classe au centre de l'application.
 * Elle gère l'affichage.
 */
public class Fenetre {

    private Application application;
    private JTextArea textArea = new JTextArea();

    private Highlighter highlighter = textArea.getHighlighter();

    private HighlightPainter selectionPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY);
    private HighlightPainter cursorPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.DARK_GRAY);

    private Object selectionTag = null;
    private Object cursorTag = null;

    private Actionneur actionneur;

    /**
     * Constructeur de la classe Fenetre.
     * 
     * @param application l'application.
     */
    public Fenetre(Application application) {
        this.application = application;
        this.actionneur = new InputActionneur(this);
    }

    /**
     * Permet d'obtenir l'application.
     * 
     * @return l'application.
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Permet d'obtenir la zone de texte.
     * 
     * @return la zone de texte.
     */
    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * Permet d'obtenir le highlighter.
     * 
     * @return le highlighter.
     */
    public Highlighter getHighlighter() {
        return highlighter;
    }

    /**
     * Permet d'obtenir le painter de la sélection.
     * 
     * @return le painter de la sélection.
     */
    public HighlightPainter getSelectionPainter() {
        return selectionPainter;
    }

    /**
     * Permet d'obtenir le painter du curseur.
     * 
     * @return le painter du curseur.
     */
    public HighlightPainter getCursorPainter() {
        return cursorPainter;
    }

    /**
     * Permet d'obtenir le tag de la sélection.
     * 
     * @return le tag de la sélection.
     */
    public Object getSelectionTag() {
        return selectionTag;
    }

    /**
     * Permet d'obtenir le tag du curseur.
     * 
     * @return le tag du curseur.
     */
    public Object getCursorTag() {
        return cursorTag;
    }

    /**
     * Permet d'obtenir l'actionneur.
     * 
     * @return l'actionneur.
     */
    public Actionneur getActionneur() {
        return actionneur;
    }

    /**
     * Permet de changer l'actionneur.
     * 
     * @param actionneur le nouvel actionneur.
     */
    public void setActionneur(Actionneur actionneur) {
        textArea.removeKeyListener(this.actionneur);
        this.actionneur = actionneur;
        textArea.addKeyListener(actionneur);
    }

    /**
     * Permet de mettre à jour l'affichage du texte.
     */
    public void refreshText() {
        textArea.setText(application.getEditeur().getTexte().toString());
        refreshCursorHighlight();
    }

    /**
     * Permet de mettre à jour l'affichage de la sélection.
     */
    public void refreshSelectionHighlight() {
        Editeur editeur = application.getEditeur();
        if (selectionTag != null) {
            highlighter.removeHighlight(selectionTag);
        }
        int off = editeur.getSelection().getOffset();
        int cur = editeur.getCurseur();
        try {
            if (off < 0) {
                selectionTag = highlighter.addHighlight(cur + off, cur, selectionPainter);
            } else if (off > 0) {
                selectionTag = highlighter.addHighlight(cur, cur + off, selectionPainter);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de mettre à jour l'affichage du curseur.
     */
    public void refreshCursorHighlight() {
        Editeur editeur = application.getEditeur();
        if (cursorTag != null) {
            highlighter.removeHighlight(cursorTag);
        }
        try {
            cursorTag = highlighter.addHighlight(editeur.getCurseur(), editeur.getCurseur(), cursorPainter);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche la fenêtre.
     */
    public void show() {
        JFrame frame = new JFrame("Notre éditeur de texte");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 480);
        frame.setLayout(new BorderLayout());
        initTextArea();
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * Initialise la zone de texte.
     */
    private void initTextArea() {
        // On désactive l'édition par défaut de la zone de texte.
        textArea.setEditable(false);
        textArea.setFont(new Font("consolas", Font.PLAIN, 12));
        // On cache le curseur et la sélection par défaut de la zone de texte.
        textArea.setCaretColor(Color.WHITE);
        textArea.setSelectionColor(Color.WHITE);

        textArea.addKeyListener(actionneur);
    }
}
