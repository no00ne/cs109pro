package view;


import model.PlayerColor;
import model.ChessPiece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * This is the equivalent of the ChessPiece class,
 * but this class only cares how to draw Chess on ChessboardComponent
 */
public class ElephantChessComponent extends JComponent {
    private PlayerColor owner;

    private boolean selected;
    private int rank;

    public ElephantChessComponent(PlayerColor owner, int size, int rank) {
        this.owner = owner;
        this.selected = false;
        this.rank = rank;
        setSize(size/2, size/2);
        setLocation(0,0);
        setVisible(true);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        URL imageURL=null;
        if (this.rank == 8){
            imageURL =getClass().getClassLoader().getResource("Elephant.png");
        }
        if (this.rank == 7){
             imageURL =getClass().getClassLoader().getResource("Lion.png");

        }
        if (this.rank == 6){
            imageURL =getClass().getClassLoader().getResource("Tiger.png");
        }
        if (this.rank == 5){
            imageURL =getClass().getClassLoader().getResource("Leopard.png");
        }
        if (this.rank == 4){
            imageURL =getClass().getClassLoader().getResource("Wolf.png");
        }
        if (this.rank == 3){
            imageURL =getClass().getClassLoader().getResource("Dog.png");
        }
        if (this.rank == 2){
            imageURL =getClass().getClassLoader().getResource("Cat.png");
        }
        if (this.rank == 1){
            imageURL =getClass().getClassLoader().getResource("Mouse.png");
        }
        BufferedImage image;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(this.rank>=1&&this.rank<=8){
        g2.drawImage(image, getWidth() / 4-16, getHeight() * 5 / 8-44,70,70,null);
        g2.setColor(owner.getColor());
        g2.drawRect(0, 0, getWidth() -2, getHeight()-2);}

        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
