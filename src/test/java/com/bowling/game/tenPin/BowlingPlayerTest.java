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

        for (int i = 1; i <= POSSIBLE_ROLLS_TEN_BOWLING; i++) {
            Roll roll = new BowlingRoll(gameStrategy);
            roll.setPinFall("10");
            player.addRollIntoFrames(roll);
        }
    }

    @Test(expected = BowlingException.class)
    public void whenAddLessRollsIntoFrames_thenExceptThrow() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);

        for (int i = 1; i <= POSSIBLE_ROLLS_TEN_BOWLING / 2; i++) {
            Roll roll = new BowlingRoll(gameStrategy);
            roll.setPinFall("10");
            player.addRollIntoFrames(roll);
        }
        player.calculateScore();
    }

    @Test()
    public void whenCalculateScoreWithNoSpare_thenExcept120Score() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);

        for (int i = 1; i <= POSSIBLE_ROLLS_TEN_BOWLING; i++) {
            Roll roll = new BowlingRoll(gameStrategy);
            roll.setPinFall("4");
            player.addRollIntoFrames(roll);
        }
        player.calculateScore();
        Assert.assertEquals(84, getScoreLastFrame(player.getFrames()));
    }


    @Test()
    public void whenCalculateScoreWithSpare_thenExcept150Score() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);
        for (int i = 1; i <= POSSIBLE_ROLLS_TEN_BOWLING; i++) {
            Roll roll = new BowlingRoll(gameStrategy);
            roll.setPinFall("5");
            player.addRollIntoFrames(roll);
        }

        player.calculateScore();
        Assert.assertEquals(150, getScoreLastFrame(player.getFrames()));
    }

    @Test()
    public void whenCalculateScoreUsingLimitFramesWithStrikeRolls_thenExcept300Score() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);
        for (int i = 1; i <= POSSIBLE_ROLLS_STRIKE_TEN_BOWLING; i++) {
            Roll roll = new BowlingRoll(gameStrategy);
            roll.setPinFall(BowlingTenPinEnum.MAX_PINS.toString());
            player.addRollIntoFrames(roll);
        }

        player.calculateScore();
        Assert.assertEquals(300, getScoreLastFrame(player.getFrames()));
    }

    @Test(expected = BowlingException.class)
    public void whenCalculateScoreWithTooMuchFrames_thenExceptThrow() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);
        for (int i = 1; i <= (POSSIBLE_ROLLS_TEN_BOWLING + 1); i++) {
            Roll roll = new BowlingRoll(gameStrategy);
            roll.setPinFall(BowlingTenPinEnum.MAX_PINS.toString());
            player.addRollIntoFrames(roll);
        }

        player.calculateScore();
    }
}