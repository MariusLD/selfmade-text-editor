package main;

import utilitaire.Direction;

/**
 * Classe au centre de l'application.
 * Elle gère le buffer, le curseur et la sélection.
 */
public class Editeur {

    private StringBuffer texte = new StringBuffer();
    private int curseur = 0;
    private Selection selection = new Selection();

    /**
     * Permet d'obtenir le buffer.
     * 
     * @return le buffer.
     */
    public StringBuffer getTexte() {
        return texte;
    }

    /**
     * Permet d'obtenir le curseur.
     * 
     * @return le curseur.
     */
    public int getCurseur() {
        return curseur;
    }

    /**
     * Permet d'obtenir la sélection.
     * 
     * @return la sélection.
     */
    public Selection getSelection() {
        return selection;
    }

    /**
     * Permet de définir le curseur.
     * 
     * @param curseur la nouvelle position du curseur.
     */
    public void setCurseur(int curseur) {
        this.curseur = curseur;
    }

    /**
     * Permet de définir le buffer.
     * 
     * @param texte le nouveau buffer.
     */
    public void setTexte(StringBuffer texte) {
        this.texte = texte;
    }

    /**
     * Permet d'ajouter un caractère au buffer.
     * L'ajout se fait à la position du curseur.
     * 
     * @param c le caractère à ajouter.
     */
    public void writeChar(char c) {
        texte.insert(curseur, c);
        curseur++;
    }

    /**
     * Permet de supprimer un caractère du buffer.
     * 
     * @param direction la direction de la suppression.
     */
    public void deleteChar(Direction direction) {
        if (direction == Direction.GAUCHE && curseur > 0) {
            texte.deleteCharAt(curseur - 1);
            curseur--;
        } else if (direction == Direction.DROITE && curseur < texte.length()) {
            texte.deleteCharAt(curseur);
        }
    }

    /**
     * Permet de déplacer le curseur.
     * 
     * @param direction la direction du déplacement.
     */
    public void moveCurseur(Direction direction) {
        if (!emptySelection()) {
            resetSelection();
        }
        if (direction == Direction.GAUCHE && curseur > 0) {
            curseur--;
        } else if (direction == Direction.DROITE && curseur < texte.length()) {
            curseur++;
        } else if (direction == Direction.HAUT) {
            int i = curseur - 1;
            while (i >= 0 && texte.charAt(i) != '\n') {
                i--;
            }
            if (i >= 0) {
                curseur = i;
            } else {
                curseur = 0;
            }
        } else if (direction == Direction.BAS) {
            int i = curseur;
            while (i < texte.length() && texte.charAt(i) != '\n') {
                i++;
            }
            if (i < texte.length()) {
                curseur = i + 1;
            } else {
                curseur = texte.length();
            }
        }
    }

    /**
     * Permet de déplacer la selection.
     * 
     * @param direction la direction du déplacement.
     */
    public void moveSelection(Direction direction) {
        if (direction == Direction.GAUCHE && curseur + selection.getOffset() > 0) {
            selection.setOffset(selection.getOffset() - 1);
        } else if (direction == Direction.DROITE && curseur + selection.getOffset() < texte.length()) {
            selection.setOffset(selection.getOffset() + 1);
        } else if (direction == Direction.HAUT) {
            int i = selection.getOffset() - 1;
            while (curseur + i >= 0 && texte.charAt(curseur + i) != '\n') {
                i--;
            }
            if (curseur + i >= 0) {
                selection.setOffset(i);
            } else {
                selection.setOffset(-curseur);
            }
        } else if (direction == Direction.BAS) {
            int i = selection.getOffset() + 1;
            while (curseur + i < texte.length() && texte.charAt(curseur + i) != '\n') {
                i++;
            }
            if (curseur + i < texte.length()) {
                selection.setOffset(i + 1);
            } else {
                selection.setOffset(texte.length() - curseur);
            }
        }
    }

    /**
     * Réinitialise la sélection.
     */
    public void resetSelection() {
        selection.setOffset(0);
    }

    /**
     * Permet de savoir si la sélection est vide.
     * 
     * @return true si la sélection est vide, false sinon.
     */
    public boolean emptySelection() {
        return selection.getOffset() == 0;
    }

    /**
     * Permet d'obtenir le texte sélectionné.
     * 
     * @return le texte sélectionné.
     */
    public String getSelectedText() {
        int off = selection.getOffset();
        if (off < 0) {
            return texte.substring(curseur + off, curseur);
        } else {
            return texte.substring(curseur, curseur + off);
        }
    }

    /**
     * Permet de remplacer le texte sélectionné par un autre texte.
     * 
     * @param replacement le texte de remplacement.
     */
    public void setSelectedText(String replacement) {
        int off = selection.getOffset();
        if (off < 0) {
            this.texte.replace(curseur + off, curseur, replacement);
            curseur += off + replacement.length();
        } else {
            this.texte.replace(curseur, curseur + off, replacement);
            curseur += replacement.length();
        }
    }

    /**
     * Permet de supprimer le texte sélectionné.
     */
    public void removeSelectedText() {
        int off = selection.getOffset();
        if (off < 0) {
            this.texte.delete(curseur + off, curseur);
            curseur += off;
        } else {
            this.texte.delete(curseur, curseur + off);
        }
    }

    /**
     * Permet de créer une sauvegarde de l'état.
     * 
     * @return la sauvegarde.
     */
    public Snapshot createSnapshot() {
        return new Snapshot(new StringBuffer(texte), curseur);
    }

    /**
     * Permet de restaurer l'état à partir d'une sauvegarde.
     * 
     * @param snapshot la sauvegarde.
     */
    public void restoreSnapshot(Snapshot snapshot) {
        this.texte = snapshot.getTexte();
        this.curseur = snapshot.getCurseur();
    }
}
