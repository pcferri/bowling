package com.bowling.game.tenPin;

import com.bowling.game.BowlingPlayer;
import com.bowling.game.BowlingRoll;
import com.bowling.game.Player;
import com.bowling.game.Roll;
import com.bowling.game.exception.BowlingException;
import com.bowling.game.strategy.tenPin.enuns.BowlingTenPinEnum;
import org.junit.Assert;
import org.junit.Test;

/**
 * Validate the BowlingPlayer class and tests cases for calculateScore
 *
 * @author Pedro Ferri
 */
public class BowlingPlayerTest extends AbstractUtilTest {

    @Test(expected = BowlingException.class)
    public void whenAddTooMuchRollsIntoFrames_thenExceptThrow() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);

        for (int i = 1; i <= getLimitRolls(); i++) {
            Roll roll = new BowlingRoll(gameStrategy);
            roll.setPinFall("10");
            player.addRollIntoFrames(roll);
        }
    }

    @Test(expected = BowlingException.class)
    public void whenAddLessRollsIntoFrames_thenExceptThrow() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);

        for (int i = 1; i <= getLimitRolls() / 2; i++) {
            Roll roll = new BowlingRoll(gameStrategy);
            roll.setPinFall("10");
            player.addRollIntoFrames(roll);
        }
        player.calculateScore();
    }

    @Test()
    public void whenCalculateScoreWithNoSpare_thenExcept120Score() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);
        for (int i = 1; i <= (BowlingTenPinEnum.LIMIT_FRAMES.toInt() - 1); i++) {
            player.getFrames().add(getFrameWithCustomPinFall(i, 1, player, "4"));
        }
        player.getFrames().add(getFrameWithCustomPinFall(BowlingTenPinEnum.LIMIT_FRAMES.toInt(), 3, player, "4"));

        player.calculateScore();
        Assert.assertEquals(120, getScoreLastFrame(player.getFrames()));
    }


    @Test()
    public void whenCalculateScoreWitSpare_thenExcept150Score() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);
        for (int i = 1; i <= (BowlingTenPinEnum.LIMIT_FRAMES.toInt() - 1); i++) {
            player.getFrames().add(getFrameWithCustomPinFall(i, 1, player, "5"));
        }
        player.getFrames().add(getFrameWithCustomPinFall(BowlingTenPinEnum.LIMIT_FRAMES.toInt(), 3, player, "5"));

        player.calculateScore();
        Assert.assertEquals(150, getScoreLastFrame(player.getFrames()));
    }

    @Test()
    public void whenCalculateScoreUsingLimitFramesWithStrikeRolls_thenExceptThrow() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);
        for (int i = 1; i <= (BowlingTenPinEnum.LIMIT_FRAMES.toInt() - 1); i++) {
            player.getFrames().add(getFrameWithStrike(i, 1, player));
        }
        player.getFrames().add(getFrameWithStrike(BowlingTenPinEnum.LIMIT_FRAMES.toInt(), 3, player));

        player.calculateScore();
        Assert.assertEquals(300, getScoreLastFrame(player.getFrames()));
    }

    @Test(expected = BowlingException.class)
    public void whenCalculateScoreWithTooMuchFrames_thenExceptThrow() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);
        for (int i = 1; i <= BowlingTenPinEnum.LIMIT_FRAMES.toInt(); i++) {
            player.getFrames().add(getFrameWithStrike(i, 1, player));
        }
        player.getFrames().add(getFrameWithStrike(BowlingTenPinEnum.LIMIT_FRAMES.toInt() + 1, 3, player));

        player.calculateScore();
    }
}