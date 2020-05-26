package com.bowling.game;


import com.bowling.game.strategy.GameStrategy;

/**
 * BowlingRoll implementation for Roll
 *
 * @author Pedro Ferri
 */
public final class BowlingRoll implements Roll {

    private final GameStrategy gameStrategy;

    public BowlingRoll (GameStrategy gameStrategy){
        this.gameStrategy = gameStrategy;
    }

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
        this.pinFall = this.gameStrategy.getCustomPinFall(this, pinFall);
    }
}
