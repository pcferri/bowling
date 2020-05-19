package com.bowling.game;

import com.bowling.game.enuns.BowlingEnum;
import org.junit.Assert;
import org.junit.Test;

/**
 * Validate the BowlingRoll class and test cases for convert pinFall into the number, and number into a pinFall to print to the user.
 *
 * @author Pedro Ferri
 */
public class BowlingRollTest {

    @Test
    public void whenRollWithF_thenExceptZeroPoints() {
        Roll roll = new BowlingRoll();
        roll.setPinFall("F");

        Assert.assertEquals(0, roll.getPoints());
    }

    @Test
    public void whenRollWithX_thenExceptTenPoints() {
        Roll roll = new BowlingRoll();
        roll.setPinFall("X");

        Assert.assertEquals(BowlingEnum.MAX_PINS.toInt(), roll.getPoints());
    }

    @Test
    public void whenRollWithDash_thenExceptZeroPoints() {
        Roll roll = new BowlingRoll();
        roll.setPinFall("-");

        Assert.assertEquals(0, roll.getPoints());
    }

    @Test
    public void whenRollWithSlash_thenExceptKeepPoints() {
        Roll roll = new BowlingRoll();
        roll.setPinFall("8");
        roll.setPinFall("/");

        Assert.assertEquals(8, roll.getPoints());
    }

    @Test
    public void whenRollWithTen_thenExceptX() {
        Roll roll = new BowlingRoll();
        roll.setPinFall(BowlingEnum.MAX_PINS.toString());

        Assert.assertEquals("X", roll.getPinFall());
    }

    @Test
    public void whenRollWithLessThanLimit_thenExceptKeepPoints() {
        Roll roll = new BowlingRoll();
        roll.setPinFall("8");

        Assert.assertEquals(8, roll.getPoints());
    }
}