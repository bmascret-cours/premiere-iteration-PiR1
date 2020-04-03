package model;
import java.util.List;

public class Echiquier implements BoardGames{

    Jeu Whites;
    Jeu Blacks;
    Jeu Current;


    public Echiquier(){
        Whites = new Jeu(Couleur.BLANC);
        Blacks = new Jeu(Couleur.NOIR);
        Current = Whites;
    }

    public List<PieceIHM> getPiecesIHM(){
        return null;
    }

    @Override
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public String getMessage(){
        return "";
    }

    @Override
    public Couleur getColorCurrentPlayer() {
        return null;
    }

    @Override
    public Couleur getPieceColor(int x, int y) {
        return null;
    }

    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal){
        return false;
    }

    public void switchJoueur(){
        return;
    }
}
