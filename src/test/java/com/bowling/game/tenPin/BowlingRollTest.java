package com.bowling.game.tenPin;

import com.bowling.game.BowlingRoll;
import com.bowling.game.Roll;
import com.bowling.game.strategy.GameStrategy;
import com.bowling.game.strategy.tenPin.TenPinGameStrategy;
import com.bowling.game.strategy.tenPin.enuns.BowlingTenPinEnum;
import org.junit.Assert;
import org.junit.Test;

/**
 * Validate the BowlingRoll class and test cases for convert pinFall into the number, and number into a pinFall to print to the user.
 *
 * @author Pedro Ferri
 */
public class BowlingRollTest {

    private final GameStrategy gameStrategy = new TenPinGameStrategy();

    @Test
    public void whenRollWithF_thenExceptZeroPoints() {
        Roll roll = new BowlingRoll(gameStrategy);
        roll.setPinFall("F");

        Assert.assertEquals(0, roll.getPoints());
    }

    @Test
    public void whenRollWithX_thenExceptTenPoints() {
        Roll roll = new BowlingRoll(gameStrategy);
        roll.setPinFall("X");

        Assert.assertEquals(BowlingTenPinEnum.MAX_PINS.toInt(), roll.getPoints());
    }

    @Test
    public void whenRollWithDash_thenExceptZeroPoints() {
        Roll roll = new BowlingRoll(gameStrategy);
        roll.setPinFall("-");

        Assert.assertEquals(0, roll.getPoints());
    }

    @Test
    public void whenRollWithSlash_thenExceptKeepPoints() {
        Roll roll = new BowlingRoll(gameStrategy);
        roll.setPinFall("8");
        roll.setPinFall("/");

        Assert.assertEquals(8, roll.getPoints());
    }

    @Test
    public void whenRollWithTen_thenExceptX() {
        Roll roll = new BowlingRoll(gameStrategy);
        roll.setPinFall(BowlingTenPinEnum.MAX_PINS.toString());

        Assert.assertEquals("X", roll.getPinFall());
    }

    @Test
    public void whenRollWithLessThanLimit_thenExceptKeepPoints() {
        Roll roll = new BowlingRoll(gameStrategy);
        roll.setPinFall("8");

        Assert.assertEquals(8, roll.getPoints());
    }
}