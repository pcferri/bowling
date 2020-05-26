package com.bowling.game.strategy.tenPin;

import com.bowling.game.*;
import com.bowling.game.enuns.BowlingEnum;
import com.bowling.game.exception.BowlingException;
import com.bowling.game.parser.GameParser;
import com.bowling.game.strategy.GameStrategy;
import com.bowling.game.strategy.GameValidation;

import java.util.List;

/**
 * Implementation of the TextParser to a Ten-Pin Bowling Game
 *
 * @author Pedro Ferri
 */
public class TenPinGameTextParser implements GameParser {

    private final GameValidation gameValidation;
    private final GameStrategy gameStrategy;

    public TenPinGameTextParser(GameValidation gameValidation, GameStrategy gameStrategy){
        this.gameValidation = gameValidation;
        this.gameStrategy = gameStrategy;
    }

    @Override
    public Game parse(List<String> fileContent) throws BowlingException {
        Game game = new BowlingGame(this.gameStrategy);

        if (fileContent.size() == 0) {
            throw new BowlingException(BowlingEnum.ERROR_FILE_DOES_NOT_CONTAIN_LINES.toString());
        }

        for (String rollToParse : fileContent) {
            String[] playerRoll = rollToParse.split("\t");
            this.gameValidation.validatePlayerRoll(playerRoll);

            Player player = game.getPlayer(playerRoll[0]);
            Roll roll = new BowlingRoll(this.gameStrategy);
            roll.setPinFall(playerRoll[1]);
            player.addRollIntoFrames(roll);
        }
        this.gameValidation.validateLessFrames(game);
        return game;
    }
}