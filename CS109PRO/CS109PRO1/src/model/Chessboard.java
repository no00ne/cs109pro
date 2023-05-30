package model;
import java.util.ArrayList;

import controller.GameController;
/**
 * This class store the real chess information.
 * The Chessboard has 9*7 cells, and each cell has a position for chess
 */
public class Chessboard {

    private final Cell[][] grid;

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
    public void deletePieces(){//删除所有实例化棋子对象
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j].removePiece();
            }
        }
    }
    private void initPieces() {//棋子初始化
        grid[6][0].setPiece(new ChessPiece(PlayerColor.BLUE, "象",8, false));
        grid[2][6].setPiece(new ChessPiece(PlayerColor.RED, "象",8, false));
        grid[8][6].setPiece(new ChessPiece(PlayerColor.BLUE, "狮",7, false));
        grid[0][0].setPiece(new ChessPiece(PlayerColor.RED, "狮",7, false));
        grid[8][0].setPiece(new ChessPiece(PlayerColor.BLUE, "虎",6, false));
        grid[0][6].setPiece(new ChessPiece(PlayerColor.RED, "虎",6, false));
        grid[2][2].setPiece(new ChessPiece(PlayerColor.RED, "豹",5, false));
        grid[6][4].setPiece(new ChessPiece(PlayerColor.BLUE, "豹",5, false));
        grid[6][2].setPiece(new ChessPiece(PlayerColor.BLUE, "狼",4, false));
        grid[2][4].setPiece(new ChessPiece(PlayerColor.RED, "狼",4, false));
        grid[1][1].setPiece(new ChessPiece(PlayerColor.RED, "狗",3, false));
        grid[7][5].setPiece(new ChessPiece(PlayerColor.BLUE, "狗",3, false));
        grid[7][1].setPiece(new ChessPiece(PlayerColor.BLUE, "猫",2, false));
        grid[1][5].setPiece(new ChessPiece(PlayerColor.RED, "猫",2, false));
        grid[2][0].setPiece(new ChessPiece(PlayerColor.RED, "鼠",1, false));
        grid[6][6].setPiece(new ChessPiece(PlayerColor.BLUE, "鼠",1, false));
    }
    public ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    private Cell getGridAt(ChessboardPoint point) {
        return grid[point.row()][point.col()];
    }

    private int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.row() - dest.row()) + Math.abs(src.col() - dest.col());
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
        System.out.print(getChessPieceOwner(src) + " " + getChessPieceAt(src).getName() + " move to " + dest.toString() + "\n");
        setChessPiece(dest, removeChessPiece(src));
    }

    public void captureChessPiece(ChessboardPoint src, ChessboardPoint dest) {//吃棋子（未完成）
        if (isValidCapture(src, dest)) {
            System.out.print(getChessPieceOwner(src) + " " + getChessPieceAt(src).getName() + " has eaten " + getChessPieceOwner(dest)
                     + " " + getChessPieceAt(dest).getName() + " at " + dest.toString() + "\n");
            getGridAt(dest).removePiece();
            setChessPiece(dest, removeChessPiece(src));
        }
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
                    if(getChessPieceAt(src).getOwner() == PlayerColor.BLUE){
                        if(!dest.toString().equals("(8,3)")){
                            IsValidMove = true;
                        }
                    }
                    else if(!dest.toString().equals("(0,3)")){
                        IsValidMove = true;
                    }
                }
                if(getChessPieceAt(dest) != null & calculateDistance(src, dest) == 1 & isValidCapture(src, dest)){
                    if(!WaterPoints.contains(src) & !WaterPoints.contains(dest)){
                        IsValidMove = true;
                    }//老鼠吃子移动
            }
        }
            if(getChessPieceAt(src).getRank() == 6 | getChessPieceAt(src).getRank() == 7){
                if(getChessPieceAt(dest) == null & calculateDistance(src, dest) == 1){
                    if(!WaterPoints.contains(dest)){
                        if(getChessPieceAt(src).getOwner() == PlayerColor.BLUE){
                            if(!dest.toString().equals("(8,3)")){
                                IsValidMove = true;
                            }
                        }
                        else if(!dest.toString().equals("(0,3)")){
                            IsValidMove = true;
                        }
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
                if(src.row() == dest.row() & calculateDistance(src, dest) == 3){//横向跳河
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
                        for (ChessboardPoint leftWaterPoint : LeftWaterPoints) {
                            if (getChessPieceAt(leftWaterPoint) != null) {
                                if (getChessPieceAt(leftWaterPoint).getOwner() != getChessPieceAt(src).getOwner()) {
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
                            for (ChessboardPoint leftWaterPoint : LeftWaterPoints) {
                                if (getChessPieceAt(leftWaterPoint) != null) {
                                    if (getChessPieceAt(leftWaterPoint).getOwner() != getChessPieceAt(src).getOwner()) {
                                        NoEnemyMouse = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if(dest.toString().equals(Points.get(6)) | dest.toString().equals(Points.get(7)) |
                                dest.toString().equals(Points.get(8))){
                            for (ChessboardPoint rightWaterPoint : RightWaterPoints) {
                                if (getChessPieceAt(rightWaterPoint) != null) {
                                    if (getChessPieceAt(rightWaterPoint).getOwner() != getChessPieceAt(src).getOwner()) {
                                        NoEnemyMouse = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (src.toString().equals(Points.get(6)) | (src.toString().equals(Points.get(7))) |
                            src.toString().equals(Points.get(8))){
                        for (ChessboardPoint rightWaterPoint : RightWaterPoints) {
                            if (getChessPieceAt(rightWaterPoint) != null) {
                                if (getChessPieceAt(rightWaterPoint).getOwner() != getChessPieceAt(src).getOwner()) {
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

                if(src.col() == dest.col() & calculateDistance(src, dest) == 4){//纵向跳河
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
                        for (ChessboardPoint leftWaterPoint : LeftWaterPoints) {
                            if (getChessPieceAt(leftWaterPoint) != null) {
                                if (getChessPieceAt(leftWaterPoint).getOwner() != getChessPieceAt(src).getOwner()) {
                                    nonEnemyMouse = false;
                                    break;
                                }
                            }
                        }
                    }
                    if (src.toString().equals(Points.get(4)) | (src.toString().equals(Points.get(5))) |
                            src.toString().equals(Points.get(6)) | (src.toString().equals(Points.get(7)))){
                        for (ChessboardPoint rightWaterPoint : RightWaterPoints) {
                            if (getChessPieceAt(rightWaterPoint) != null) {
                                if (getChessPieceAt(rightWaterPoint).getOwner() != getChessPieceAt(src).getOwner()) {
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
               getChessPieceAt(src).getRank() == 8 | getChessPieceAt(src).getRank() == 0){//其他棋子移动
                if(getChessPieceAt(dest) == null & calculateDistance(src, dest) == 1){
                    if(!WaterPoints.contains(dest)){
                        if(getChessPieceAt(src).getOwner() == PlayerColor.BLUE){
                            if(!dest.toString().equals("(8,3)")){
                                IsValidMove = true;
                            }
                        }
                        else if(!dest.toString().equals("(0,3)")){
                            IsValidMove = true;
                        }
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
        return getChessPieceAt(src) != null & getChessPieceAt(dest) != null & getChessPieceAt(src).canCapture(getChessPieceAt(dest));
    }

    @Override
    public String toString() {//保存当前的棋子信息，便于写入save文件
        String currentPlayer;
        if(GameController.TurnNumber % 2 == 1){
            currentPlayer = "BLUE";
        }
        else {
            currentPlayer = "RED";
        }
        int [][] chess = new int [9][7];//记录棋子位置 1-8代表棋子等级
        int [][] color = new int [9][7];//记录每个位置棋子颜色，1蓝 2红
        int [][] trapped = new int [9][7];//记录每个位置棋子是否踩过陷阱 1踩  2没有踩
        for (int i = 0; i < 9; i ++){
            for (int j = 0; j < 7; j++){
                if(this.grid[i][j].getPiece() != null){
                    if(this.grid[i][j].getPiece().isTrapped()){
                        trapped[i][j] = 2;
                    }else trapped[i][j] = 1;
                }
            }
        }
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 7; j++){
                if(this.grid[i][j].getPiece() != null){
                    chess[i][j] = this.grid[i][j].getPiece().getRank();
                    if(this.grid[i][j].getPiece().getOwner().equals(PlayerColor.BLUE)){
                        color[i][j] = 1;
                    }
                    else color[i][j] = 2;
                }
                else {
                    chess[i][j] = 0;
                    color[i][j] = 0;
                }
            }
        }
        StringBuilder chess1 = new StringBuilder();
        StringBuilder color1 = new StringBuilder();
        StringBuilder trapped1 = new StringBuilder();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 7; j++){
                chess1.append(chess[i][j]);
                color1.append(color[i][j]);
                trapped1.append(trapped[i][j]);
            }
        }
        String chessRow1 = chess1.substring(0,7);
        String chessRow2 = chess1.substring(7,14);
        String chessRow3 = chess1.substring(14,21);
        String chessRow4 = chess1.substring(21,28);
        String chessRow5 = chess1.substring(28,35);
        String chessRow6 = chess1.substring(35,42);
        String chessRow7 = chess1.substring(42,49);
        String chessRow8 = chess1.substring(49,56);
        String chessRow9 = chess1.substring(56,63);
        String colorRow1 = color1.substring(0,7);
        String colorRow2 = color1.substring(7,14);
        String colorRow3 = color1.substring(14,21);
        String colorRow4 = color1.substring(21,28);
        String colorRow5 = color1.substring(28,35);
        String colorRow6 = color1.substring(35,42);
        String colorRow7 = color1.substring(42,49);
        String colorRow8 = color1.substring(49,56);
        String colorRow9 = color1.substring(56,63);
        String trapRow1 = trapped1.substring(0,7);
        String trapRow2 = trapped1.substring(7,14);
        String trapRow3 = trapped1.substring(14,21);
        String trapRow4 = trapped1.substring(21,28);
        String trapRow5 = trapped1.substring(28,35);
        String trapRow6 = trapped1.substring(35,42);
        String trapRow7 = trapped1.substring(42,49);
        String trapRow8 = trapped1.substring(49,56);
        String trapRow9 = trapped1.substring(56,63);
        return GameController.TurnNumber + "\n" + currentPlayer + "\n"+chessRow1 + "\n" + chessRow2 + "\n" + chessRow3
                 + "\n" + chessRow4 + "\n" + chessRow5 + "\n" + chessRow6 + "\n" +chessRow7 + "\n" + chessRow8 + "\n" + chessRow9 +
                "\n" + colorRow1 + "\n" + colorRow2 + "\n"  + colorRow3 + "\n" + colorRow4 + "\n" + colorRow5 + "\n" +
                colorRow6 + "\n" + colorRow7 + "\n" + colorRow8 + "\n" + colorRow9 + "\n" + trapRow1 + "\n" + trapRow2 + "\n" + trapRow3
                + "\n" +trapRow4 + "\n" + trapRow5 + "\n" + trapRow6 + "\n" + trapRow7 + "\n" + trapRow8 + "\n" + trapRow9;
    }
}


