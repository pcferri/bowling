package com.bowling.game.services;

import com.bowling.file.FileManager;
import com.bowling.file.TextFileManager;
import com.bowling.game.Game;
import com.bowling.game.exception.BowlingException;
import com.bowling.game.parser.BowlingGameTextParser;
import com.bowling.game.parser.GameParser;

import java.io.IOException;

/**
 * Manage main responsibilities of Game
 *
 * @author Pedro Ferri
 */
public class BowlingService implements GameService {

    @Override
    public Game processFileIntoGame(String fileName) throws BowlingException, IOException {
        FileManager file = new TextFileManager();
        GameParser gameParser = new BowlingGameTextParser();

        return gameParser.parse(file.loadFileContent(fileName));
    }
}
