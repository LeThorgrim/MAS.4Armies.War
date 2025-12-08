package com.FordeloneLeteyProjectMAS;

import com.FordeloneLeteyProjectMAS.game.GameContext;

public class Main {
    public static void main(String[] args) {
        GameContext gameContext = GameContext.getInstance();
        gameContext.getMap().printMapZoneTypes();
        gameContext.printGameMap(999);
        gameContext.playGame();
    }
}