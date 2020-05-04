package model;
import java.util.List;

public class Echiquier implements BoardGames{

    private Jeu Whites;
    private Jeu Blacks;
    private Jeu Current;

    private String message;


    public Echiquier(){
        Whites = new Jeu(Couleur.BLANC);
        Blacks = new Jeu(Couleur.NOIR);
        Current = Whites;
        this.message="";
    }

    public List<PieceIHM> getPiecesIHM() {
        List<PieceIHM> listWhite = this.Whites.getPiecesIHM();
        List<PieceIHM> listBlack = this.Blacks.getPiecesIHM();
        listWhite.addAll(listBlack);
        return listWhite;
    }

    @Override
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        if (this.isMoveOk(xInit, yInit, xFinal, yFinal)){
            this.Current.move(xInit, yInit, xFinal, yFinal);
            this.setMessage("Déplacement Ok");
            return true;
        }
        this.setMessage("Déplacement interdit");
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public String getMessage(){
        return this.message;
    }
    private void setMessage(String msg){
        this.message=msg;
    }


    @Override
    public Couleur getColorCurrentPlayer() {
        return this.Current.getCouleur();
    }

    @Override
    public Couleur getPieceColor(int x, int y) {
        Couleur color = this.Blacks.getPieceColor(x, y);
        if(color == Couleur.NOIRBLANC) {
            color = this.Whites.getPieceColor(x, y);
        }
        return Couleur.BLANC;
    }


    // ToDo
    // Vérification pièce intermédiaire
    // Si pièce à position finale ->
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal){
        boolean ret=false;
        this.setMessage("Déplacement interdit");
        // S'il y a bien une pièce à l'endroit indiqué
        if (this.Current.isPieceHere(xInit, yInit)){
            //Si déplacement possible
            if(this.Current.isMoveOk(xInit,yInit, xFinal, yFinal)){
                //Si les coordonnées sont dans le plateau
                if(xFinal < 8 && xFinal >= 0 && yFinal < 8 && yFinal >= 0) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    public void switchJoueur(){
        if(this.Current == this.Whites){
            this.Current = this.Blacks;
        }
        else{
            this.Current = this.Whites;
        }
        return;
    }

    public String toString() {
        return this.Whites.toString() + "\n" + this.Blacks.toString();
    }

    public static void main(String[] args) {
        Echiquier e = new Echiquier();
        System.out.println(e.getPieceColor(4,6));
        System.out.println(e.isMoveOk(0, 6, 0, 4));
        System.out.println(e.move(3, 6, 3, 4));
        System.out.println(e.getMessage());
        e.move(3,4,3,6);
        System.out.println(e.getMessage());
        System.out.println(e.toString());
        e.switchJoueur();
        System.out.println(e.getColorCurrentPlayer());
        System.out.println(e.move(0,4,0,3));
        System.out.println(e.getMessage());

        System.out.println(e.toString());
    }
}