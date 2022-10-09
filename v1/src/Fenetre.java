package src;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.Highlighter.HighlightPainter;

import java.awt.*;

public class Fenetre {
    private Application application;

    private JTextArea textArea;

    private Highlighter highlighter;

    private HighlightPainter selectionPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY);
    private HighlightPainter cursorPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.DARK_GRAY);

    private Object selectionTag = null;
    private Object cursorTag = null;

    public Fenetre(Application application) {
        this.application = application;
        this.textArea = new JTextArea();
        this.highlighter = textArea.getHighlighter();
    }

    public Application getApplication() {
        return application;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public Highlighter getHighlighter() {
        return highlighter;
    }

    public HighlightPainter getSelectionPainter() {
        return selectionPainter;
    }

    public HighlightPainter getCursorPainter() {
        return cursorPainter;
    }

    public Object getSelectionTag() {
        return selectionTag;
    }

    public Object getCursorTag() {
        return cursorTag;
    }

    public void refreshText(){
        textArea.setText(application.getEditeur().getTexte().toString());
    }

    public void refreshSelectionHighlight(){
        Editeur editeur = application.getEditeur();
        if(selectionTag != null){
            highlighter.removeHighlight(selectionTag);
        }
        int off = editeur.getSelection().getOffset();
        int cur = editeur.getCurseur();
        try {
            if(off < 0){
                selectionTag = highlighter.addHighlight(cur+off, cur, selectionPainter);
            }
            else if(off > 0){
                selectionTag = highlighter.addHighlight(cur, cur+off, selectionPainter);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void refreshCursorHighlight(){
        Editeur editeur = application.getEditeur();
        if(cursorTag != null){
            highlighter.removeHighlight(cursorTag);
        }
        try {
            cursorTag = highlighter.addHighlight(editeur.getCurseur(), editeur.getCurseur(), cursorPainter);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void show(){
        JFrame frame = new JFrame("Notre éditeur de texte");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 480);
        frame.setLayout(new BorderLayout());
        initTextArea();
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void initTextArea(){
        textArea.setEditable(false);
        //set text area font to monospaced
        textArea.setFont(new Font("consolas", Font.PLAIN, 12));
        //hide the caret and the selection
        textArea.setCaretColor(Color.WHITE);
        textArea.setSelectionColor(Color.WHITE);
        textArea.addKeyListener(new Actionneur(application, this));
    }
}
