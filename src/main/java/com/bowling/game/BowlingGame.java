package com.bowling.game;

import com.bowling.game.exception.BowlingException;
import com.bowling.game.strategy.GameStrategy;

import java.util.LinkedList;
import java.util.List;

/**
 * BowlingGame implementation for Game
 *
 * @author Pedro Ferri
 */
public final class BowlingGame implements Game {
    private final GameStrategy gameStrategy;

    public BowlingGame (GameStrategy gameStrategy){
        this.gameStrategy = gameStrategy;
    }

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
            player = new BowlingPlayer(this.gameStrategy);
            player.setName(playerName);
            players.add(player);
        }
        return player;
    }

    @Override
    public void calculateScore() throws BowlingException {
        this.gameStrategy.calculateScore(this);
    }
}