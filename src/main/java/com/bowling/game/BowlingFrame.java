package com.bowling.game;

import com.bowling.game.enuns.BowlingEnum;
import com.bowling.game.exception.BowlingException;

import java.util.LinkedList;
import java.util.List;

/**
 * BowlingFrame implementation for Frame
 *
 * @author Pedro Ferri
 */
public class BowlingFrame implements Frame {

    /**
     * Check if this frame has a spare point
     */
    private boolean spare = false;

    /**
     * Unique number to this current frame
     */
    private int currentFrame = 0;

    /**
     * All rolls from this frame
     */
    private final List<Roll> rolls = new LinkedList<>();

    private int score = 0;

    /**
     * Current player of this frame
     */
    private Player player = null;

    @Override
    public Frame getPreviousFrame() {
        return this.player.getFrames().get(this.currentFrame - 2);
    }

    @Override
    public Frame getNextFrame() throws BowlingException {
        if (this.player.getFrames().size() > this.currentFrame) {
            return this.player.getFrames().get(this.currentFrame);
        } else {
            throw new BowlingException(BowlingEnum.INVALID_NUMBERS_OF_FRAMES_FOUND.toString());
        }
    }

    @Override
    public int getCurrentFrame() {
        return this.currentFrame;
    }

    @Override
    public void setCurrentFrame(int frameNumber) {
        this.currentFrame = frameNumber;
    }

    @Override
    public List<Roll> getRolls() {
        return rolls;
    }

    @Override
    public boolean isSpare() {
        return this.spare;
    }

    @Override
    public void setSpare(boolean spare) {
        this.spare = spare;
    }

    @Override
    public int getRollSize() {
        return rolls.size();
    }

    /**
     * Score of this frame
     */
    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void calculateScore() throws BowlingException {
        player.getGameStrategy().calculateScore(this);
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }
}
