package controller;
import listener.GameListener;
import model.Constant;
import model.PlayerColor;
import model.Chessboard;
import model.ChessboardPoint;
import view.CellComponent;
import view.ElephantChessComponent;
import view.ChessboardComponent;
import view.ChessGameFrame;


/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and onPlayerClickChessPiece()]
 *
*/
public class GameController implements GameListener {


    private Chessboard model;
    private ChessboardComponent view;
    private PlayerColor currentPlayer;
    private ChessGameFrame frame;

    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;
    public static int TurnNumber;

    public GameController(ChessboardComponent view, Chessboard model, ChessGameFrame frame) {
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;
        this.frame = frame;
        view.registerController(this);
        view.initiateChessComponent(model);
        view.repaint();
        setTurnNumber(1);
        frame.addSaveButton(model);

    }

    public ChessGameFrame getFrame() {
        return frame;
    }

    public ChessboardComponent getView() {
        return view;
    }

    public static void setTurnNumber(int turnNumber) {
        TurnNumber = turnNumber;
    }

    public void setCurrentPlayer(PlayerColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Chessboard getModel() {
        return model;
    }


    // after a valid move swap the player
    private void swapColor() {
        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;
        TurnNumber ++;
    }

    private boolean win() {
        // TODO: Check the board if there is a winner
        boolean win = false;
        boolean NoRed = true;
        boolean NoBlue = true;
        ChessboardPoint redCave = new ChessboardPoint(0,3);
        ChessboardPoint blueCave = new ChessboardPoint(8, 3);
        if(model.getGrid()[redCave.row()][redCave.col()].getPiece() != null){
            if(model.getChessPieceOwner(redCave) == PlayerColor.BLUE){
            System.out.print("Blue wins!");
            win = true;
        }
        }
        if(model.getGrid()[blueCave.row()][blueCave.col()].getPiece() != null){
            if(model.getChessPieceOwner(blueCave) == PlayerColor.RED){
            System.out.print("Red wins!");
            win = true;
        }
        }
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(model.getGrid()[i][j].getPiece() != null){
                    if(model.getGrid()[i][j].getPiece().getOwner() == PlayerColor.BLUE){
                      NoBlue = false;
                    }
                    if(model.getGrid()[i][j].getPiece().getOwner() == PlayerColor.RED){
                      NoRed = false;
                    }
                }
            }
        }
        if(NoBlue){
            System.out.print("Red wins!");
            win = true;
        }
        if(NoRed){
            System.out.print("Blue wins!");
            win = true;
        }
        return win;
    }


    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {//视图中的移动
        if (selectedPoint != null && model.isValidMove(selectedPoint, point)) {
            model.moveChessPiece(selectedPoint, point);
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
            selectedPoint = null;
            swapColor();
            frame.addTurn();
            frame.ColorSwitch();
            view.repaint();
            if(point.toString().equals("(0,2)") | point.toString().equals("(0,4)") |
               point.toString().equals("(1,3)")){
              if(model.getChessPieceOwner(point) == PlayerColor.BLUE){
                  System.out.print(model.getChessPieceOwner(point) + " " + model.getChessPieceAt(point).getName() +
                          " has entered the trap!");
                  model.getGrid()[point.row()][point.col()].getPiece().setTrapped(true);
              }
            }
            if(point.toString().equals("(8,2)") | point.toString().equals("(8,4)") |
               point.toString().equals("(7,3)")){
                if(model.getChessPieceOwner(point) == PlayerColor.RED){
                    System.out.print(model.getChessPieceOwner(point) + " " + model.getChessPieceAt(point).getName() +
                            " has entered the trap!");
                    model.getGrid()[point.row()][point.col()].getPiece().setTrapped(true);
                }
            }
            if(win()){
                System.exit(0);
            }
            // TODO: if the chess enter Dens or Traps and so on
        }
    }
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, ElephantChessComponent component) {//视图中的吃棋子（未完成）
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        } else if (model.isValidMove(selectedPoint, point) && model.isValidCapture(selectedPoint, point)) {
            model.captureChessPiece(selectedPoint, point);
            view.removeChessComponentAtGrid(point);
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
            selectedPoint = null;
            swapColor();
            frame.ColorSwitch();
            frame.addTurn();
            view.repaint();
        }
        if(win()){
            System.exit(0);
        }
    }

}
