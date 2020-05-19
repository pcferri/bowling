package com.bowling.game.services;

import com.bowling.game.Game;
import com.bowling.game.exception.BowlingException;

import java.io.IOException;

/**
 * The interface for objects which contain a behavior
 * process the file into a Game
 *
 * @author Pedro Ferri
 */
public interface GameService {

    /**
     * Process the file and convert into a Game Object
     *
     * @param fileName path of the file
     * @return Game Game prepared to be used
     * @throws BowlingException if any data does not comply with the system standards.
     * @throws IOException      File not found
     */
    Game processFileIntoGame(String fileName) throws BowlingException, IOException;
}
