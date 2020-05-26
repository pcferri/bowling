package com.bowling.game.strategy.tenPin.enuns;

import java.util.ResourceBundle;

/**
 * Enum for manage the application messages properties for the Ten-pin Bowling Game
 *
 * @author Pedro Ferri
 */
public enum BowlingTenPinEnum {
    ROLLS_LAST_FRAME("tenpin.rolls.last.frame"),
    ROLLS_NORMAL_FRAME("tenpin.rolls.normal.frame"),
    MAX_PINS("tenpin.max.pins"),
    LIMIT_FRAMES("tenpin.limit.frames");

    private final String value;

    BowlingTenPinEnum(String bundleName) {
        this.value = ResourceBundle.getBundle("application").getString(bundleName);
    }

    /**
     * Return the enum value as int
     *
     * @return enum value as int
     */
    public int toInt() {
        return Integer.parseInt(value);
    }

    /**
     * Return the enum value as string
     *
     * @return enum value as string
     */
    @Override
    public String toString() {
        return value;
    }
}
