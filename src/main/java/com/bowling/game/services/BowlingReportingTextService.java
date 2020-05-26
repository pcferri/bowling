package com.bowling.game.services;

import com.bowling.game.Game;
import com.bowling.game.strategy.GameStrategy;

/**
 * Build the Bowling reporting in text format
 *
 * @author Pedro Ferri
 */
public final class BowlingReportingTextService implements GameReportingService {

    private final GameStrategy gameStrategy;

    public BowlingReportingTextService(GameStrategy gameStrategy) {
        this.gameStrategy = gameStrategy;
    }

    /**
     * Generate the report in text format
     *
     * @param game Game object with score already calculate
     * @return Report in text format
     */
    @Override
    public String generateReport(Game game) {
        return this.gameStrategy.generateReport(game);
    }


}
