package com.bowling.game.strategy;

import com.bowling.game.parser.GameParser;
import com.bowling.game.services.GameReportingService;

/**
 * The interface for objects which contain a behavior
 * to manage the game strategy
 *
 * @author Pedro Ferri
 */
public interface GameStrategy extends ScoreCalculation, GameParser, GameReportingService, RollManagement {

}
