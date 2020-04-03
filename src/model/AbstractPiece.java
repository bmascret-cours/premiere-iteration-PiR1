package model;


public abstract class AbstractPiece implements Pieces {

	private String name;
	private Couleur couleur;
	private Coord coord;
	
	
	public AbstractPiece (String name, Couleur couleur, Coord coord) {
		this.couleur = couleur;
		this.coord = coord;
		this.name=name;
	}
	
	public int getX() {
		return this.coord.x;
	}
	
	public int getY() {
		return this.coord.y;
	}
	
	public Couleur getCouleur() {
		return this.couleur;
	}
	
	public boolean move(int x, int y) {
		if (this.coord.coordonnees_valides(x, y) && (this.coord.x != x || this.coord.y !=y) && this.isMoveOk(x,y)) {
			this.coord.x=x;
			this.coord.y=y;
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean capture() {
		this.coord.x = -1;
		this.coord.y = -1;
		return true;
	}
	
	public java.lang.String toString(){
		return this.name+", x:"+this.coord.x+" y:"+this.coord.y;
	}
	
	public abstract boolean isMoveOk(int xFinal, int yFinal);
	
	public static void main(String[] args) {
		Pieces maTour = new Pion(Couleur.NOIR, new Coord(0,0));
		System.out.println(maTour.toString());
		maTour.move(4,1);
		System.out.println(maTour.toString());
		maTour.move(0,2);
		System.out.println(maTour.toString());
		maTour.move(0,3);
		System.out.println(maTour.move(0,2));
	}
}
