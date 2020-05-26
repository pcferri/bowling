package com.bowling.game.strategy;

import com.bowling.game.Frame;
import com.bowling.game.Game;
import com.bowling.game.Player;
import com.bowling.game.exception.BowlingException;

/**
 * The interface for objects which contain a behavior
 * to validate the Bowling Game
 *
 * @author Pedro Ferri
 */
public interface ScoreCalculation {

    /**
     * Calculate the score for the player level
     * @param player Current player
     * @throws BowlingException if any data does not comply with the system standards.
     */
    void calculateScore(Player player) throws BowlingException;

    /**
     * Calculate the score for the frame level
     * @param frame Current player
     * @throws BowlingException if any data does not comply with the system standards.
     */
    void calculateScore(Frame frame) throws BowlingException;

    /**
     * Calculate the score for the game level
     * @param game Current player
     * @throws BowlingException if any data does not comply with the system standards.
     */
    void calculateScore(Game game)throws BowlingException;
}
