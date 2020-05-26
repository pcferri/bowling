package com.bowling.game;

import com.bowling.game.exception.BowlingException;
import com.bowling.game.strategy.GameStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * BowlingPlayer implementation for Player
 *
 * @author Pedro Ferri
 */
public class BowlingPlayer implements Player {

    /**
     * Name of the player
     */
    private String name;

    private final GameStrategy gameStrategy;

    @Override
    public GameStrategy getGameStrategy() {
        return gameStrategy;
    }

    /**
     * List of Frames
     */
    private final List<Frame> frames = new LinkedList<>();

    public BowlingPlayer(GameStrategy gameStrategy) {
        this.gameStrategy = gameStrategy;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Frame> getFrames() {
        return frames;
    }

    @Override
    public void addRollIntoFrames(Roll roll) throws BowlingException {
        getGameStrategy().addRollIntoFrames(this, roll);
    }

    @Override
    public void calculateScore() throws BowlingException {
        gameStrategy.calculateScore(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingPlayer that = (BowlingPlayer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}