package org.example.server;

import org.example.CommonGameState;
import org.example.shared.FieldStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class GameLogic extends CommonGameState {


    private GameLogic(Integer turn, List<FieldStatus> board) {
        super(turn, board);
    }

    public static GameLogic initialState() {
        return new GameLogic(1, Collections.emptyList());
    }

    public void nextTurn() {
        this.turn++;
    }

    public void maybeResetTurn() {
        if (turn == 7)
            turn = 1;
    }

    public void checkWin(int playerNum) {
        if (playerNum < 1 || playerNum > 2) {
            throw new IllegalArgumentException("Invalid playerNum");
        }
        FieldStatus oppositeFieldValue;
        if (playerNum == 1) {
            oppositeFieldValue = FieldStatus.PLAYER_0;
        } else {
            oppositeFieldValue = FieldStatus.PLAYER_X;
        }
        int countO = 0;
        for (int i = 0; i < 100; i++) {
            if (board.get(i) == oppositeFieldValue) countO++;
        }
        if (countO == 0) {
            setWinner(1);
        }
    }

    public boolean isAbleToMakeMove(int newIndex, FieldStatus player2, FieldStatus player1Override) {
        return getCountAround(newIndex, player2) > 0 ||
                getCountAround(newIndex, player1Override) > 0;
    }


    private int getCountAround(int newIndex, FieldStatus symbol) {
        return Stream.of(
                Arrays.asList(0, 0),
                Arrays.asList(0, 1),
                Arrays.asList(0, 2),
                Arrays.asList(1, 0),
                Arrays.asList(1, 1),
                Arrays.asList(1, 2),
                Arrays.asList(2, 0),
                Arrays.asList(2, 1),
                Arrays.asList(2, 2)
        ).map(pair -> newIndex + 10 * pair.get(0) + pair.get(1))
                .map(pos -> isPosContains(pos, symbol))
                .mapToInt(result -> result? 1: 0)
                .sum();
    }

    private boolean isPosContains(int pos, FieldStatus field) {
        return pos >= 0 && pos < 100 && board.get(pos) == field;
    }

    public boolean isPlayerTurn(int playerNum) {
        if (playerNum < 1 || playerNum > 2) {
            throw new IllegalArgumentException("Invalid playerNum");
        }

        return (playerNum == 1 && (turn >= 1 && turn <= 3)) || (playerNum == 2 && (turn >= 4 && turn <= 6));
    }
}
