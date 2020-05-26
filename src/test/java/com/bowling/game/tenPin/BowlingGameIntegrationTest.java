package com.bowling.game.tenPin;

import com.bowling.game.Game;
import com.bowling.game.exception.BowlingException;
import com.bowling.game.services.BowlingService;
import com.bowling.game.services.GameService;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

/**
 * Integration test to test all examples informed on the PDF
 */
public class BowlingGameIntegrationTest extends AbstractUtilTest {


    /**
     * Used to process file into a Game
     */
    private final GameService bowlingService = new BowlingService(gameStrategy);

    /**
     * Load the file perfect-score.txt from the resource path, convert it into a Game, and verify the results.
     *
     * @throws BowlingException if any data does not comply with the system standards.
     * @throws IOException      File not found
     */
    @Test
    public void whenProcessPerfectScoreInput_thenExcept300Points() throws BowlingException, IOException {
        Game game = getGameFromInputWithScoreCalculated("perfect-score.txt");

        Assert.assertEquals(300, getScoreLastFrame(game, 1));
    }

    /**
     * Load the file zero-score.txt from the resource path, convert it into a Game, and verify the results.
     *
     * @throws BowlingException if any data does not comply with the system standards.
     * @throws IOException      File not found
     */
    @Test
    public void whenProcessZeroScoreInput_thenExceptZeroPoints() throws BowlingException, IOException {
        Game game = getGameFromInputWithScoreCalculated("zero-score.txt");

        Assert.assertEquals(0, getScoreLastFrame(game, 1));
    }

    /**
     * Load the file sample-input.txt from the resource path, convert it into a Game, and verify the results.
     *
     * @throws BowlingException if any data does not comply with the system standards.
     * @throws IOException      File not found
     */
    @Test
    public void whenProcessSampleInput_thenExcept167And151Points() throws BowlingException, IOException {
        Game game = getGameFromInputWithScoreCalculated("sample-input.txt");

        Assert.assertEquals(167, getScoreLastFrame(game, 1));
        Assert.assertEquals(151, getScoreLastFrame(game, 2));
    }

    /**
     * Load the file sample-youtube.txt from the resource path, convert it into a Game, and verify the results.
     *
     * @throws BowlingException if any data does not comply with the system standards.
     * @throws IOException      File not found
     */
    @Test
    public void whenProcessSampleYoutubeInput_thenExcept170Points() throws BowlingException, IOException {
        Game game = getGameFromInputWithScoreCalculated("sample-youtube.txt");

        Assert.assertEquals(170, getScoreLastFrame(game, 1));
    }

    /**
     * Load the file, process it into a Game and calculate the score
     *
     * @param nameOfTestFile name of the file to be load from the resource test
     * @return Game object ready to be used to print the results
     * @throws BowlingException if any data does not comply with the system standards.
     * @throws IOException      File not found
     */
    private Game getGameFromInputWithScoreCalculated(String nameOfTestFile) throws BowlingException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(nameOfTestFile);
        if(url == null){
            throw new IOException();
        }
        Game game = bowlingService.processFileIntoGame(url.getPath());

        game.calculateScore();
        return game;
    }

}
