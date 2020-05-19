package com.bowling.game;

import com.bowling.game.enuns.BowlingEnum;

/**
 * BowlingRoll implementation for Roll
 *
 * @author Pedro Ferri
 */
public class BowlingRoll implements Roll {

    private String pinFall;
    private int points;


    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String getPinFall() {
        return this.pinFall;
    }

    @Override
    public void setPinFall(String pinFall) {
        switch (pinFall.toUpperCase()) {
            case "F":
            case "-":
                setPoints(0);
                this.pinFall = pinFall;
                break;
            case "10":
                setPoints(10);
                this.pinFall = "X";
                break;
            case "/":
                this.pinFall = pinFall;
                break;
            case "X":
                this.setPoints(BowlingEnum.MAX_PINS.toInt());
                this.pinFall = pinFall;
                break;
            default:
                this.setPoints(Integer.parseInt(pinFall));
                this.pinFall = pinFall;
        }
    }
}
