package com.bowling.game;

import java.util.List;

/**
 * The interface for objects which contain a behavior
 * to manage Game
 *
 * @author Pedro Ferri
 */
public interface Game extends ProcessScore {

    /**
     * Get all players
     *
     * @return List of Player objects
     */
    List<Player> getPlayers();

    /**
     * Get the player from the Game
     *
     * @param playerName name of the player
     * @return The Player object found from the Game
     */
    Player getPlayer(String playerName);
}
