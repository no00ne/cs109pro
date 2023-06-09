package view;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import controller.GameController;
import model.ChessPiece;
import model.Chessboard;
import model.PlayerColor;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {

    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;

    private final int ONE_CHESS_SIZE;

    private ChessboardComponent chessboardComponent;
    private JLabel TurnLabel = new JLabel();
    private JLabel ColorLabel = new JLabel();

    public ChessGameFrame(int width, int height) {
        setTitle("2023 CS109 Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        setTurnLabel();
        setColorLabel();
        ColorSwitch();
        addChessboard();
        addLabel();
        addHelloButton();
    }

    public ChessboardComponent getChessboardComponent() {
        return chessboardComponent;
    }

    public void setChessboardComponent(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent.setLocation(HEIGTH / 5, HEIGTH / 10);
        add(chessboardComponent);
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {

        JLabel statusLabel = new JLabel("JAVA斗兽棋");
        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("楷体", Font.BOLD, 20));
        add(statusLabel);
    }

    public void addTurn(){
        int i;
        i = GameController.TurnNumber;
        TurnLabel.setText("Turn: " + i);
        }

    private void setTurnLabel() {
        TurnLabel.setText("Turn: 1");
        this.TurnLabel.setLocation(HEIGTH, HEIGTH / 10 + 240);
        this.TurnLabel.setSize(200, 60);
        this.TurnLabel.setFont(new Font("楷体", Font.BOLD, 20));
        add(this.TurnLabel);
    }

    public void setColorLabel() {
        this.ColorLabel.setText("");
        this.ColorLabel.setLocation(HEIGTH, HEIGTH / 10 + 360);
        this.ColorLabel.setSize(200, 60);
        this.ColorLabel.setFont(new Font("楷体", Font.BOLD, 20));
        add(this.ColorLabel);
    }
    public void ColorSwitch(){
        if(GameController.TurnNumber % 2 == 1){
            this.ColorLabel.setText("蓝方移动");
        }
        else {
            this.ColorLabel.setText("红方移动");
        }
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addHelloButton() {
        JButton button = new JButton("Instructions");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Jungle is a Chinese traditional chess game, here are the rules:" + "\n" +
                "Chess eating priority: Elephant>Lion>Tiger>Leopard>Wolf>Dog>Cat>Mouse. The smallest mouse can beat the biggest elephant, however." + "\n" +
                "Chess Movement: One player can move one chess for once, usually just one grid towards four directions. And only mice can enter the river" + "\n" +
                "But Lions and Tigers can jump across the river and beat animals weaker than themselves. However, if your opponent's mouse have occupied the river, " + "\n" +
                "your lion and tiger are unable to jump." + "\n" +
                "How to win the game: the cave is the center of your animal army, if anyone's cave is occupied by your opponents, you lose." + "\n" +
                "If you managed to beat all the animals of your opponent, you will also win the game." + "\n" +
                "The traps: if any of your animals enter opponents trap(orange block), any type of opponent chess can beat the trapped animal, i.e., even the cat can beat the trapped tiger."));
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    public void addResetButton (GameController gameController){
        JButton button = new JButton("Reset");
        button.setLocation(HEIGTH, HEIGTH / 10 + 600);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.getView().removeChessComponent(gameController.getModel());
                gameController.getModel().deletePieces();
                gameController.setTurnNumber(1);
                gameController.getFrame().addTurn();
                gameController.getFrame().ColorSwitch();
                gameController.setCurrentPlayer(PlayerColor.BLUE);
                gameController.getModel().getGrid()[6][0].setPiece(new ChessPiece(PlayerColor.BLUE, "象",8, false));
                gameController.getModel().getGrid()[2][6].setPiece(new ChessPiece(PlayerColor.RED, "象",8, false));
                gameController.getModel().getGrid()[8][6].setPiece(new ChessPiece(PlayerColor.BLUE, "狮",7, false));
                gameController.getModel().getGrid()[0][0].setPiece(new ChessPiece(PlayerColor.RED, "狮",7, false));
                gameController.getModel().getGrid()[8][0].setPiece(new ChessPiece(PlayerColor.BLUE, "虎",6, false));
                gameController.getModel().getGrid()[0][6].setPiece(new ChessPiece(PlayerColor.RED, "虎",6, false));
                gameController.getModel().getGrid()[2][2].setPiece(new ChessPiece(PlayerColor.RED, "豹",5, false));
                gameController.getModel().getGrid()[6][4].setPiece(new ChessPiece(PlayerColor.BLUE, "豹",5, false));
                gameController.getModel().getGrid()[6][2].setPiece(new ChessPiece(PlayerColor.BLUE, "狼",4, false));
                gameController.getModel().getGrid()[2][4].setPiece(new ChessPiece(PlayerColor.RED, "狼",4, false));
                gameController.getModel().getGrid()[1][1].setPiece(new ChessPiece(PlayerColor.RED, "狗",3, false));
                gameController.getModel().getGrid()[7][5].setPiece(new ChessPiece(PlayerColor.BLUE, "狗",3, false));
                gameController.getModel().getGrid()[7][1].setPiece(new ChessPiece(PlayerColor.BLUE, "猫",2, false));
                gameController.getModel().getGrid()[1][5].setPiece(new ChessPiece(PlayerColor.RED, "猫",2, false));
                gameController.getModel().getGrid()[2][0].setPiece(new ChessPiece(PlayerColor.RED, "鼠",1, false));
                gameController.getModel().getGrid()[6][6].setPiece(new ChessPiece(PlayerColor.BLUE, "鼠",1, false));
                gameController.getView().initiateChessComponent(gameController.getModel());
            }
        });
    }
    public void addLoadButton(GameController gameController) {
        JButton button = new JButton("Load");
        button.setLocation(810, 601);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String line;
                ArrayList<String> Text  = new ArrayList<>();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("标签文件(*.txt)", "txt");//过滤文件格式
                fileChooser.setFileFilter(filter);
                fileChooser.setSelectedFile(new File("C:\\Users\\Administrator\\Desktop\\save"));//选择文件读取路径
                fileChooser.showSaveDialog(null);
                File file = fileChooser.getSelectedFile();
                int state = fileChooser.showOpenDialog(null);
                if(state == JFileChooser.APPROVE_OPTION){
                    try {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        InputStreamReader reader = new InputStreamReader(fileInputStream);
                        BufferedReader bufferedReader = new BufferedReader(reader);
                        while ((line = bufferedReader.readLine()) != null){
                            Text.add(line);
                        }
                        bufferedReader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    if(Text.size() != 29){//各种各样的错误检查
                        System.out.print("Wrong File Size!");
                    }
                    else if (!Text.get(1).equals("RED") & !Text.get(1).equals("BLUE")){
                        System.out.print("Wrong Player!");
                    }
                    else if (!Text.get(0).matches("^[0-9]*$")){
                        System.out.print("Wrong Turn number!");
                    }
                    else if(Text.get(0).startsWith("0")){
                        System.out.print("Wrong Turn number!");
                    }
                    else if (Text.get(2).length() != 7 | Text.get(3).length() != 7 |
                            Text.get(4).length() != 7 | Text.get(5).length() != 7 |
                            Text.get(6).length() != 7 | Text.get(7).length() != 7 |
                            Text.get(8).length() != 7 | Text.get(9).length() != 7 |
                            Text.get(10).length()!= 7 | Text.get(11).length() != 7|
                            Text.get(12).length() != 7| Text.get(13).length() != 7|
                            Text.get(14).length() != 7| Text.get(15).length() != 7|
                            Text.get(16).length() != 7| Text.get(17).length() != 7|
                            Text.get(18).length() != 7| Text.get(19).length() != 7|
                            Text.get(20).length() != 7| Text.get(21).length() != 7|
                            Text.get(22).length() != 7| Text.get(23).length() != 7|
                            Text.get(24).length() != 7| Text.get(25).length() != 7|
                            Text.get(26).length() != 7| Text.get(27).length() != 7|
                            Text.get(28).length() != 7) {
                            System.out.print("Wrong Chessboard size!");
                    }
                    else if(!Text.get(2).matches("^[0-9]*$") |
                            !Text.get(3).matches("^[0-9]*$") |
                            !Text.get(4).matches("^[0-9]*$") |
                            !Text.get(5).matches("^[0-9]*$") |
                            !Text.get(6).matches("^[0-9]*$") |
                            !Text.get(7).matches("^[0-9]*$") |
                            !Text.get(8).matches("^[0-9]*$") |
                            !Text.get(9).matches("^[0-9]*$") |
                            !Text.get(10).matches("^[0-9]*$")){
                        System.out.print("Wrong Chess Piece!");
                    }
                    else if (!Text.get(11).matches("^[0-2]*$") |
                            !Text.get(12).matches("^[0-2]*$") |
                            !Text.get(13).matches("^[0-2]*$") |
                            !Text.get(14).matches("^[0-2]*$") |
                            !Text.get(15).matches("^[0-2]*$") |
                            !Text.get(16).matches("^[0-2]*$") |
                            !Text.get(17).matches("^[0-2]*$") |
                            !Text.get(18).matches("^[0-2]*$") |
                            !Text.get(19).matches("^[0-2]*$")) {
                        System.out.print("Wrong Player Color!");
                    }
                    else if (!Text.get(20).matches("^[0-2]*$") |
                             !Text.get(21).matches("^[0-2]*$") |
                             !Text.get(22).matches("^[0-2]*$") |
                             !Text.get(23).matches("^[0-2]*$") |
                             !Text.get(24).matches("^[0-2]*$") |
                             !Text.get(25).matches("^[0-2]*$") |
                             !Text.get(26).matches("^[0-2]*$") |
                             !Text.get(27).matches("^[0-2]*$") |
                             !Text.get(28).matches("^[0-2]*$")) {
                        System.out.print("Wrong Trapped Animals!");
                    } else {
                        gameController.getView().removeChessComponent(gameController.getModel());
                        gameController.getModel().deletePieces();
                        int readTurnNumber = Integer.parseInt(Text.get(0));
                            gameController.setTurnNumber(readTurnNumber);
                            gameController.getFrame().addTurn();
                            gameController.getFrame().ColorSwitch();
                        if(Text.get(1).equals("BLUE")){
                            gameController.setCurrentPlayer(PlayerColor.BLUE);
                        }
                        if(Text.get(1).equals("RED")){
                            gameController.setCurrentPlayer(PlayerColor.RED);
                        }
                        int [][] readChess = new int[9][7];
                        int [][] readColor = new int[9][7];
                        int [][] readTrap = new int[9][7];
                        for (int i = 0; i < 9; i++){
                            for (int j = 0; j < 7; j++){
                                if(j < 6){
                                    readChess[i][j] = Integer.parseInt(Text.get(i + 2).substring(j, j+1));
                                    readColor[i][j] = Integer.parseInt(Text.get(i + 11).substring(j, j+1));
                                    readTrap[i][j] = Integer.parseInt(Text.get(i + 20).substring(j,j+1));
                                }
                                else {
                                    readChess[i][j] = Integer.parseInt(Text.get(i + 2).substring(j));
                                    readColor[i][j] = Integer.parseInt(Text.get(i + 11).substring(j));
                                    readTrap[i][j] = Integer.parseInt(Text.get(i + 20).substring(j));
                                }
                            }
                        }
                        for (int i = 0; i < 9; i++){
                            for (int j = 0; j < 7; j++){
                                if(readChess[i][j] == 1){
                                    if(readColor[i][j] == 1){
                                      if(readTrap[i][j] == 1){
                                          gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Mouse", 1, false));
                                      }
                                      else if (readTrap[i][j] == 2) {
                                          gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Mouse", 1, true));
                                      }
                                      else {
                                          System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" + " ");
                                          break;
                                      }
                                    }
                                    else if(readColor[i][j] == 2){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Mouse", 1, false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Mouse", 1, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" +" ");
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.print("Chess and color not match!" + "(" + i + "," + j + ")" + "\n" +" ");
                                        break;
                                    }
                                }
                                if(readChess[i][j] == 2){
                                    if(readColor[i][j] == 1){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat", 2, false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat", 2, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" +" ");
                                            break;
                                        }
                                    }
                                    else if(readColor[i][j] == 2){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Cat", 2, false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Cat", 2, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" + " ");
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.print("Chess and color not match!" + "(" + i + "," + j + ")" + "\n" +" ");
                                        break;
                                    }
                                }
                                if(readChess[i][j] == 3){
                                    if(readColor[i][j] == 1){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog", 3, false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog", 3, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n"+ " ");
                                            break;
                                        }
                                    }
                                    else if(readColor[i][j] == 2){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Dog", 3, false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Dog", 3, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" +" ");
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.print("Chess and color not match!" + "(" + i + "," + j + ")" + "\n" + " ");
                                        break;
                                    }
                                }
                                if(readChess[i][j] == 4){
                                    if(readColor[i][j] == 1){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf", 4, false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf", 4, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" + " ");
                                            break;
                                        }
                                    }
                                    else if(readColor[i][j] == 2){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Wolf", 4, false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Wolf", 4, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" +" ");
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.print("Chess and color not match!" + "(" + i + "," + j + ")" + "\n" +" ");
                                        break;
                                    }
                                }
                                if(readChess[i][j] == 5){
                                    if(readColor[i][j] == 1){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard", 5, false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard", 5, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" + " ");
                                            break;
                                        }
                                    }
                                    else if(readColor[i][j] == 2){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Leopard", 5, false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Leopard", 5, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" +" ");
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.print("Chess and color not match!" + "(" + i + "," + j + ")" + "\n" +" ");
                                        break;
                                    }
                                }
                                if(readChess[i][j] == 6){
                                    if(readColor[i][j] == 1){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger", 6, false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger", 6, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" + " ");
                                            break;
                                        }
                                    }
                                    else if(readColor[i][j] == 2){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Tiger", 6, false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Tiger", 6, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" + " ");
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.print("Chess and color not match!" + "(" + i + "," + j + ")" + "\n" + " ");
                                        break;
                                    }
                                }
                                if(readChess[i][j] == 7){
                                    if(readColor[i][j] == 1){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion",7, false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion",7, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" + " ");
                                            break;
                                        }
                                    }
                                    else if(readColor[i][j] == 2){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Lion",7, false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Lion",7, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" + " ");
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.print("Chess and color not match!" + "(" + i + "," + j + ")" + "\n" + " ");
                                        break;
                                    }
                                }
                                if(readChess[i][j] == 8){
                                    if(readColor[i][j] == 1){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant",8, false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant",8, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" +" ");
                                            break;
                                        }
                                    }
                                    else if(readColor[i][j] == 2){
                                        if(readTrap[i][j] == 1){
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Elephant",8,false));
                                        }
                                        else if (readTrap[i][j] == 2) {
                                            gameController.getModel().getGrid()[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Elephant",8, true));
                                        }
                                        else {
                                            System.out.print("Chess and trap not match!" + "(" + i + "," + j + ")" + "\n" + " ");
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.print("Chess and color not match!" + "(" + i + "," + j + ")" + "\n" + " ");
                                        break;
                                    }
                                }
                            }
                        }
                        gameController.getView().initiateChessComponent(gameController.getModel());
                    }
                }
            }

        });
    }
    public void addSaveButton(Chessboard chessboard){
        JButton button = new JButton("Save");
        button.setLocation(810, 481);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("标签文件(*.txt)", "txt");
                fileChooser.setFileFilter(filter);
                fileChooser.setSelectedFile(new File("C:\\Users\\Administrator\\Desktop\\save"));
                fileChooser.showSaveDialog(null);
                File file = fileChooser.getSelectedFile();
                int state = fileChooser.showOpenDialog(null);
                if(state == JFileChooser.APPROVE_OPTION){
                    try{
                        PrintStream printStream = new PrintStream(new FileOutputStream(file)) ;
                        printStream.println(chessboard.toString());//把棋盘信息录入txt文件
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
    }

}
