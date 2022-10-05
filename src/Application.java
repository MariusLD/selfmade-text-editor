import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Scanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application  {
    private static Editeur editeur;
    private Actionneur actionneur;
    private static String clipboard;

    private static Command copie;
    private static Command colle;

    private void show() {
        JFrame f = new JFrame("Notre éditeur de texte");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ApplicationPannel ap = new ApplicationPannel();
        f.add(ap);

        f.setContentPane(ap);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        f.setVisible(true);
        f.setSize(600,600);
    }

    public Application() {
        editeur = new Editeur();
        actionneur = new Actionneur();
        clipboard = new String();
        copie = new Copier(this, editeur);
        colle = new Coller(this, editeur);
    }

    public String getClipboard() {
        return clipboard;
    }

    public void setClipboard(String texte) {
        clipboard = texte;
    }

    private static class ApplicationPannel extends JPanel implements KeyListener {
        Scanner sc = new Scanner(System.in);
        JTextField textField = new JTextField();
        public ApplicationPannel() {
            this.addKeyListener(this);
            this.setFocusable(true);
            this.requestFocusInWindow();
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            textField.setEditable(false);
            this.add(textField);
            textField.setText("");
            JPanel jp = new JPanel();
            JButton ctrlC = new JButton("Ctrl+C");
            ctrlC.addActionListener(e -> {
                System.out.println("Choisis la borne droite de la limite de copie, P pour previous et N pour next D pour done");
                String temp = "";
                while (!editeur.getCurseur().keyTypedRight(temp)) {
                    editeur.getCurseur().affichageDroite(editeur.getBuffer());
                    temp = sc.nextLine();
                }
                System.out.println("Choisis la borne gauche de la limite de copie, P pour previous et N pour next D pour done");
                temp = "";
                while (!editeur.getCurseur().keyTypedLeft(temp)) {
                    editeur.getCurseur().affichageGauche(editeur.getBuffer());
                    temp = sc.nextLine();
                }
                copie.execute();
            });
            JButton ctrlX = new JButton("Ctrl+X");
            JButton ctrlV = new JButton("Ctrl+V");
            ctrlV.addActionListener(e ->  {
                colle.execute();
                textField.setText(editeur.getBuffer() + clipboard);
            });
            JButton ctrlZ = new JButton("Ctrl+Z");
            jp.add(ctrlC);
            jp.add(ctrlX);
            jp.add(ctrlV);
            jp.add(ctrlZ);
            this.add(jp);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            editeur.setBuffer(String.valueOf(e.getKeyChar()));
            textField.setText(editeur.getBuffer().toString());
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Application().show();
            }
        });
    }
}