package com.bowling.game;

import com.bowling.game.enuns.BowlingEnum;
import com.bowling.game.exception.BowlingException;

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

    /**
     * List of Frames
     */
    private final List<Frame> frames = new LinkedList<>();

    /**
     * Create new instance of Player
     *
     * @param name Name of the new Player
     * @return New Player object
     */
    public static Player create(String name) {
        Player player = new BowlingPlayer();
        player.setName(name);
        return player;
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
        Frame frame = null;
        int currentFrame = 0;
        boolean createNewFrame = false;

        if (frames.size() > 0) {
            frame = frames.get(frames.size() - 1);
            existsMoreRollsThanAllowed(frame);

            if (frame.getRolls().size() == BowlingEnum.ROLLS_NORMAL_FRAME.toInt() &&
                    frames.size() < BowlingEnum.LIMIT_FRAMES.toInt()) {
                createNewFrame = true;
            }
            currentFrame = frames.size();
        }

        //Check if the previous roll was a strike and create a new frame to save this roll
        if (!createNewFrame && frame != null && currentFrame < BowlingEnum.LIMIT_FRAMES.toInt()) {
            Roll previousRoll = frame.getRolls().get(frame.getRolls().size() - 1);
            if (BowlingEnum.LIMIT_FRAMES.toInt() == previousRoll.getPoints()) {
                createNewFrame = true;
            }
        }

        if (createNewFrame || frame == null) {
            frame = new BowlingFrame();
            frame.setCurrentFrame(++currentFrame);
            frame.setPlayer(this);
            frames.add(frame);

            verifyPreviousFrameWasSpared(frame);
        }
        frame.setPlayer(this);
        frame.getRolls().add(roll);
    }


    /**
     * Check if has more rolls than allowed
     *
     * @param frame frame to validate
     * @throws BowlingException if any data does not comply with the system standards.
     */
    private void existsMoreRollsThanAllowed(Frame frame) throws BowlingException {
        //Check if has more rolls than allowed
        if (frames.size() == BowlingEnum.LIMIT_FRAMES.toInt()) {
            if (frame.getRolls().size() >= BowlingEnum.ROLLS_LAST_FRAME.toInt()) {
                throw new BowlingException(BowlingEnum.ERROR_WRONG_ROLL.toString());
            }
        }
    }

    /**
     * Verify and checked if the previous frame was spared
     *
     * @param frame current frame to validation
     */
    private void verifyPreviousFrameWasSpared(Frame frame) {
        if (frames.size() > 1) {
            Frame lastFrame = frame.getPreviousFrame();
            //Check if the previous roll was spare
            int totalPointsLastFrame = lastFrame.getRolls().stream().mapToInt(Roll::getPoints).sum();
            if (totalPointsLastFrame >= BowlingEnum.MAX_PINS.toInt()) {
                lastFrame.setSpare(true);
                if (lastFrame.getRollSize() > 1) {
                    Roll lastRoll = lastFrame.getRolls().stream().reduce((a, b) -> b).get();
                    lastRoll.setPinFall("/");
                }
            }
        }
    }

    @Override
    public void calculateScore() throws BowlingException {
        if (frames.size() != BowlingEnum.LIMIT_FRAMES.toInt()) {
            throw new BowlingException(BowlingEnum.THE_NUMBER_OF_FRAMES_IS_DIFFERENT_THAN_CONFIGURED.toString());
        }
        for (Frame frame : frames) {
            frame.calculateScore();
        }
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