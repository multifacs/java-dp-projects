package org.example.client;

import org.example.CommonGameState;
import org.example.shared.GameStateDto;

import java.io.Serializable;

public class PlayerGameState extends CommonGameState implements Serializable {
    public PlayerGameState(GameStateDto dto) {
        super(dto);
    }
}
