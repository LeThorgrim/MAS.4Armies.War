package com.FordeloneLeteyProjectMAS.unit;

import com.FordeloneLeteyProjectMAS.diplomacy.RelationManager;
import com.FordeloneLeteyProjectMAS.diplomacy.RelationType;
import com.FordeloneLeteyProjectMAS.map.Map;

import java.util.HashSet;
import java.util.Set;

public class Infantry extends Unit{

    //no knowledge constructor
    public Infantry(int x, int y, FactionType factionType) {
        super(x, y, factionType, UnitType.INFANTRY);
    }

    //normal constructor
    public Infantry(int x, int y, java.util.Set<KnowledgeType> knownKnowledge, FactionType factionType) {
        super(x, y, knownKnowledge, factionType, UnitType.INFANTRY);
    }

    @Override
    public boolean moveUnit(int x, int y) {
        // Infantry can move 1 tile in any direction including diagonals
        int deltaX = Math.abs(x - this.getX());
        int deltaY = Math.abs(y - this.getY());
        if (deltaX <= 1 && deltaY <= 1) {
            this.setX(x);
            this.setY(y);
            this.setStamina(getStamina() - 1);
            return true;
        }
        return false;
    }

    @Override
    public void interactWithUnit(Unit otherUnit) {
        //SAME FACTION : shares 100% knowledge
        if (this.getFactionType() == otherUnit.getFactionType()) {
            Set<KnowledgeType> combinedKnowledge = new HashSet<>(this.getKnownKnowledge());
            combinedKnowledge.addAll(otherUnit.getKnownKnowledge());
            otherUnit.setKnownKnowledge(combinedKnowledge);
        }
        //ALLY : shares random 50% of knowledge
        else if (RelationManager.getInstance().getRelation(this.getFactionType(), otherUnit.getFactionType()).isAllied()) {
            Set<KnowledgeType> combinedKnowledge = new HashSet<>(this.getKnownKnowledge());

            //remove random 50% of knowledge from combinedKnowledge
            int itemsToRemove = combinedKnowledge.size() / 2;
            KnowledgeType[] knowledgeArray = combinedKnowledge.toArray(new KnowledgeType[0]);
            for (int i = 0; i < itemsToRemove; i++) {
                int randomIndex = (int) (Math.random() * knowledgeArray.length);
                combinedKnowledge.remove(knowledgeArray[randomIndex]);
            }

            //the rest is shared
            combinedKnowledge.addAll(otherUnit.getKnownKnowledge());
            otherUnit.setKnownKnowledge(combinedKnowledge);
        }
        //NEUTRAL : shares random 25% of knowledge
        else if (RelationManager.getInstance().getRelation(this.getFactionType(), otherUnit.getFactionType()).isNeutral()) {
            Set<KnowledgeType> combinedKnowledge = new HashSet<>(this.getKnownKnowledge());

            //remove random 75% of knowledge from combinedKnowledge
            int itemsToRemove = (combinedKnowledge.size() * 3) / 4;
            KnowledgeType[] knowledgeArray = combinedKnowledge.toArray(new KnowledgeType[0]);
            for (int i = 0; i < itemsToRemove; i++) {
                int randomIndex = (int) (Math.random() * knowledgeArray.length);
                combinedKnowledge.remove(knowledgeArray[randomIndex]);
            }

            //the rest is shared
            combinedKnowledge.addAll(otherUnit.getKnownKnowledge());
            otherUnit.setKnownKnowledge(combinedKnowledge);
        }
        //ENEMY : coinflip (50% chance) to 'steal' 10% of knowledge
        else if (RelationManager.getInstance().getRelation(this.getFactionType(), otherUnit.getFactionType()).isAtWar()) {
            double randomValue = Math.random();
            if (randomValue < 0.5) {
                Set<KnowledgeType> combinedKnowledge = new HashSet<>(this.getKnownKnowledge());

                //remove random 90% of knowledge from combinedKnowledge
                int itemsToRemove = (combinedKnowledge.size() * 9) / 10;
                KnowledgeType[] knowledgeArray = combinedKnowledge.toArray(new KnowledgeType[0]);
                for (int i = 0; i < itemsToRemove; i++) {
                    int randomIndex = (int) (Math.random() * knowledgeArray.length);
                    combinedKnowledge.remove(knowledgeArray[randomIndex]);
                }

                //the rest is shared
                combinedKnowledge.addAll(otherUnit.getKnownKnowledge());
                otherUnit.setKnownKnowledge(combinedKnowledge);
            }
        }
        else {
            return;
        }
    }

    @Override
    public void playTurn(Map gameMap, java.util.List<Unit> allUnits) {
        //just use the move method taking into account stamina and that some tiles are not accessible
        //the tiles innaccessible are hq zones of other factions + tiles occupied by other units
        if (getStamina() > 10) {
            //try to move to a random adjacent tile
            int newX = this.getX() + (int)(Math.random() * 3) - 1; // -1, 0, or 1
            int newY = this.getY() + (int)(Math.random() * 3) - 1; // -1, 0, or 1

            //check if the new tile is within bounds
            if (newX >= 0 && newX < gameMap.getWidth() && newY >= 0 && newY < gameMap.getHeight()) {
                //check if the tile is occupied by another unit
                boolean occupied = false;
                for (Unit unit : allUnits) {
                    if (unit.getX() == newX && unit.getY() == newY && unit != this) {
                        occupied = true;
                        break;
                    }
                }

                //check if the tile is an HQ zone of another faction
                boolean hqZone = gameMap.isOtherHQZone(newX, newY, this.getFactionType());

                if (!occupied && !hqZone) {
                    moveUnit(newX, newY);
                }
            }
        }
        else if(getStamina() <= 10){
            //rest to regain stamina
            setStamina(getStamina() + 5);
        }
    }
}
