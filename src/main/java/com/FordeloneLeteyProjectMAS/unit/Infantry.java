package com.FordeloneLeteyProjectMAS.unit;

import com.FordeloneLeteyProjectMAS.diplomacy.RelationManager;
import com.FordeloneLeteyProjectMAS.diplomacy.RelationType;

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
            return true;
        }
        return false;
    }

    @Override
    public void interactWithUnit(Unit otherUnit) {
        //SAME FACTION : shares 100% knowledge
        if (this.getFactionType() == otherUnit.getFactionType()) {
            Set<KnowledgeType> combinedKnowledge = this.getKnownKnowledge();
            combinedKnowledge.addAll(otherUnit.getKnownKnowledge());
            otherUnit.setKnownKnowledge(combinedKnowledge);
        }
        //ALLY : shares random 50% of knowledge
        else if (RelationManager.getInstance().getRelation(this.getFactionType(), otherUnit.getFactionType()).isAllied()) {
            Set<KnowledgeType> combinedKnowledge = this.getKnownKnowledge();

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
            Set<KnowledgeType> combinedKnowledge = this.getKnownKnowledge();

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
        //ENEMY : no knowledge shared (TODO: might add fight)
        else {
            return;
        }
    }
}
