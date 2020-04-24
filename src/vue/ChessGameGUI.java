package vue;

import controler.ChessGameControlers;
import controler.controlerLocal.ChessGameControler;
import model.Coord;
import model.PieceIHM;
import tools.ChessImageProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer {

    ChessGameControlers chessGameControler;
    Dimension dim;
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    Coord Init;
    Coord initalPosition;
    Boolean pieceSelected = false;

    public ChessGameGUI(String titre, ChessGameControlers chessGameControler, Dimension dim) {
        this.chessGameControler = chessGameControler;
        this.dim = dim;
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(this.dim);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(this.dim);
        chessBoard.setBounds(0, 0, this.dim.width, this.dim.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground(i % 2 == 0 ? Color.black : Color.white);
            else
                square.setBackground(i % 2 == 0 ? Color.white : Color.black);
        }
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(mouseEvent.getX(), mouseEvent.getY());


        if (c instanceof JPanel)
            return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - mouseEvent.getX();
        yAdjustment = parentLocation.y - mouseEvent.getY();
        chessPiece = (JLabel) c;
        chessPiece.setLocation(mouseEvent.getX() + xAdjustment, mouseEvent.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);

            this.initalPosition = new Coord(mouseEvent.getX() + xAdjustment, mouseEvent.getY() + yAdjustment);

    }
//TODO : bug duplication
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (chessPiece == null) return;

        chessPiece.setVisible(false);
        Component c = chessBoard.findComponentAt(mouseEvent.getX(), mouseEvent.getY());

        if (this.chessGameControler.move(getPieceCoord(this.initalPosition.x, this.initalPosition.y), getPieceCoord(c.getX(), c.getY()))){
            if (c instanceof JLabel) {
                Container parent = c.getParent();
                parent.remove(0);
                parent.add(chessPiece);
            } else {
                Container parent = (Container) c;
                parent.add(chessPiece);
            }
        }
        else
        {
            chessPiece.setLocation(this.initalPosition.x, this.initalPosition.y);
        }
        chessPiece.setVisible(true);
    }

    private Coord getPieceCoord(int x, int y) {
        return new Coord(x/(700/8), y/(700/8));
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (chessPiece == null) return;
        chessPiece.setLocation(mouseEvent.getX() + xAdjustment, mouseEvent.getY() + yAdjustment);

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void update(Observable observable, Object o) {

        List<PieceIHM> piecesIHM = (List<PieceIHM>) o;

        for (PieceIHM pieceIHM : piecesIHM) {
            for (Coord coord : pieceIHM.getList()) {
                JLabel piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile(pieceIHM.getTypePiece(), pieceIHM.getCouleur())));
                JPanel panel = (JPanel) chessBoard.getComponent(coord.x + 8 * coord.y);
                panel.add(piece);
            }
        }
    }
}
