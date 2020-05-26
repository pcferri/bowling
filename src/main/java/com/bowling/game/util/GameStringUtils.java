package com.bowling.game.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Main functions related with Strings
 *
 * @author Pedro Ferri
 */
public class GameStringUtils {

    public static String leftPad(String value, int size){
        return StringUtils.leftPad(value, size, " ");
    }

    public static String rightPad(String value){
        return rightPad(value, 0);
    }

    public static String rightPad(String value, int size){
        return StringUtils.rightPad(value, (size == 0 ? 12 : size), " ");
    }
}
