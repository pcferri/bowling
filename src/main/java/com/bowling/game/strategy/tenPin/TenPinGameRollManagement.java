package com.bowling.game.strategy.tenPin;

import com.bowling.game.BowlingFrame;
import com.bowling.game.Frame;
import com.bowling.game.Player;
import com.bowling.game.Roll;
import com.bowling.game.enuns.BowlingEnum;
import com.bowling.game.exception.BowlingException;
import com.bowling.game.strategy.GameValidation;
import com.bowling.game.strategy.RollManagement;
import com.bowling.game.strategy.tenPin.enuns.BowlingTenPinEnum;

/**
 * Implementation of the RollManagement to a Ten-Pin Bowling Game
 *
 * @author Pedro Ferri
 */
public class TenPinGameRollManagement implements RollManagement {

    private final GameValidation gameValidation;

    public TenPinGameRollManagement(GameValidation gameValidation){
        this.gameValidation = gameValidation;
    }

    @Override
    public void addRollIntoFrames(Player player, Roll roll) throws BowlingException {
        Frame lastFrame = null;
        boolean createNewFrame = false;

        if (player.getFrames().size() > 0) {
            lastFrame = player.getFrames()
                    .stream()
                    .reduce((a, b) -> b)
                    .orElseThrow(() -> new BowlingException(BowlingEnum.INVALID_NUMBERS_OF_FRAMES_FOUND.toString()));

            createNewFrame = gameValidation.hasReachedLimitOfRolls(player, lastFrame);
        }
        createNewFrame = gameValidation.hasStrikePreviousFrame(lastFrame, createNewFrame);

        if (createNewFrame || lastFrame == null) {
            int nextFrameNumber = lastFrame != null ? lastFrame.getCurrentFrame() + 1 : 1;
            lastFrame = new BowlingFrame();
            lastFrame.setCurrentFrame(nextFrameNumber);
            lastFrame.setPlayer(player);
            player.getFrames().add(lastFrame);

            if(player.getFrames().size() > 1) {
                checkIfPreviousFrameHasSpare(lastFrame);
            }
        }

        gameValidation.existsMoreRollsThanAllowed(player.getFrames(), lastFrame);

        lastFrame.setPlayer(player);
        lastFrame.getRolls().add(roll);
    }

    /**
     * Check if the previous frame has a spare
     *
     * @param frame current frame to validation
     */
    private void checkIfPreviousFrameHasSpare(Frame frame) throws BowlingException {
        Frame lastFrame = frame.getPreviousFrame();
        int totalPointsLastFrame = lastFrame.getRolls().stream().mapToInt(Roll::getPoints).sum();
        if (totalPointsLastFrame >= BowlingTenPinEnum.MAX_PINS.toInt()) {
            lastFrame.setSpare(true);
            if (lastFrame.getRollSize() > 1) {
                Roll lastRoll = lastFrame.getRolls()
                        .stream()
                        .reduce((a, b) -> b)
                        .orElseThrow(() -> new BowlingException(BowlingEnum.INVALID_NUMBERS_OF_FRAMES_FOUND.toString()));
                lastRoll.setPinFall("/");
            }
        }
    }

    @Override
    public String getCustomPinFall(Roll roll, String pinFall) {
        String result;
        switch (pinFall.toUpperCase()) {
            case "F":
            case "-":
                roll.setPoints(0);
                result = pinFall;
                break;
            case "10":
                roll.setPoints(10);
                result = "X";
                break;
            case "/":
                result = pinFall;
                break;
            case "X":
                roll.setPoints(BowlingTenPinEnum.MAX_PINS.toInt());
                result = pinFall;
                break;
            default:
                roll.setPoints(Integer.parseInt(pinFall));
                result = pinFall;
        }

        return result;
    }
}