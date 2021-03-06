package com.bowling.game.enuns;

import java.util.ResourceBundle;

/**
 * Enum for manage the application messages properties
 *
 * @author Pedro Ferri
 */
public enum BowlingEnum {
    ERROR_WRONG_ROLL("error.wrong.roll"),
    ERROR_FILE_DOES_NOT_CONTAIN_LINES("error.file.does.not.contain.lines"),
    ERROR_PLAYER_DOES_NOT_CONTAIN_INFORMATION("error.player.does.not.contain.information"),
    ERROR_LOAD_FILE("error.load.file"),
    REPORT_FRAME("report.frame"),
    REPORT_PINFALLS("report.pinfalls"),
    REPORT_SCORE("report.score"),
    ROLL_NOT_ALLOWED_FOUND_TO_THE_PLAYER("roll.not.allowed.found.to.the.player"),
    INVALID_NUMBERS_OF_FRAMES_FOUND("invalid.numbers.of.frames.found"),
    THE_NUMBER_OF_FRAMES_IS_DIFFERENT_THAN_CONFIGURED("the.number.of.frames.is.different.than.configured");

    private final String value;

    BowlingEnum(String bundleName) {
        this.value = ResourceBundle.getBundle("application").getString(bundleName);
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



