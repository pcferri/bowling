package com.bowling.game;

/**
 * The interface for objects which contain a behavior
 * to roll a ball
 *
 * @author Pedro Ferri
 */
public interface Roll {
    /**
     * Get points from the roll
     *
     * @return points of roll
     */
    int getPoints();

    /**
     * Set new points
     *
     * @param points new points
     */
    void setPoints(int points);

    /**
     * Get the pinFall of this roll
     *
     * @return get the pinFall value of this roll
     */
    String getPinFall();

    /**
     * Set the pinFall of this roll
     *
     * @param pinFall new pinfall
     */
    void setPinFall(String pinFall);
}
