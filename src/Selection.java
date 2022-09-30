import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.awt.event.KeyEvent;
public class Selection {
    private CharacterIterator start;
    private CharacterIterator end;
    private CharacterIterator left;
    private CharacterIterator right;
    public Selection(StringBuffer sb) {
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

    public void keyTypedLeft(KeyEvent event) {
        if (event.getKeyChar() == KeyEvent.VK_LEFT) {
            if (left.getIndex() != start.getIndex()) {
                left.previous();
            }
        }
        if (event.getKeyChar() == KeyEvent.VK_RIGHT) {
            if (left.getIndex() != end.getIndex()) {
                left.next();
                if (left.getIndex() > right.getIndex()) {
                    CharacterIterator it = (CharacterIterator) left.clone();
                    left = right;
                    right = it;
                }
            }
        }
    }

    public void keyTypedRight(KeyEvent event) {
        if (event.getKeyChar() == KeyEvent.VK_LEFT) {
            if (right.getIndex() != start.getIndex()) {
                right.previous();
                if (left.getIndex() > right.getIndex()) {
                    CharacterIterator it = (CharacterIterator) left.clone();
                    left = right;
                    right = it;
                }
            }
        }
        if (event.getKeyChar() == KeyEvent.VK_RIGHT) {
            if (left.getIndex() != end.getIndex()) {
                right.next();
            }
        }
    }
}