package com.bowling.game;

import com.bowling.game.exception.BowlingException;

import java.util.List;

/**
 * The interface for objects which contain a behavior
 * of Frame
 *
 * @author Pedro Ferri
 */
public interface Frame extends Score, ProcessScore {
    /**
     * Get the previous frame
     *
     * @return Previous frame
     */
    Frame getPreviousFrame();

    /**
     * Get the next frame
     *
     * @return next Frame
     * @throws BowlingException if any data does not comply with the system standards.
     */
    Frame getNextFrame() throws BowlingException;

    /**
     * get number of the current Frame
     *
     * @return number of the current frame
     */
    int getCurrentFrame();

    /**
     * Get Player owner of this Frame
     *
     * @return Player
     */
    Player getPlayer();

    /**
     * Set a new Player
     *
     * @param player player object
     */
    void setPlayer(Player player);

    /**
     * Set a new current number
     *
     * @param frameNumber number of the current frame
     */
    void setCurrentFrame(int frameNumber);

    /**
     * Get all rolls
     *
     * @return List of Roll
     */
    List<Roll> getRolls();

    /**
     * Check if this frame has a spare point
     *
     * @return true if this frame has a spare point
     */
    boolean isSpare();

    /**
     * Set if this frame has a spare
     *
     * @param spare new spare status
     */
    void setSpare(boolean spare);

    /**
     * Get the site of the Roll
     *
     * @return size of the Roll list
     */
    int getRollSize();
}
