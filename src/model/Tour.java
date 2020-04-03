package model;

public class Tour extends AbstractPiece {
	
	public Couleur couleur_de_piece;
	public Coord coord;
	
	public Tour(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		if(this.coord.x - xFinal == 0 ^ this.coord.y - yFinal == 0 ) {
			return true;
		}
		else {
			return false;
		}
	}

}
