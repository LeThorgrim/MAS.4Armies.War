package com.FordeloneLeteyProjectMAS.unit;

import com.FordeloneLeteyProjectMAS.map.Map;

import java.util.HashSet;
import java.util.Set;

//The abstract class representing a unit in the game
//It can be used to create new types of units by extending this class
public abstract class Unit {
    private int x;
    private int y;
    private Set<KnowledgeType> knownKnowledge; //set = no duplicate knowledge
    private FactionType factionType;
    private UnitType unitType;
    private int stamina; //to be used later for movement and actions

    public Unit(int x, int y, FactionType factionType, UnitType unitType) {
        this.x = x;
        this.y = y;
        this.knownKnowledge = new HashSet<>();
        this.factionType = factionType;
        this.unitType = unitType;
        this.stamina = 20;
    }

    public Unit(int x, int y, Set<KnowledgeType> knownKnowledge, FactionType factionType, UnitType unitType) {
        this.x = x;
        this.y = y;
        this.knownKnowledge = knownKnowledge;
        this.factionType = factionType;
        this.unitType = unitType;
        this.stamina = 20;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public Set<KnowledgeType> getKnownKnowledge() {
        return knownKnowledge;
    }

    public void setKnownKnowledge(Set<KnowledgeType> knownKnowledge) {
        this.knownKnowledge = knownKnowledge;
    }

    public void addKnowledge(KnowledgeType knowledge) {
        this.knownKnowledge.add(knowledge);
    }

    public void addKnowledge(Set<KnowledgeType> knowledgeList) {
        this.knownKnowledge.addAll(knowledgeList);
    }

    public FactionType getFactionType() {
        return factionType;
    }

    public void setFactionType(FactionType factionType) {
        this.factionType = factionType;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    //boolean to know if the move was successful (possible) or not and moves the unit if possible
    public abstract boolean moveUnit(int x, int y);
    //when a unit interacts with another unit (both units must use this method)
    public abstract void interactWithUnit(Unit otherUnit);
    //play function to be called each turn
    public abstract void playTurn(Map gameMap, java.util.List<Unit> allUnits);
}
