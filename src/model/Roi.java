package model;

public class Roi extends AbstractPiece {

    public Roi(Couleur couleur_de_piece, Coord coord) {
        super("Roi", couleur_de_piece, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        return Math.abs(this.getX() - xFinal)==1 && Math.abs(this.getY() - yFinal)==1;
    }
}
