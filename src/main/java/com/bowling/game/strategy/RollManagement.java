package com.bowling.game.strategy;

import com.bowling.game.Player;
import com.bowling.game.Roll;
import com.bowling.game.exception.BowlingException;

/**
 * The interface for objects which contain a behavior
 * to manage the roll
 *
 * @author Pedro Ferri
 */
public interface RollManagement {

    /**
     * Add a new roll into available frames
     *
     * @param player Player
     * @param roll Roll with all information need
     * @throws BowlingException if any data does not comply with the system standards.
     */
    void addRollIntoFrames(Player player, Roll roll) throws BowlingException;


    /**
     * Get the custom pinFall value to print
     * @param roll Current roll
     * @param pinFall The text informed by the user
     * @return New value converted into a custom print value
     */
    String getCustomPinFall(Roll roll, String pinFall);

}
