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

    /**
     * Score of this frame
     */
    private int score = 0;

    /**
     * Current player of this frame
     */
    private Player player = null;

    @Override
    public Frame getPreviousFrame() {
        return getPlayer().getFrames().get(this.currentFrame - 2);
    }

    @Override
    public Frame getNextFrame() throws BowlingException {
        if (getPlayer().getFrames().size() > this.currentFrame) {
            return getPlayer().getFrames().get(this.currentFrame);
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

    @Override
    public int getScore() {
        return score;
    }

    /**
     * Calculate the score from this frame respecting the rules of spare Bowling score.
     *
     * @throws BowlingException if any data does not comply with the system standards.
     */
    @Override
    public void calculateScore() throws BowlingException {
        int scoreRoll = rolls.stream().mapToInt(Roll::getPoints).sum();

        //if the frame spare and has only 1 roll get next 2 rolls
        if (isSpare() && rolls.size() == 1) {
            Frame nextFrame = getNextFrame();

            //Get ony 2 rolls in the next frame
            if (nextFrame.getCurrentFrame() == BowlingEnum.LIMIT_FRAMES.toInt()) {
                scoreRoll += nextFrame.getRolls().stream().limit(2).mapToInt(Roll::getPoints).sum();
            } else {
                scoreRoll += nextFrame.getRolls().stream().mapToInt(Roll::getPoints).sum();
            }

            //If the next has only 1 roll, get in the next frame
            if (nextFrame.getRolls().size() == 1) {
                scoreRoll += nextFrame.getNextFrame().getRolls().stream().findFirst().get().getPoints();
            }
        } else {
            //if the frame spare and has 2 rolls get next 1 roll only
            if (isSpare() && rolls.size() == 2) {
                scoreRoll += getNextFrame().getRolls().stream().findFirst().get().getPoints();
            }
        }

        //sum the score with previous score frame
        if (currentFrame > 1) {
            scoreRoll += getPreviousFrame().getScore();
        }

        this.score = scoreRoll;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }
}
