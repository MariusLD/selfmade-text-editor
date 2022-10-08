import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.awt.event.KeyEvent;
public class Selection {
    private CharacterIterator start;
    private CharacterIterator end;
    private CharacterIterator left;
    private CharacterIterator right;
    public Selection(StringBuffer sb) {
        refresh(sb);
    }

    public void refresh(StringBuffer sb) {
        start = new StringCharacterIterator(sb.toString());
        start.first();
        end = new StringCharacterIterator(sb.toString());
        end.last();
        left = new StringCharacterIterator(sb.toString());
        right = new StringCharacterIterator(sb.toString());
    }

    public void setLeft(CharacterIterator left) {
        this.left = left;
    }

    public void setRight(CharacterIterator right) {
        this.right = right;
    }

    public CharacterIterator getLeft() {
        return left;
    }

    public CharacterIterator getRight() {
        return right;
    }

    public boolean keyTypedLeft(String s) {
        if (s.equals("P")) {
            if (left.getIndex() != start.getIndex()) {
                left.previous();
            }
            return false;
        } else if (s.equals("N")) {
            if (left.getIndex() != end.getIndex()) {
                left.next();
                if (left.getIndex() > right.getIndex()) {
                    CharacterIterator it = (CharacterIterator) left.clone();
                    left = right;
                    right = it;
                }
            }
            return false;
        } else if (s.equals("D")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean keyTypedRight(String s) {
        if (s.equals("P")) {
            if (right.getIndex() != start.getIndex()) {
                right.previous();
                if (left.getIndex() > right.getIndex()) {
                    CharacterIterator it = (CharacterIterator) left.clone();
                    left = right;
                    right = it;
                }
            }
            return false;
        } else if (s.equals("N")) {
            if (right.getIndex() != end.getIndex()) {
                right.next();
            }
            return false;
        } else if (s.equals("D")) {
            return true;
        } else {
            return false;
        }
    }

    public void affichageDroite(StringBuffer sb) {
        System.out.println(sb.toString());
        for (int i = 0; i < right.getIndex(); i++) {
            System.out.print(" ");
        }
        System.out.println("^");
    }

    public void affichageGauche(StringBuffer sb) {
        System.out.println(sb.toString());
        for (int i = 0; i < left.getIndex(); i++) {
            System.out.print(" ");
        }
        System.out.println("^");
    }
}