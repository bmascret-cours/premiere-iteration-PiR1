package model;

public class Fou extends AbstractPiece {

    public Fou(Couleur couleur_de_piece, Coord coord) {
        super("Fou", couleur_de_piece, coord);
    }


    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        return (Math.abs(xFinal - this.getX()) == Math.abs(yFinal - this.getY()));
    }

}
