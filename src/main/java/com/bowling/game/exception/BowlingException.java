package com.bowling.game.exception;

/**
 * Internal implementation for inner errors for this Game
 *
 * @author Pedro Ferri
 */
public class BowlingException extends Exception {

    public BowlingException(String message) {
        super(message);
    }
}
