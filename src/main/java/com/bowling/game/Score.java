package com.bowling.game;

/**
 * The interface for objects which contain a behavior
 * to save a score
 *
 * @author Pedro Ferri
 */
public interface Score {
    /**
     * Get the score from the object
     *
     * @return total of score of the object
     */
    int getScore();

    void setScore(int score);
}
