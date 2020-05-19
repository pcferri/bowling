package com.bowling.game.services;

import com.bowling.game.Game;

/**
 * Build the Bowling reporting in text format
 *
 * @author Pedro Ferri
 */
public interface GameReportingService {

    /**
     * Generate the report in text format
     *
     * @param game Game object with score already calculate
     * @return Report in text format
     */
    String generateReport(Game game);
}
