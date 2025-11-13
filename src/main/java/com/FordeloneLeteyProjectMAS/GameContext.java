package com.FordeloneLeteyProjectMAS;

import com.FordeloneLeteyProjectMAS.map.Map;
import com.FordeloneLeteyProjectMAS.map.Tile;
import com.FordeloneLeteyProjectMAS.map.TileZoneType;
import com.FordeloneLeteyProjectMAS.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class GameContext {
    // GameContext is a singleton that holds :
    // current state of the game, including the map, units, factions, and others

    private static GameContext instance = null;
    private final Map gameMap;
    private final List<Unit> units = new ArrayList<>();

    // Private constructor to prevent instantiation
    private GameContext() {
        gameMap = new Map(14, 10);
        initializeTileZoneTypes();
    }

    private void initializeTileZoneTypes(){
        // place TileZoneType(s) 4 corners of each faction
        // TOP LEFT : HUMAN
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Tile tile = gameMap.getTileAt(x, y);
                tile.setZoneType(TileZoneType.FACTION_HQ_HUMAN);
            }
        }
        // TOP RIGHT : ORC
        for (int y = 0; y < 3; y++) {
            for (int x = gameMap.getWidth() - 3; x < gameMap.getWidth(); x++) {
                Tile tile = gameMap.getTileAt(x, y);
                tile.setZoneType(TileZoneType.FACTION_HQ_ORC);
            }
        }
        // BOTTOM LEFT : ELF
        for (int y = gameMap.getHeight() - 3; y < gameMap.getHeight(); y++) {
            for (int x = 0; x < 3; x++) {
                Tile tile = gameMap.getTileAt(x, y);
                tile.setZoneType(TileZoneType.FACTION_HQ_ELF);
            }
        }
        // BOTTOM RIGHT : GOBLIN
        for (int y = gameMap.getHeight() - 3; y < gameMap.getHeight(); y++) {
            for (int x = gameMap.getWidth() - 3; x < gameMap.getWidth(); x++) {
                Tile tile = gameMap.getTileAt(x, y);
                tile.setZoneType(TileZoneType.FACTION_HQ_GOBLIN);
            }
        }
    }

    private void initializeUnits(){
        //TODO
    }

    public static GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
        }
        return instance;
    }

    public Map getMap() {
        return gameMap;
    }

}
