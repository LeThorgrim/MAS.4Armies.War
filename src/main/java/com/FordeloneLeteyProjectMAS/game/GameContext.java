package com.FordeloneLeteyProjectMAS.game;

import com.FordeloneLeteyProjectMAS.diplomacy.RelationManager;
import com.FordeloneLeteyProjectMAS.map.Map;
import com.FordeloneLeteyProjectMAS.map.Tile;
import com.FordeloneLeteyProjectMAS.map.TileZoneType;
import com.FordeloneLeteyProjectMAS.unit.FactionType;
import com.FordeloneLeteyProjectMAS.unit.KnowledgeType;
import com.FordeloneLeteyProjectMAS.unit.Master;
import com.FordeloneLeteyProjectMAS.unit.Unit;

import java.util.*;

public class GameContext {
    // GameContext is a singleton that holds :
    // current state of the game, including the map, units, factions, and others

    private static GameContext instance = null;
    private final Map gameMap;
    private final RelationManager relationManager =  null;
    private final List<Unit> units = new ArrayList<>();

    // Private constructor to prevent instantiation
    private GameContext() {
        System.out.println("[GameContext] Initializing game context...");
        //initialize map
        gameMap = new Map(14, 10);
        initializeTileZoneTypes();
        //initialize diplomacy
        RelationManager.getInstance();
        //initialize units
        initializeMasters();
        System.out.println("[GameContext] Game context initialized.");

        //TODO : play
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

    private void initializeMasters(){
        //Init 1 master of each faction in each corner
        Master humanMaster = new Master(0, 0, FactionType.FACTION_HUMAN);
        Master orcMaster = new Master(gameMap.getWidth() - 1, 0, FactionType.FACTION_ORC);
        Master elfMaster = new Master(0, gameMap.getHeight() - 1, FactionType.FACTION_ELF);
        Master goblinMaster = new Master(gameMap.getWidth() - 1, gameMap.getHeight() - 1, FactionType.FACTION_GOBLIN);

        units.add(humanMaster);
        units.add(orcMaster);
        units.add(elfMaster);
        units.add(goblinMaster);

        //there is 20 knowledges to share, we give 5 different knowledges to each master
        List<KnowledgeType> knowledgeList = new ArrayList<>(Set.of(KnowledgeType.values()));
        Collections.shuffle(knowledgeList); //add a bit of randomness :p

        //use sublist to give each master 5 different knowledges (recast to HashSet to match the type)
        humanMaster.setKnownKnowledge(new HashSet<>(knowledgeList.subList(0,5)));
        orcMaster.setKnownKnowledge(new HashSet<>(knowledgeList.subList(5,10)));
        elfMaster.setKnownKnowledge(new HashSet<>(knowledgeList.subList(10,15)));
        goblinMaster.setKnownKnowledge(new HashSet<>(knowledgeList.subList(15,20)));
    }

    private void initializeSoldiers(){
        //TODO : init some soldiers for each faction
    }

    public static GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
            System.out.println("[GameContext] Instance created");
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
        System.out.println("[GameContext] Instance reset");
    }

    public Map getMap() {
        return gameMap;
    }

}
