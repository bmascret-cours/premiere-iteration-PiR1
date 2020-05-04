package model;

public class Pion extends AbstractPiece implements Pions{

    boolean hasMoved = false;

    public Pion(Couleur couleur_de_piece, Coord coord){
        super("Pion", couleur_de_piece, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        if(Coord.coordonnees_valides(xFinal,yFinal) && this.getX() == xFinal) {
            if (this.getCouleur().equals(Couleur.BLANC)) {
                if (yFinal == (this.getY() - 1)) {
                    return true;
                } else if (!hasMoved && yFinal == this.getY() - 2) {
                    return true;
                }
            } else {
                if (yFinal == this.getY() + 1) {
                    return true;
                } else if (!hasMoved && yFinal == this.getY() + 2) {
                    return true;
                }
            }
        }
        return  false;

    }

    @Override
    public boolean move(int xFinal, int yFinal) {
        boolean moveRet = super.move(xFinal, yFinal);
        if(moveRet)
            hasMoved = true;
        return moveRet;
    }

    @Override
    public boolean isMoveDiagOk(int xFinal, int yFinal) {
        return false;
    }
}
