package org.example.shared;

import java.io.Serializable;
import java.util.List;

public class GameStateDto implements Serializable {
    public Integer turn;
    public List<FieldStatus> board;
    public boolean isPlayer1Win;
    public boolean isPlayer2Win;
}
