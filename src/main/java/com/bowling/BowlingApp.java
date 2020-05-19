package com.bowling;

import com.bowling.game.Game;
import com.bowling.game.enuns.BowlingEnum;
import com.bowling.game.exception.BowlingException;
import com.bowling.game.services.BowlingReportingTextService;
import com.bowling.game.services.BowlingService;
import com.bowling.game.services.GameReportingService;
import com.bowling.game.services.GameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
     * Bowling Service to process the game
     */
    private static final GameService bowlingService = new BowlingService();

    /**
     * Game Reporting Service to print the results
     */
    private static final GameReportingService bowlingReportingTextService = new BowlingReportingTextService();

    /**
     * Main class to start the example of Bowling
     *
     * @param args Not necessary to this example
     */
    public static void main(String[] args) {
        try {
            logger.info(BowlingEnum.PLEASE_TYPE_THE_PATH_OF_THE_TXT_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String filePath = reader.readLine();

            Game game = bowlingService.processFileIntoGame(filePath);
            game.calculateScore();
            logger.info(bowlingReportingTextService.generateReport(game));
        } catch (BowlingException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(BowlingEnum.ERROR_LOAD_FILE);
        }
    }
}