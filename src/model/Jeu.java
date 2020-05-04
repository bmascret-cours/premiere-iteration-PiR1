package model;

import java.util.List;
import java.util.LinkedList;

public class Jeu {
    Couleur color;
    List<Pieces> pieces;
    private boolean isCapturePossible = false;

    boolean rockKing;

    public Jeu(Couleur color) {
        this.color = color;
        this.pieces = tools.ChessPiecesFactory.newPieces(color);
        this.rockKing = false;
    }

    private Pieces findPiece(int x, int y) {
        Pieces foundPiece = null;
        for (Pieces p : this.pieces) {
            if (p.getX() == x && p.getY() == y) {
                foundPiece = p;
            }
        }
        return foundPiece;
    }

    public boolean isPieceHere(int x, int y) {
        if (findPiece(x, y) == null) {
            return false;
        }
        return true;
    }

    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
        Pieces p = findPiece(xInit, yInit);
        if (p == null) {
            return false;
        }
        return p.isMoveOk(xFinal, yFinal);
    }

    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        Pieces p = findPiece(xInit, yInit);
        if (p == null) {
            return false;
        }
        return p.move(xFinal, yFinal);
    }

    //ToDo
    public void setPossibleCapture() {
        isCapturePossible = true;
        return;
    }

    //ToDo
    boolean	capture(int xCatch, int yCatch) {
        boolean capture = false;
        Pieces piece = findPiece(xCatch, yCatch);
        if(piece != null) {
            capture = piece.capture();
        }
        return capture;
    }

    public String toString() {
        String str = "";
        for (Pieces p : this.pieces) {
            str += p.toString() + "\n";
        }
        return str;
    }

    public Couleur getPieceColor(int x, int y) {
        Pieces p = findPiece(x, y);
        if(p != null){
            return p.getCouleur();
        }
        return Couleur.NOIRBLANC;
    }

    public String getPieceType(int x, int y) {
        Pieces p = findPiece(x, y);
        if(p != null)
            return p.getClass().getSimpleName();
        return null;
    }

    public Couleur getCouleur() {
        return this.color;
    }

    public List<PieceIHM> getPiecesIHM() {
        PieceIHM newPieceIHM = null;
        List<PieceIHM> list = new LinkedList<PieceIHM>();
        for (Pieces piece : pieces) {
            boolean existe = false;
            // si le type de piece existe déjà dans la liste de PieceIHM
            // ajout des coordonnées de la pièce dans la liste de Coord de ce type
            // si elle est toujours en jeu (x et y != -1)
            for (PieceIHM pieceIHM : list) {
                if ((pieceIHM.getTypePiece()).equals(piece.getClass().getSimpleName())) {
                    existe = true;
                    if (piece.getX() != -1) {
                        pieceIHM.add(new Coord(piece.getX(), piece.getY()));
                    }
                }
            }
            // sinon, création d'une nouvelle PieceIHM si la pièce est toujours en jeu
            if (!existe) {
                if (piece.getX() != -1) {
                    newPieceIHM = new PieceIHM(piece.getClass().getSimpleName(),
                            piece.getCouleur());
                    newPieceIHM.add(new Coord(piece.getX(), piece.getY()));
                    list.add(newPieceIHM);
                }
            }
        }
        return list;
    }

    public void setCastling() {
        this.rockKing = true;
    }

    //ToDo
    public void undoMove() {

    }

    //ToDo
    public void undoCapture() {

    }

    public boolean isPawnPromotion(int x, int y) {
        Pieces p = findPiece(x, y);
        if (getPieceType(x, y) == "Pion") {
            if ((p.getCouleur().equals(Couleur.BLANC) && p.getY() == 0) || (p.getCouleur().equals(Couleur.NOIR) && p.getX() == 7)) {
                return true;
            }
        }
        return false;
    }

    //ToDo
    public boolean pawnPromotion(int x, int y, String type) {
        if (isPawnPromotion(x, y)) {
            Pieces p = findPiece(x, y);
        }
        return false;
    }

    public Coord getKingCoord() {
        for (Pieces p : this.pieces) {
            if (p.getClass().getSimpleName().equals("Roi")) {
                return new Coord(p.getX(), p.getY());
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Jeu wl = new Jeu(Couleur.BLANC);
        Jeu bl = new Jeu(Couleur.NOIR);
        System.out.println(wl);

        System.out.println(bl);
        System.out.println(wl.move(4, 6, 4, 4));
        System.out.println(wl.move(4, 4, 4, 3));
        System.out.println(wl.isPieceHere(4, 3));
    }
}
