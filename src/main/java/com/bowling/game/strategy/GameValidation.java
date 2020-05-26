package com.bowling.game.strategy;

import com.bowling.game.Frame;
import com.bowling.game.Game;
import com.bowling.game.Player;
import com.bowling.game.exception.BowlingException;

import java.util.List;

/**
 * The interface for objects which contain a behavior
 * to validate the Bowling Game
 *
 * @author Pedro Ferri
 */
public interface GameValidation {

    /**
     * Verify if has a strike in the previous frame
     * @param frame Current frame
     * @param createNewFrame This variable if to keep the value after the check
     * @return True if the previous frame has a strike, otherwise send back the value of createNewFrame
     */
    boolean hasStrikePreviousFrame(Frame frame, boolean createNewFrame);

    /**
     * Check if has more rolls than allowed
     *
     * @param frame frame to validate
     * @throws BowlingException if any data does not comply with the system standards.
     */
    void existsMoreRollsThanAllowed(List<Frame> frames, Frame frame) throws BowlingException;

    /**
     * Validate if the Roll of this Player has the information needed
     *
     * @param playerRoll String[] with two-position, first with name and second with the roll
     * @throws BowlingException if any data does not comply with the system standards.
     */
    void validatePlayerRoll(String[] playerRoll) throws BowlingException;

    /**
     * Validate if exists inside of this Game fewer frames than needed
     *
     * @param game Current game object to validate
     * @throws BowlingException if any data does not comply with the system standards.
     */
    void validateLessFrames(Game game) throws BowlingException;

    /**
     * Verify if the limit of rolls for this frame was reached
     * @param player Player to validate the size of the frames
     * @param frame Frame to validate size of the rolls
     * @return True if the limit was reached to be able to create a new frame
     */
    boolean hasReachedLimitOfRolls(Player player, Frame frame);
}
