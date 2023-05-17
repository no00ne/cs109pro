package model;
import java.util.ArrayList;
/**
 * This class store the real chess information.
 * The Chessboard has 9*7 cells, and each cell has a position for chess
 */
public class Chessboard {
    private Cell[][] grid;

    public Chessboard() {
        this.grid =
                new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];//19X19

        initGrid();
        initPieces();
    }

    private void initGrid() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j] = new Cell();
            }
        }
    }
    private void initPieces() {//棋子初始化
        grid[6][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant",8));
        grid[2][6].setPiece(new ChessPiece(PlayerColor.RED, "Elephant",8));
        grid[2][0].setPiece(new ChessPiece(PlayerColor.RED, "Mouse", 1));
        grid[6][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Mouse", 1));
        grid[8][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion",7));
        grid[0][0].setPiece(new ChessPiece(PlayerColor.RED, "Lion",7));
        grid[0][6].setPiece(new ChessPiece(PlayerColor.RED, "Tiger",6));
        grid[8][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger",6));
        grid[2][2].setPiece(new ChessPiece(PlayerColor.RED, "Leopard",5));
        grid[6][4].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard",5));
        grid[2][4].setPiece(new ChessPiece(PlayerColor.RED, "Wolf",4));
        grid[6][2].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf",4));
        grid[1][1].setPiece(new ChessPiece(PlayerColor.RED, "Dog",3));
        grid[7][5].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog",3));
        grid[1][5].setPiece(new ChessPiece(PlayerColor.RED, "Cat",2));
        grid[7][1].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat",2));
    }
    private ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    private Cell getGridAt(ChessboardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }

    private int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    private ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }

    private void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);
    }

    public void moveChessPiece(ChessboardPoint src, ChessboardPoint dest) {//移动棋子
        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal chess move!");
        }
        setChessPiece(dest, removeChessPiece(src));
    }

    public void captureChessPiece(ChessboardPoint src, ChessboardPoint dest) {//吃棋子（未完成）
        if (isValidCapture(src, dest)) {
            getGridAt(dest).removePiece();
            setChessPiece(dest, removeChessPiece(src));
        }
        // TODO: Finish the method.
    }

    public Cell[][] getGrid() {
        return grid;
    }
    public PlayerColor getChessPieceOwner(ChessboardPoint point) {
        return getGridAt(point).getPiece().getOwner();
    }

    public boolean isValidMove(ChessboardPoint src, ChessboardPoint dest) {//判断棋子移动是否合规
        boolean IsValidMove = false;
        ArrayList<String> Points = new ArrayList<>();
        ArrayList<ChessboardPoint> RightWaterPoints = new ArrayList<>();
        ArrayList<ChessboardPoint> LeftWaterPoints = new ArrayList<>();
        ArrayList<ChessboardPoint> WaterPoints = new ArrayList<>();
        LeftWaterPoints.add(new ChessboardPoint(3,1));
        LeftWaterPoints.add(new ChessboardPoint(3,2));
        LeftWaterPoints.add(new ChessboardPoint(4,1));
        LeftWaterPoints.add(new ChessboardPoint(4,2));
        LeftWaterPoints.add(new ChessboardPoint(5,1));
        LeftWaterPoints.add(new ChessboardPoint(5,2));
        RightWaterPoints.add(new ChessboardPoint(3,4));
        RightWaterPoints.add(new ChessboardPoint(3,5));
        RightWaterPoints.add(new ChessboardPoint(4,4));
        RightWaterPoints.add(new ChessboardPoint(4,5));
        RightWaterPoints.add(new ChessboardPoint(5,4));
        RightWaterPoints.add(new ChessboardPoint(5,5));
        WaterPoints.add(new ChessboardPoint(3,1));
        WaterPoints.add(new ChessboardPoint(3,2));
        WaterPoints.add(new ChessboardPoint(4,1));
        WaterPoints.add(new ChessboardPoint(4,2));
        WaterPoints.add(new ChessboardPoint(5,1));
        WaterPoints.add(new ChessboardPoint(5,2));
        WaterPoints.add(new ChessboardPoint(3,4));
        WaterPoints.add(new ChessboardPoint(3,5));
        WaterPoints.add(new ChessboardPoint(4,4));
        WaterPoints.add(new ChessboardPoint(4,5));
        WaterPoints.add(new ChessboardPoint(5,4));
        WaterPoints.add(new ChessboardPoint(5,5));
        if (getChessPieceAt(src) != null){
            if(getChessPieceAt(src).getRank() == 1){//老鼠的常规移动
                if(getChessPieceAt(dest) == null & calculateDistance(src, dest) == 1){
                    IsValidMove = true;
                }
                if(getChessPieceAt(dest) != null & calculateDistance(src, dest) == 1 & isValidCapture(src, dest)){
                    if(!WaterPoints.contains(src)){
                        IsValidMove = true;
                    }//老鼠吃子移动
            }
        }
            if(getChessPieceAt(src).getRank() == 6 | getChessPieceAt(src).getRank() == 7){
                if(getChessPieceAt(dest) == null & calculateDistance(src, dest) == 1){
                    if(!WaterPoints.contains(dest)){
                        IsValidMove = true;
                    }
                }//Lion tiger 吃子移动
                if(getChessPieceAt(dest) != null & calculateDistance(src, dest) == 1 & isValidCapture(src, dest)){
                    Points.add("(3,1)");
                    Points.add("(3,2)");
                    Points.add("(3,4)");
                    Points.add("(3,5)");
                    Points.add("(4,1)");
                    Points.add("(4,2)");
                    Points.add("(4,4)");
                    Points.add("(4,5)");
                    Points.add("(5,1)");
                    Points.add("(5,2)");
                    Points.add("(5,4)");
                    Points.add("(5,5)");
                    if(!Points.contains(dest.toString())){
                        IsValidMove = true;
                    }
                }
                if(src.getRow() == dest.getRow() & calculateDistance(src, dest) == 3){//横向跳河
                    Points.add("(3,0)");
                    Points.add("(4,0)");
                    Points.add("(5,0)");
                    Points.add("(3,3)");
                    Points.add("(4,3)");
                    Points.add("(5,3)");
                    Points.add("(3,6)");
                    Points.add("(4,6)");
                    Points.add("(5,6)");
                    boolean NoEnemyMouse = true;
                    if (src.toString().equals(Points.get(0)) | (src.toString().equals(Points.get(1))) |
                        src.toString().equals(Points.get(2))){
                        for (int i = 0; i < LeftWaterPoints.size(); i++){
                            if (getChessPieceAt(LeftWaterPoints.get(i)) != null){
                                if (getChessPieceAt(LeftWaterPoints.get(i)).getOwner() != getChessPieceAt(src).getOwner()){
                                    NoEnemyMouse = false;
                                    break;
                            }
                        }
                    }
                    }
                    if (src.toString().equals(Points.get(3)) | (src.toString().equals(Points.get(4))) |
                            src.toString().equals(Points.get(5))){
                        if(dest.toString().equals(Points.get(0)) | dest.toString().equals(Points.get(1)) |
                           dest.toString().equals(Points.get(2))){
                            for (int i = 0; i < LeftWaterPoints.size(); i++){
                                if (getChessPieceAt(LeftWaterPoints.get(i)) != null){
                                    if (getChessPieceAt(LeftWaterPoints.get(i)).getOwner() != getChessPieceAt(src).getOwner()){
                                        NoEnemyMouse = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if(dest.toString().equals(Points.get(6)) | dest.toString().equals(Points.get(7)) |
                                dest.toString().equals(Points.get(8))){
                            for (int i = 0; i < RightWaterPoints.size(); i++){
                                if (getChessPieceAt(RightWaterPoints.get(i)) != null){
                                    if (getChessPieceAt(RightWaterPoints.get(i)).getOwner() != getChessPieceAt(src).getOwner()){
                                        NoEnemyMouse = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (src.toString().equals(Points.get(6)) | (src.toString().equals(Points.get(7))) |
                            src.toString().equals(Points.get(8))){
                        for (int i = 0; i < RightWaterPoints.size(); i++){
                            if (getChessPieceAt(RightWaterPoints.get(i)) != null){
                                if (getChessPieceAt(RightWaterPoints.get(i)).getOwner() != getChessPieceAt(src).getOwner()){
                                    NoEnemyMouse = false;
                                    break;
                                }
                            }
                        }
                    }
                    if (Points.contains(src.toString()) & NoEnemyMouse){
                        if (getChessPieceAt(dest) == null){
                            IsValidMove = true;
                        }
                        else if (isValidCapture(src, dest)){
                            IsValidMove = true;
                        }
                    }
                }

                if(src.getCol() == dest.getCol() & calculateDistance(src, dest) == 4){//纵向跳河
                    Points.add("(2,1)");
                    Points.add("(2,2)");
                    Points.add("(6,1)");
                    Points.add("(6,2)");
                    Points.add("(2,4)");
                    Points.add("(2,5)");
                    Points.add("(6,4)");
                    Points.add("(6,5)");
                    boolean nonEnemyMouse = true;
                    if (src.toString().equals(Points.get(0)) | (src.toString().equals(Points.get(1))) |
                            src.toString().equals(Points.get(2)) | (src.toString().equals(Points.get(3)))){
                        for (int i = 0; i < LeftWaterPoints.size(); i++){
                            if (getChessPieceAt(LeftWaterPoints.get(i)) != null){
                                if (getChessPieceAt(LeftWaterPoints.get(i)).getOwner() != getChessPieceAt(src).getOwner()){
                                    nonEnemyMouse = false;
                                    break;
                                }
                            }
                        }
                    }
                    if (src.toString().equals(Points.get(4)) | (src.toString().equals(Points.get(5))) |
                            src.toString().equals(Points.get(6)) | (src.toString().equals(Points.get(7)))){
                        for (int i = 0; i < RightWaterPoints.size(); i++){
                            if (getChessPieceAt(RightWaterPoints.get(i)) != null){
                                if (getChessPieceAt(RightWaterPoints.get(i)).getOwner() != getChessPieceAt(src).getOwner()){
                                    nonEnemyMouse = false;
                                    break;
                                }
                            }
                        }
                    }
                    if (Points.contains(src.toString()) & nonEnemyMouse){
                        if (getChessPieceAt(dest) == null){
                            IsValidMove = true;
                        }
                        else if (isValidCapture(src, dest)){
                            IsValidMove = true;
                        }
                    }
                }
            }
            if(getChessPieceAt(src).getRank() == 2 | getChessPieceAt(src).getRank() == 3 |
               getChessPieceAt(src).getRank() == 4 | getChessPieceAt(src).getRank() == 5 |
               getChessPieceAt(src).getRank() == 8){//其他棋子移动
                if(getChessPieceAt(dest) == null & calculateDistance(src, dest) == 1){
                    if(!WaterPoints.contains(dest)){
                        IsValidMove = true;
                    }
                }
                if(getChessPieceAt(dest) != null & calculateDistance(src, dest) == 1 & isValidCapture(src, dest)){
                    if(!WaterPoints.contains(dest)){
                        IsValidMove = true;
                    }
                }
            }
    }
       return IsValidMove;
    }

    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {//判断吃子是否合规
        boolean ValidCapture = false;
            if(getChessPieceAt(src) != null & getChessPieceAt(dest) != null & getChessPieceAt(src).canCapture(getChessPieceAt(dest))){
            ValidCapture = true;
        }
        return ValidCapture;
    }
}


