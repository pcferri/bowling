package com.bowling.game;

import com.bowling.game.enuns.BowlingEnum;

import java.util.List;

/**
 * Abstract util class to share main methods to the tests
 *
 * @author Pedro Ferri
 */
class AbstractUtilTest {

    protected int getScoreLastFrame(Game game, int playerPosition) {
        return getScoreLastFrame(game.getPlayers().get(playerPosition - 1).getFrames());
    }

    protected int getLimitRolls() {
        return (BowlingEnum.LIMIT_FRAMES.toInt() - 1) *
                BowlingEnum.ROLLS_NORMAL_FRAME.toInt() +
                BowlingEnum.ROLLS_LAST_FRAME.toInt();
    }


    protected int getScoreLastFrame(List<Frame> frames) {
        return frames.stream().reduce((a, b) -> b).get().getScore();
    }


    protected Frame getFrameWithStrike(int currentFrame, int totalRolls, Player player) {
        Frame frame = new BowlingFrame();
        frame.setSpare(true);
        frame.setPlayer(player);
        frame.setCurrentFrame(currentFrame);
        for (int i = 1; i <= totalRolls; i++) {
            frame.getRolls().add(getRollWithStrike());
        }
        return frame;
    }

    protected Frame getFrameWithZeroPoints(int currentFrame, int totalRolls, Player player) {
        Frame frame = new BowlingFrame();
        frame.setSpare(true);
        frame.setPlayer(player);
        frame.setCurrentFrame(currentFrame);
        for (int i = 1; i <= totalRolls; i++) {
            frame.getRolls().add(getRollWithZeroPoints());
        }
        return frame;
    }

    protected Frame getFrameWithCustomPinFall(int currentFrame, int totalRolls, Player player, String pinFall) {
        Frame frame = new BowlingFrame();
        frame.setSpare(true);
        frame.setPlayer(player);
        frame.setCurrentFrame(currentFrame);
        for (int i = 1; i <= totalRolls; i++) {
            frame.getRolls().add(getRoll(pinFall));
        }
        return frame;
    }

    protected Roll getRollWithStrike() {
        return getRoll("10");
    }

    protected Roll getRoll(String pinFall) {
        Roll roll = new BowlingRoll();
        roll.setPinFall(pinFall);
        return roll;
    }

    protected Roll getRollWithZeroPoints() {
        return getRoll("0");
    }
}
