package com.bowling.game.tenPin;

import com.bowling.game.Frame;
import com.bowling.game.Game;
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

    final int POSSIBLE_ROLLS_TEN_BOWLING = ((BowlingTenPinEnum.LIMIT_FRAMES.toInt() - 1) * BowlingTenPinEnum.ROLLS_NORMAL_FRAME.toInt()) +  BowlingTenPinEnum.ROLLS_LAST_FRAME.toInt();
    final int POSSIBLE_ROLLS_STRIKE_TEN_BOWLING = (BowlingTenPinEnum.LIMIT_FRAMES.toInt() - 1) +  BowlingTenPinEnum.ROLLS_LAST_FRAME.toInt();
    final GameStrategy gameStrategy = new TenPinGameStrategy();

    int getScoreLastFrame(Game game, int playerPosition) throws BowlingException {
        return getScoreLastFrame(game.getPlayers().get(playerPosition - 1).getFrames());
    }


    int getScoreLastFrame(List<Frame> frames) throws BowlingException{
        return frames
                .stream()
                .reduce((a, b) -> b)
                .orElseThrow(() -> new BowlingException(BowlingEnum.INVALID_NUMBERS_OF_FRAMES_FOUND.toString()))
            .getScore();
    }

    String getPinFallLastFrame(List<Frame> frames) throws BowlingException{
        return frames
                .stream()
                .reduce((a, b) -> b)
                .orElseThrow(() -> new BowlingException(BowlingEnum.INVALID_NUMBERS_OF_FRAMES_FOUND.toString()))
                .getRolls()
                .stream()
                .reduce((a, b) -> b)
                .orElseThrow(() -> new BowlingException(BowlingEnum.INVALID_NUMBERS_OF_FRAMES_FOUND.toString()))
                .getPinFall();
    }
}
