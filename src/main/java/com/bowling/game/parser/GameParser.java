package com.bowling.game.parser;

import com.bowling.game.Game;
import com.bowling.game.exception.BowlingException;

import java.util.List;

/**
 * The interface for objects which contain a behavior
 * to parse a file into a Game
 *
 * @author Pedro Ferri
 */
public interface GameParser {

    /**
     * Parse file content into a game
     *
     * @param fileContent content of the file
     * @return Game ready to be used
     * @throws BowlingException if any data does not comply with the system standards.
     */
    Game parse(List<String> fileContent) throws BowlingException;
}
