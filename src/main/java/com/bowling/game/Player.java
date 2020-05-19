package com.bowling.game;

import com.bowling.game.exception.BowlingException;

import java.util.List;

/**
 * The interface for objects which contain a behavior
 * to manage a Player
 *
 * @author Pedro Ferri
 */
public interface Player extends ProcessScore {
    /**
     * Get name of the player
     *
     * @return name of player
     */
    String getName();

    /**
     * Set the name of the player
     *
     * @param name new name
     */
    void setName(String name);

    /**
     * Get all the frames which this player has
     *
     * @return List of Frame
     */
    List<Frame> getFrames();

    /**
     * Add a new roll into available frames
     *
     * @param roll Roll with all information need
     * @throws BowlingException if any data does not comply with the system standards.
     */
    void addRollIntoFrames(Roll roll) throws BowlingException;
}
