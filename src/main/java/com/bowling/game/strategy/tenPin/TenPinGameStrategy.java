package com.bowling.game.strategy.tenPin;

import com.bowling.game.Frame;
import com.bowling.game.Game;
import com.bowling.game.Player;
import com.bowling.game.Roll;
import com.bowling.game.exception.BowlingException;
import com.bowling.game.parser.GameParser;
import com.bowling.game.services.GameReportingService;
import com.bowling.game.strategy.GameStrategy;
import com.bowling.game.strategy.GameValidation;
import com.bowling.game.strategy.ScoreCalculation;

import java.util.List;

/**
 * Implementation of the GameStrategy to a Ten-Pin Bowling Game
 *
 * @author Pedro Ferri
 */
public class TenPinGameStrategy implements GameStrategy {

    private final ScoreCalculation scoreCalculation;
    private final TenPinGameRollManagement rollManagement;
    private final GameReportingService reportingText;
    private final GameParser gameTextParser;

    public TenPinGameStrategy(){
        GameValidation gameValidation = new TenPinGameValidation();
        this.scoreCalculation = new TenPinGameScoreCalculation();
        this.rollManagement = new TenPinGameRollManagement(gameValidation);
        this.reportingText = new TenPinGameReportingText();
        this.gameTextParser = new TenPinGameTextParser(gameValidation, this);
    }

    @Override
    public void addRollIntoFrames(Player player, Roll roll) throws BowlingException {
        rollManagement.addRollIntoFrames(player, roll);
    }

    @Override
    public String getCustomPinFall(Roll roll, String pinFall) {
        return rollManagement.getCustomPinFall(roll, pinFall);
    }

    @Override
    public String generateReport(Game game) {
        return reportingText.generateReport(game);
    }

    @Override
    public void calculateScore(Player player) throws BowlingException {
        scoreCalculation.calculateScore(player);
    }

    public void calculateScore(Frame frame) throws BowlingException {
        scoreCalculation.calculateScore(frame);
    }

    @Override
    public void calculateScore(Game game) throws BowlingException {
        scoreCalculation.calculateScore(game);
    }

    @Override
    public Game parse(List<String> fileContent) throws BowlingException {
        return this.gameTextParser.parse(fileContent);
    }
}