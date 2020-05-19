package com.bowling.game.parser;


import com.bowling.game.Game;
import com.bowling.game.exception.BowlingException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Cover the main actions of the BowlingGameTextParser class
 *
 * @author Pedro Ferri
 */
public class BowlingGameTextParserTest {

    private final GameParser gameParser = new BowlingGameTextParser();

    private List<String> generateInputText(String prefixNamePlayer, int players, int rolls, String pinFalls){
        List<String> fileContent = new ArrayList<>();

        for (int i = 1; i <= players; i++) {
            for (int j = 1; j <= rolls; j++) {
                if(prefixNamePlayer.isEmpty()){
                    fileContent.add("\t"+ pinFalls);
                }else{
                    fileContent.add(prefixNamePlayer + i + "\t"+ pinFalls);
                }
            }
        }
        return fileContent;
    }


    @Test(expected = BowlingException.class)
    public void whenParseAFileContentWithFewRolls_thenAssertionSucceeds() throws BowlingException {
       gameParser.parse(generateInputText("John",1, 1, "10"));
    }

    @Test(expected = BowlingException.class)
    public void whenParseAEmptyFileContent_thenExceptThrow() throws BowlingException {
        gameParser.parse(generateInputText("John",0, 1, "10"));
    }

    @Test()
    public void whenParseAPerfectScore_thenExceptOnePlayer() throws BowlingException {
        Game game = gameParser.parse(generateInputText("John",1, 12, "10"));
        Assert.assertEquals(1, game.getPlayers().size());
    }

    @Test(expected = BowlingException.class)
    public void whenParseAPerfectScoreWithNoNameOfPlayer_thenExceptTwoPlayer() throws BowlingException {
        gameParser.parse(generateInputText("",1, 12, "10"));
    }

    @Test()
    public void whenParseAPerfectScoreWithTwoPlayer_thenExceptTwoPlayer() throws BowlingException {
        Game game = gameParser.parse(generateInputText("John",2, 12, "10"));
        Assert.assertEquals(2, game.getPlayers().size());
    }

    @Test(expected = BowlingException.class)
    public void whenParseAPerfectScoreWithMoreThanAllowedRolls_thenExceptThrow() throws BowlingException {
        gameParser.parse(generateInputText("John",2, 13, "10"));
    }

    @Test()
    public void whenParseWithStrikeMaskedField_thenExceptOnePlayerWithTenAsFirstRoll() throws BowlingException {
        Game game = gameParser.parse(generateInputText("John",1, 12, "X"));
        Assert.assertEquals( 10, game.getPlayers().get(0).getFrames().get(0).getRolls().get(0).getPoints());
    }

    @Test()
    public void whenParseWithFaultAsMaskedField_thenExceptOnePlayerWithZeroAsFirstRoll() throws BowlingException {
        Game game = gameParser.parse(generateInputText("John",1, 21, "F"));
        Assert.assertEquals(0, game.getPlayers().get(0).getFrames().get(0).getRolls().get(0).getPoints());
    }

    @Test()
    public void whenParseWithMissMaskedField_thenExceptOnePlayerWithZeroAsFirstRoll() throws BowlingException {
        Game game = gameParser.parse(generateInputText("John",1, 21, "-"));
        Assert.assertEquals(0, game.getPlayers().get(0).getFrames().get(0).getRolls().get(0).getPoints());
    }

    @Test(expected = BowlingException.class)
    public void whenParseWithUnknownMaskedField_thenExceptOnePlayerWithZeroAsFirstRoll() throws BowlingException {
        Game game = gameParser.parse(generateInputText("John",1, 21, "-1"));
        Assert.assertEquals(0, game.getPlayers().get(0).getFrames().get(0).getRolls().get(0).getPoints());
    }
}