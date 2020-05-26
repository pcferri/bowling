package com.bowling.game.tenPin;

import com.bowling.game.BowlingPlayer;
import com.bowling.game.Frame;
import com.bowling.game.Player;
import com.bowling.game.exception.BowlingException;
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
        for (int i = 1; i <= 9; i++) {
            player.getFrames().add(getFrameWithStrike(i, 1, player));
        }
        player.getFrames().add(getFrameWithStrike(10, 3, player));

        for (Frame frame : player.getFrames()) {
            frame.calculateScore();
        }

        Assert.assertEquals(300, getScoreLastFrame(player.getFrames()));
    }

    @Test
    public void whenCalculateScoreWithZeroScore_thenExceptZeroPoints() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);
        for (int i = 1; i <= 9; i++) {
            player.getFrames().add(getFrameWithZeroPoints(i, 1, player));
        }
        player.getFrames().add(getFrameWithZeroPoints(10, 3, player));

        for (Frame frame : player.getFrames()) {
            frame.calculateScore();
        }

        Assert.assertEquals(0, getScoreLastFrame(player.getFrames()));
    }

    @Test(expected = BowlingException.class)
    public void whenCalculateScoreWithLessFrame_thenExceptThrow() throws BowlingException {
        Player player = new BowlingPlayer(gameStrategy);
        player.getFrames().add(getFrameWithStrike(1, 1, player));

        for (Frame frame : player.getFrames()) {
            frame.calculateScore();
        }
    }
}