package com.FordeloneLeteyProjectMAS;

import com.FordeloneLeteyProjectMAS.game.GameContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing Fordelone Letey Project MAS...");

        GameContext gameContext = GameContext.getInstance();
        gameContext.getMap().printMapZoneTypes();
        gameContext.printGameMap();
    }
}