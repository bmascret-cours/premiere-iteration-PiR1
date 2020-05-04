package model;

public class Tour extends AbstractPiece {

	
	public Tour(Couleur couleur_de_piece, Coord coord) {
		super("Tour", couleur_de_piece, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		if((this.getX() - xFinal) * (this.getY() - yFinal) == 0 ) {
			return true;
		}
		else {
			return false;
		}
	}

}
