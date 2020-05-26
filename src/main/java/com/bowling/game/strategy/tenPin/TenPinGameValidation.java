package com.bowling.game.strategy.tenPin;

import com.bowling.game.Frame;
import com.bowling.game.Game;
import com.bowling.game.Player;
import com.bowling.game.Roll;
import com.bowling.game.enuns.BowlingEnum;
import com.bowling.game.exception.BowlingException;
import com.bowling.game.strategy.GameValidation;
import com.bowling.game.strategy.tenPin.enuns.BowlingTenPinEnum;

import java.util.List;

/**
 * Implementation of the validations to a Ten-Pin Bowling Game
 *
 * @author Pedro Ferri
 */
public class TenPinGameValidation implements GameValidation {

    @Override
    public boolean hasStrikePreviousFrame(Frame frame, boolean createNewFrame) {
        if (!createNewFrame && frame != null && frame.getCurrentFrame() < BowlingTenPinEnum.LIMIT_FRAMES.toInt()) {
            Roll previousRoll = frame.getRolls().get(frame.getRolls().size() - 1);
            if (BowlingTenPinEnum.LIMIT_FRAMES.toInt() == previousRoll.getPoints()) {
                createNewFrame = true;
            }
        }
        return createNewFrame;
    }

    @Override
    public void existsMoreRollsThanAllowed(List<Frame> frames, Frame frame) throws BowlingException {
        //Check if has more rolls than allowed
        if (frames.size() == BowlingTenPinEnum.LIMIT_FRAMES.toInt()) {
            if (frame.getRolls().size() >= BowlingTenPinEnum.ROLLS_LAST_FRAME.toInt()) {
                throw new BowlingException(BowlingEnum.ERROR_WRONG_ROLL.toString());
            }
        }
    }

    @Override
    public void validatePlayerRoll(String[] playerRoll) throws BowlingException {
        if (playerRoll.length != 2) {
            throw new BowlingException(BowlingEnum.ERROR_PLAYER_DOES_NOT_CONTAIN_INFORMATION.toString());
        }
        if (playerRoll[0].isEmpty() || playerRoll[1].isEmpty()) {
            throw new BowlingException(BowlingEnum.ERROR_PLAYER_DOES_NOT_CONTAIN_INFORMATION.toString());
        }

        String possibleRolls = "0,1,2,3,4,5,6,7,8,9,10,-,F,X";
        if (!possibleRolls.contains(playerRoll[1])) {
            throw new BowlingException(BowlingEnum.ROLL_NOT_ALLOWED_FOUND_TO_THE_PLAYER +" "+ playerRoll[0]);
        }
    }

    @Override
    public void validateLessFrames(Game game) throws BowlingException {
        for (Player player : game.getPlayers()) {
            int totalFrames = player.getFrames().size();
            if (totalFrames != BowlingTenPinEnum.LIMIT_FRAMES.toInt()) {
                throw new BowlingException(BowlingEnum.ERROR_WRONG_ROLL.toString());
            }
        }
    }

    @Override
    public boolean hasReachedLimitOfRolls(Player player, Frame frame) {
        return frame.getRolls().size() == BowlingTenPinEnum.ROLLS_NORMAL_FRAME.toInt() &&
                player.getFrames().size() < BowlingTenPinEnum.LIMIT_FRAMES.toInt();
    }
}