package model;

import java.awt.*;

/**
 * This class is mainly used to wrap the Color object that describes
 * the ownership of the piece and the current player.
 */
public enum PlayerColor {//双方玩家
    BLUE(Color.BLUE), RED(Color.RED);

    private final Color color;

    PlayerColor(Color color) {
        this.color = color;
    }


    public Color getColor() {
        return color;
    }


}
