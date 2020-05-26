package com.bowling;

import com.bowling.game.Game;
import com.bowling.game.enuns.BowlingEnum;
import com.bowling.game.exception.BowlingException;
import com.bowling.game.services.BowlingReportingTextService;
import com.bowling.game.services.BowlingService;
import com.bowling.game.services.GameReportingService;
import com.bowling.game.services.GameService;
import com.bowling.game.strategy.GameStrategy;
import com.bowling.game.strategy.tenPin.TenPinGameStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Bowling main class to start the application
 *
 * @author Pedro Ferri
 */
class BowlingApp {

    /**
     * Logger to user to print the results in console
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * Main class to start the example of Bowling
     *
     * @param args Not necessary to this example
     */
    public static void main(String[] args) {
        try {
            if(args.length == 0){
                throw new IOException();
            }

            GameStrategy tenPinStrategy = new TenPinGameStrategy();
            GameService bowlingService = new BowlingService(tenPinStrategy);
            GameReportingService bowlingReportingTextService = new BowlingReportingTextService(tenPinStrategy);

            Game game = bowlingService.processFileIntoGame(args[0]);
            game.calculateScore();
            logger.info(bowlingReportingTextService.generateReport(game));
        } catch (BowlingException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(BowlingEnum.ERROR_LOAD_FILE);
        }
    }
}