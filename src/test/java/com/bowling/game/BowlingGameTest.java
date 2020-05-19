package com.bowling.game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Validate the getPlayer method from the Game
 *
 * @author Pedro Ferri
 */
public class BowlingGameTest {

    private Game game;

    @Before
    public void before() {
        game = new BowlingGame();

    }

    @Test
    public void whenAddManyPlayersAndGetFromGame_thenExpectSamePlayer() {
        Player player = new BowlingPlayer();
        player.setName("John");

        Player player2 = new BowlingPlayer();
        player2.setName("John2");

        game.getPlayers().add(player);
        game.getPlayers().add(player2);

        Player playerFound = game.getPlayer("John");

        Assert.assertEquals(player, playerFound);
    }

    @Test
    public void whenAddManyPlayersAndGetNewFromGame_thenExpectNewPlayer() {
        Player player = new BowlingPlayer();
        player.setName("John");

        Player player2 = new BowlingPlayer();
        player2.setName("John2");

        game.getPlayers().add(player);
        game.getPlayers().add(player2);

        Player playerFound = game.getPlayer("John3");

        Assert.assertNotEquals(player, playerFound);
    }
}