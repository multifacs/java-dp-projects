package client_data.UI;

import java.awt.Color;

public enum  UIState {
    NOT_ACTIVE_POINT(new Color(128, 128, 128)),
    NOT_ACTIVE_LINE(new Color(0, 0, 0)),
    ACTIVE_FIRST_PLAYER(new Color(0, 255, 0)),
    ACTIVE_SECOND_PLAYER(new Color(255, 0, 255)),
    CHOOSING_FIRST_PLAYER(new Color(0, 255, 255)),
    CHOOSING_SECOND_PLAYER(new Color(255, 0, 255)),
    MISSED_POINT(new Color(255, 0, 0));

    private final Color color;

    UIState(Color color) {
        this.color = color;
    }

    public Color getColor() {
        Color color1 = color;
        return color1;
    }
}
