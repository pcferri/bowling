package com.bowling.game.parser;

import com.bowling.game.Game;
import com.bowling.game.exception.BowlingException;
import com.bowling.game.strategy.GameStrategy;

import java.util.List;

/**
 * Parse a text content file into a Game
 *
 * @author Pedro Ferri
 */
public final class BowlingGameTextParser implements GameParser {

    private final GameStrategy gameStrategy;

    public BowlingGameTextParser (GameStrategy gameStrategy){
        this.gameStrategy = gameStrategy;
    }

    /**
     * Parse a text content file into a Game
     *
     * @param fileContent content of the file
     * @return Game object ready to be used
     * @throws BowlingException if any data does not comply with the system standards.
     */
    @Override
    public Game parse(List<String> fileContent) throws BowlingException {
        return gameStrategy.parse(fileContent);
    }


}