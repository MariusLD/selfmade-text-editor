import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Application implements KeyListener{
    private Editeur editeur;
    private Actionneur actionneur;
    private String clipboard;

    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void GUI() {
        JFrame frame = new JFrame("Notre éditeur de texte");
        JPanel content = new JPanel();
        JTextArea textField;
        frame.setContentPane(content);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        textField = new JTextArea();
        textField.setLineWrap(true);
        content.add(textField);
        JPanel jp = new JPanel();
        JButton ctrlC = new JButton("Ctrl+C");
        JButton ctrlX = new JButton("Ctrl+X");
        JButton ctrlV = new JButton("Ctrl+V");
        JButton ctrlZ = new JButton("Ctrl+Z");
        jp.add(ctrlC);
        jp.add(ctrlX);
        jp.add(ctrlV);
        jp.add(ctrlZ);
        content.add(jp);
        frame.setSize(450, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public Application(){
        editeur = new Editeur();
        actionneur = new Actionneur();
        clipboard = new String();
    }

    public String getClipboard() {
        return clipboard;
    }

    public void setClipboard(String texte) {
        clipboard = texte;
    }
}