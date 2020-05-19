package com.bowling.game.parser;

import com.bowling.game.*;
import com.bowling.game.enuns.BowlingEnum;
import com.bowling.game.exception.BowlingException;

import java.util.List;

/**
 * Parse a text content file into a Game
 *
 * @author Pedro Ferri
 */
public class BowlingGameTextParser implements GameParser {

    /**
     * Parse a text content file into a Game
     *
     * @param fileContent content of the file
     * @return Game object ready to be used
     * @throws BowlingException if any data does not comply with the system standards.
     */
    @Override
    public Game parse(List<String> fileContent) throws BowlingException {
        Game game = new BowlingGame();

        if (fileContent.size() == 0) {
            throw new BowlingException(BowlingEnum.ERROR_FILE_DOES_NOT_CONTAIN_LINES.toString());
        }

        for (String rollToParse : fileContent) {
            String[] playerRoll = rollToParse.split("\t");
            validatePlayerRoll(playerRoll);

            Player player = game.getPlayer(playerRoll[0]);
            Roll roll = new BowlingRoll();
            roll.setPinFall(playerRoll[1]);
            player.addRollIntoFrames(roll);
        }
        validateLessFrames(game);
        return game;
    }

    /**
     * Validate if the Roll of this Player has the information needed
     *
     * @param playerRoll String[] with two-position, first with name and second with the roll
     * @throws BowlingException if any data does not comply with the system standards.
     */
    private void validatePlayerRoll(String[] playerRoll) throws BowlingException {
        if (playerRoll.length != 2) {
            throw new BowlingException(BowlingEnum.ERROR_PLAYER_DOES_NOT_CONTAIN_INFORMATION.toString());
        }
        if (playerRoll[0].isEmpty() || playerRoll[1].isEmpty()) {
            throw new BowlingException(BowlingEnum.ERROR_PLAYER_DOES_NOT_CONTAIN_INFORMATION.toString());
        }

        String possibleRolls = "0,1,2,3,4,5,6,7,8,9,10,-,F,X";
        if (!possibleRolls.contains(playerRoll[1])) {
            throw new BowlingException(BowlingEnum.ROLL_NOT_ALLOWED_FOUND_TO_THE_PLAYER + playerRoll[0]);
        }
    }

    /**
     * Validate if exists inside of this Game fewer frames than needed
     *
     * @param game Current game object to validate
     * @throws BowlingException if any data does not comply with the system standards.
     */
    private void validateLessFrames(Game game) throws BowlingException {
        for (Player player : game.getPlayers()) {
            int totalFrames = player.getFrames().size();
            if (totalFrames != BowlingEnum.LIMIT_FRAMES.toInt()) {
                throw new BowlingException(BowlingEnum.ERROR_WRONG_ROLL.toString());
            }
        }
    }
}