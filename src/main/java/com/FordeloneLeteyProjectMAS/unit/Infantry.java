package com.FordeloneLeteyProjectMAS.unit;

public class Infantry extends Unit{

    //no knowledge constructor
    public Infantry(int x, int y, FactionType factionType) {
        super(x, y, factionType);
    }

    //normal constructor
    public Infantry(int x, int y, java.util.Set<KnowledgeType> knownKnowledge, FactionType factionType) {
        super(x, y, knownKnowledge, factionType);
    }

    @Override
    public void moveUnit(int x, int y) {
        // Infantry can move 1 tile in any direction including diagonals
        int deltaX = Math.abs(x - this.getX());
        int deltaY = Math.abs(y - this.getY());
        if (deltaX <= 1 && deltaY <= 1) {
            this.setX(x);
            this.setY(y);
        }
    }

    @Override
    public void interactWithUnit(Unit otherUnit) {
        //SAME FACTION : shares 100% knowledge
        //ALLY
        //NEUTRAL
        //ENEMY
    }
}
