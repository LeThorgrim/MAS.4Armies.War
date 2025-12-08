package com.FordeloneLeteyProjectMAS.game;

import com.FordeloneLeteyProjectMAS.diplomacy.RelationManager;
import com.FordeloneLeteyProjectMAS.map.Map;
import com.FordeloneLeteyProjectMAS.map.Tile;
import com.FordeloneLeteyProjectMAS.map.TileZoneType;
import com.FordeloneLeteyProjectMAS.unit.*;

import java.time.Instant;
import java.util.*;

public class GameContext {
    // GameContext is a singleton that holds :
    // current state of the game, including the map, units, factions, and others

    private static GameContext instance = null;
    private final Map gameMap;
    private final RelationManager relationManager =  null;
    private final List<Unit> units = new ArrayList<>();
    private Boolean isGameOver = false;
    private int turnNumber = 0;

    // Private constructor to prevent instantiation
    private GameContext() {
        System.out.println("[GameContext] Initializing game context...");
        //initialize map
        gameMap = new Map(14, 10);
        initializeTileZoneTypes();
        System.out.println("[GameContext] Map initialized");
        gameMap.printMapZoneTypes();
        //initialize diplomacy
        RelationManager.getInstance();
        //initialize units
        initializeMasters();
        System.out.println("[GameContext] Masters initialized");
        initializeSoldiers();
        System.out.println("[GameContext] Soldiers initialized");
        System.out.println("[GameContext] Game context initialized");
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
        //Init 3 infantry units for each faction
        for(FactionType faction : FactionType.values()){
            for(int i = 0; i < 3; i++) {
                Infantry tmpInfantry = new Infantry(-1, -1, faction);
                spawnUnit(tmpInfantry, faction);
            }
        }
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

    public boolean isThereUnit(int x, int y){
        for (Unit unit : units) {
            if (unit.getX() == x && unit.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public Unit getUnitAt(int x, int y){
        for (Unit unit : units) {
            if (unit.getX() == x && unit.getY() == y) {
                return unit;
            }
        }
        return null;
    }

    //print the game map in ascii (shows units)
    //with 2 characters per tile for clarity
    public void printGameMap(int turnNumber){
        System.out.println("\n========== TURN " + (turnNumber) + " ===========");
        for (int y = 0; y < this.getMap().getHeight(); y++) {
            for (int x = 0; x < this.getMap().getWidth(); x++) {
                if(isThereUnit(x, y)){
                    Unit unit = getUnitAt(x, y);
                    if(unit.getUnitType() == UnitType.MASTER){
                        switch (unit.getFactionType()) {
                            case FACTION_HUMAN -> System.out.print("mH ");
                            case FACTION_ORC -> System.out.print("mO ");
                            case FACTION_ELF -> System.out.print("mE ");
                            case FACTION_GOBLIN -> System.out.print("mG ");
                        }
                    } else if(unit.getUnitType() == UnitType.INFANTRY){
                        switch (unit.getFactionType()) {
                            case FACTION_HUMAN -> System.out.print("iH ");
                            case FACTION_ORC -> System.out.print("iO ");
                            case FACTION_ELF -> System.out.print("iE ");
                            case FACTION_GOBLIN -> System.out.print("iG ");
                        }
                    } else {
                        //unknown unit type
                        System.out.print("xX ");
                    }
                } else {
                    System.out.print(".. ");
                }
            }
            System.out.println();
        }
        System.out.println("LEGEND:");
        System.out.println("1st CHAR | m: Master, i: Infantry,");
        System.out.println("2nd CHAR | H: Human, O: Orc, E: Elf, G: Goblin");
        //we dont precize xX as it should be a bug / forgotten code

        //print knowledge of each master
        for (FactionType faction : FactionType.values()) {
            Master master = getMasterOfFaction(faction);
            if (master != null) {
                int knowledgeCount = master.getKnownKnowledge().size();
                int knowledgeTotal = KnowledgeType.values().length;
                System.out.println(faction + ": " + knowledgeCount + "/" + knowledgeTotal);
            }
        }
    }

    public void playGame(){
        System.out.println("[GameContext] Game started");
        //main game loop
        while(!isGameOver){
            playTurn();
        }
        System.out.println("[GameContext] Game over");
    }

    //one turn has a duration of 1 second
    private void playTurn(){
        Instant startTurnTime = Instant.now();
        //TODO : implement turn logic (movement, interactions, win conditions, etc.)
        //move all units from the units list

        //search for pairs of units that are next to each other and make them interact

        //end condition // TODO : DELETE
        if(turnNumber == 10){
            isGameOver = true;
        }
        //end condition
        for (FactionType faction : FactionType.values()) {
            if (didFactionWin(faction)) {
                System.out.println("Faction " + faction + " has won the game!");
                isGameOver = true;
            }
        }

        //print map
        turnNumber += 1;
        printGameMap(turnNumber);

        //ensure turn duration is 1 second
        Instant endTurnTime = Instant.now();
        long elapsedTime = java.time.Duration.between(startTurnTime, endTurnTime).toMillis();
        if(elapsedTime < 1000){
            try {
                Thread.sleep(1000 - elapsedTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // problem during sleep (?)
            }
        }
    }

    //checks if he has 100% of the KnowledgeType(s)
    private boolean didFactionWin(FactionType faction) {
        Master masterOfFaction = getMasterOfFaction(faction);
        return masterOfFaction != null && masterOfFaction.getKnownKnowledge().size() == KnowledgeType.values().length;
    }

    //finds master of the faction in the units list
    private Master getMasterOfFaction(FactionType faction) {
        for (Unit unit : units) {
            if (unit.getUnitType() == UnitType.MASTER && unit.getFactionType() == faction) {
                return (Master) unit;
            }
        }
        return null;
    }

    //spawns a unit in his faction's HQ zone
    //returns true if successful, false otherwise
    private boolean spawnUnit(Unit unit, FactionType factionType){
        //check for first free tile in faction HQ zone
        for (int y = 0; y < gameMap.getHeight(); y++) {
            for (int x = 0; x < gameMap.getWidth(); x++) {
                Tile tile = gameMap.getTileAt(x, y);
                if (tile.getZoneType().getFaction() == factionType && !isThereUnit(x, y)) {
                    unit.setX(x);
                    unit.setY(y);
                    units.add(unit);
                    return true;
                }
            }
        }
        return false;
    }
}
