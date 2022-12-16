package org.example;

import org.example.shared.FieldStatus;
import org.example.shared.GameStateDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommonGameState {
    protected Integer turn;
    public final List<FieldStatus> board;

    public boolean isPlayer1Win() {
        return isPlayer1Win;
    }

    public boolean isPlayer2Win() {
        return isPlayer2Win;
    }

    protected boolean isPlayer1Win;
    protected boolean isPlayer2Win;

    public CommonGameState(Integer turn, List<FieldStatus> board) {
        this.turn = turn;
        this.board = new ArrayList<>(board);
    }

    public CommonGameState(GameStateDto dto) {
        turn = dto.turn;
        board = new ArrayList<>(dto.board);
        isPlayer1Win = dto.isPlayer1Win;
        isPlayer2Win = dto.isPlayer2Win;
    }

    public Integer getTurn() {
        return turn;
    }

    public GameStateDto getDto() {
        GameStateDto dto = new GameStateDto();
        dto.turn = turn;
        dto.board = board;
        dto.isPlayer1Win = isPlayer1Win;
        dto.isPlayer2Win = isPlayer2Win;
        return dto;
    }

    public boolean checkTurn(int playerNum) {
        if (playerNum < 1 || playerNum > 2) {
            throw new IllegalArgumentException("Invalid player num");
        }
        boolean flag = false;
        switch (playerNum) {
            case 1 -> flag = Arrays.asList(1,2,3).contains(turn);
            case 2 -> flag = Arrays.asList(4,5,6).contains(turn);
        }
        return flag;
    }

    public void setWinner(int playerNum) {
        if (playerNum < 0 || playerNum > 2) {
            throw new IllegalArgumentException("Invalid player num");
        }
        if (playerNum == 1) {
            isPlayer1Win = true;
        } else {
            isPlayer2Win = true;
        }
    }

    public boolean isNoWinner() {
        return !isPlayer1Win && !isPlayer2Win;
    }
}
