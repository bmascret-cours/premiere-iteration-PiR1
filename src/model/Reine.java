package model;

public class Reine extends AbstractPiece {

    public Reine(Couleur couleur_de_piece, Coord coord) {
        super("Reine", couleur_de_piece, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        if (Math.abs(xFinal - this.getX()) == Math.abs(yFinal - this.getY())) {
            return true;
        }
        else if ((this.getX() - xFinal) * (this.getY() - yFinal) == 0 ) {
            return true;
        }
        return false;
    }
}
