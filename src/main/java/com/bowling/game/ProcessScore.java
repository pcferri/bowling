package com.bowling.game;

import com.bowling.game.exception.BowlingException;

/**
 * The interface for objects which contain a behavior
 * to process score of the game
 *
 * @author Pedro Ferri
 */
public interface ProcessScore {
    /**
     * Calculate the internal score
     *
     * @throws BowlingException if any data does not comply with the system standards.
     */
    void calculateScore() throws BowlingException;
}
