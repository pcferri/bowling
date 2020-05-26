package com.bowling.game.strategy.tenPin;

import com.bowling.game.Frame;
import com.bowling.game.Game;
import com.bowling.game.Player;
import com.bowling.game.Roll;
import com.bowling.game.enuns.BowlingEnum;
import com.bowling.game.exception.BowlingException;
import com.bowling.game.strategy.ScoreCalculation;
import com.bowling.game.strategy.tenPin.enuns.BowlingTenPinEnum;

/**
 * Implementation of the ScoreCalculation to a Ten-Pin Bowling Game
 *
 * @author Pedro Ferri
 */
public class TenPinGameScoreCalculation implements ScoreCalculation {

    @Override
    public void calculateScore(Game game) throws BowlingException {
        for (Player player : game.getPlayers()) {
            player.calculateScore();
        }
    }

    @Override
    public void calculateScore(Player player) throws BowlingException {
        if (player.getFrames().size() != BowlingTenPinEnum.LIMIT_FRAMES.toInt()) {
            throw new BowlingException(BowlingEnum.THE_NUMBER_OF_FRAMES_IS_DIFFERENT_THAN_CONFIGURED.toString());
        }
        for (Frame frame : player.getFrames()) {
            frame.calculateScore();
        }
    }

    @Override
    public void calculateScore(Frame frame) throws BowlingException {
        int scoreRoll = frame.getRolls().stream().mapToInt(Roll::getPoints).sum();

        //if the frame spare and has only 1 roll get next 2 rolls
        if (frame.isSpare() && frame.getRolls().size() == 1) {
            Frame nextFrame = frame.getNextFrame();

            //Get ony 2 rolls in the next frame
            if (nextFrame.getCurrentFrame() == BowlingTenPinEnum.LIMIT_FRAMES.toInt()) {
                scoreRoll += nextFrame.getRolls()
                        .stream()
                        .limit(2)
                        .mapToInt(Roll::getPoints)
                        .sum();
            } else {
                scoreRoll += nextFrame.getRolls()
                        .stream()
                        .mapToInt(Roll::getPoints)
                        .sum();
            }

            //If the next has only 1 roll, get in the next frame
            if (nextFrame.getRolls().size() == 1) {
                scoreRoll += nextFrame.getNextFrame().getRolls()
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new BowlingException(BowlingEnum.INVALID_NUMBERS_OF_FRAMES_FOUND.toString()))
                        .getPoints();
            }
        } else {
            //if the frame spare and has 2 rolls get next 1 roll only
            if (frame.isSpare() && frame.getRolls().size() == 2) {
                scoreRoll += frame.getNextFrame().getRolls()
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new BowlingException(BowlingEnum.INVALID_NUMBERS_OF_FRAMES_FOUND.toString()))
                        .getPoints();
            }
        }

        //sum the score with previous score frame
        if (frame.getCurrentFrame() > 1) {
            scoreRoll += frame.getPreviousFrame().getScore();
        }

        frame.setScore(scoreRoll);
    }
}