package com.bowling.game.tenPin;

import com.bowling.game.*;
import com.bowling.game.exception.BowlingException;
import com.bowling.game.strategy.tenPin.enuns.BowlingTenPinEnum;
import org.junit.Assert;
import org.junit.Test;


/**
 * Cover the calculateScore method of the BowlingFrame class
 *
 * @author Pedro Ferri
 */
public class BowlingFrameTest extends AbstractUtilTest {

    @Test
    public void whenCalculateScoreWithPerfectScore_thenExcept300Points() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);
        for (int i = 1; i <= (POSSIBLE_ROLLS_STRIKE_TEN_BOWLING); i++) {
            Roll roll = new BowlingRoll(gameStrategy);
            roll.setPinFall(BowlingTenPinEnum.MAX_PINS.toString());
            player.addRollIntoFrames(roll);
        }

        for (Frame frame : player.getFrames()) {
            frame.calculateScore();
        }

        Assert.assertEquals(300, getScoreLastFrame(player.getFrames()));
    }

    @Test
    public void whenCalculateScoreWithZeroScore_thenExceptZeroPoints() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);
        for (int i = 1; i <= (POSSIBLE_ROLLS_TEN_BOWLING); i++) {
            Roll roll = new BowlingRoll(gameStrategy);
            roll.setPinFall("0");
            player.addRollIntoFrames(roll);
        }

        for (Frame frame : player.getFrames()) {
            frame.calculateScore();
        }

        Assert.assertEquals(0, getScoreLastFrame(player.getFrames()));
    }

    @Test(expected = BowlingException.class)
    public void whenCalculateScoreWithLessFrame_thenExceptThrow() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);

        Roll roll = new BowlingRoll(gameStrategy);
        roll.setPinFall("10");
        player.addRollIntoFrames(roll);


        for (Frame frame : player.getFrames()) {
            frame.calculateScore();
        }
    }


    @Test
    public void whenCalculateScoreWithPerfectScore_thenExceptXPinFallLastRoll() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);
        for (int i = 1; i <= (POSSIBLE_ROLLS_STRIKE_TEN_BOWLING); i++) {
            Roll roll = new BowlingRoll(gameStrategy);
            roll.setPinFall(BowlingTenPinEnum.MAX_PINS.toString());
            player.addRollIntoFrames(roll);
        }

        for (Frame frame : player.getFrames()) {
            frame.calculateScore();
        }

        Assert.assertEquals("X", getPinFallLastFrame(player.getFrames()));
    }

    @Test
    public void whenCalculateScoreWithPerfectScore_thenExceptSlashSecondRollEachFrame() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);
        for (int i = 1; i <= (POSSIBLE_ROLLS_TEN_BOWLING / 2 -1); i++) {
            Roll roll = new BowlingRoll(gameStrategy);
            roll.setPinFall("8");
            player.addRollIntoFrames(roll);

            Roll roll2 = new BowlingRoll(gameStrategy);
            roll2.setPinFall("2");
            player.addRollIntoFrames(roll2);
        }

        Roll roll = new BowlingRoll(gameStrategy);
        roll.setPinFall("8");
        player.addRollIntoFrames(roll);

        Roll roll2 = new BowlingRoll(gameStrategy);
        roll2.setPinFall("2");
        player.addRollIntoFrames(roll2);

        Roll roll3 = new BowlingRoll(gameStrategy);
        roll3.setPinFall("8");
        player.addRollIntoFrames(roll3);

        for (Frame frame : player.getFrames()) {
            frame.calculateScore();
        }

        for (Frame frame : player.getFrames()) {
            Assert.assertEquals("/", frame.getRolls().get(1).getPinFall());
        }
    }


    @Test
    public void whenCalculateScoreWithPerfectScore_thenExceptSlashSecondRollEachFrameXXXXXXXXXXXXXXXXX() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);
        for (int i = 1; i <= (POSSIBLE_ROLLS_TEN_BOWLING / 2 -1); i++) {
            Roll roll = new BowlingRoll(gameStrategy);
            roll.setPinFall("8");
            player.addRollIntoFrames(roll);

            Roll roll2 = new BowlingRoll(gameStrategy);
            roll2.setPinFall("1");
            player.addRollIntoFrames(roll2);
        }

        Roll roll = new BowlingRoll(gameStrategy);
        roll.setPinFall("X");
        player.addRollIntoFrames(roll);

        Roll roll2 = new BowlingRoll(gameStrategy);
        roll2.setPinFall("8");
        player.addRollIntoFrames(roll2);

        Roll roll3 = new BowlingRoll(gameStrategy);
        roll3.setPinFall("1");
        player.addRollIntoFrames(roll3);

        for (Frame frame : player.getFrames()) {
            frame.calculateScore();
        }

        Assert.assertEquals("1", getPinFallLastFrame(player.getFrames()));
    }
}