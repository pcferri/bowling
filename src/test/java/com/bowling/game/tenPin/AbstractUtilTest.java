package com.bowling.game.tenPin;

import com.bowling.game.*;
import com.bowling.game.enuns.BowlingEnum;
import com.bowling.game.exception.BowlingException;
import com.bowling.game.strategy.GameStrategy;
import com.bowling.game.strategy.tenPin.TenPinGameStrategy;
import com.bowling.game.strategy.tenPin.enuns.BowlingTenPinEnum;

import java.util.List;

/**
 * Abstract util class to share main methods to the tests
 *
 * @author Pedro Ferri
 */
class AbstractUtilTest {

    final GameStrategy gameStrategy = new TenPinGameStrategy();

    int getScoreLastFrame(Game game, int playerPosition) throws BowlingException {
        return getScoreLastFrame(game.getPlayers().get(playerPosition - 1).getFrames());
    }

    int getLimitRolls() {
        return (BowlingTenPinEnum.LIMIT_FRAMES.toInt() - 1) *
                BowlingTenPinEnum.ROLLS_NORMAL_FRAME.toInt() +
                BowlingTenPinEnum.ROLLS_LAST_FRAME.toInt();
    }


    int getScoreLastFrame(List<Frame> frames) throws BowlingException{
        return frames
                .stream()
                .reduce((a, b) -> b)
                .orElseThrow(() -> new BowlingException(BowlingEnum.INVALID_NUMBERS_OF_FRAMES_FOUND.toString()))
            .getScore();
    }


    Frame getFrameWithStrike(int currentFrame, int totalRolls, Player player) {
        Frame frame = new BowlingFrame();
        frame.setSpare(true);
        frame.setPlayer(player);
        frame.setCurrentFrame(currentFrame);
        for (int i = 1; i <= totalRolls; i++) {
            frame.getRolls().add(getRollWithStrike());
        }
        return frame;
    }

    Frame getFrameWithZeroPoints(int currentFrame, int totalRolls, Player player) {
        Frame frame = new BowlingFrame();
        frame.setSpare(true);
        frame.setPlayer(player);
        frame.setCurrentFrame(currentFrame);
        for (int i = 1; i <= totalRolls; i++) {
            frame.getRolls().add(getRollWithZeroPoints());
        }
        return frame;
    }

    Frame getFrameWithCustomPinFall(int currentFrame, int totalRolls, Player player, String pinFall) {
        Frame frame = new BowlingFrame();
        frame.setSpare(true);
        frame.setPlayer(player);
        frame.setCurrentFrame(currentFrame);
        for (int i = 1; i <= totalRolls; i++) {
            frame.getRolls().add(getRoll(pinFall));
        }
        return frame;
    }

    private Roll getRollWithStrike() {
        return getRoll("10");
    }

    private Roll getRoll(String pinFall) {
        Roll roll = new BowlingRoll(gameStrategy);
        roll.setPinFall(pinFall);
        return roll;
    }

    private Roll getRollWithZeroPoints() {
        return getRoll("0");
    }
}
