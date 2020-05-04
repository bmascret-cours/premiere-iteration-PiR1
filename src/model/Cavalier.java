package model;

public class Cavalier extends AbstractPiece {


    public Cavalier(Couleur couleur_de_piece, Coord coord) {
        super("Cavalier", couleur_de_piece, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        if ( Math.abs(this.getX() - xFinal) == 1 && Math.abs(this.getY() - yFinal) == 2 ) {
            return true;
        }
        else if ( Math.abs(this.getX() - xFinal) == 2 && Math.abs(this.getY() - yFinal) == 1 ) {
            return true;
        }
        return false;
    }
}
