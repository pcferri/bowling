package com.bowling.game;

import com.bowling.game.exception.BowlingException;

import java.util.LinkedList;
import java.util.List;

/**
 * BowlingGame implementation for Game
 *
 * @author Pedro Ferri
 */
public class BowlingGame implements Game {
    /**
     * All players of this Game
     */
    private final List<Player> players = new LinkedList<>();

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    @Override
    public Player getPlayer(String playerName) {
        Player player = players.stream()
                .filter(x -> playerName.equals(x.getName()))
                .findAny()
                .orElse(null);
        if (player == null) {
            player = BowlingPlayer.create(playerName);
            players.add(player);
        }
        return player;
    }

    /**
     * Call the calculateScore from the all players
     *
     * @throws BowlingException if any data does not comply with the system standards.
     */
    @Override
    public void calculateScore() throws BowlingException {
        for (Player player : players) {
            player.calculateScore();
        }
    }
}
