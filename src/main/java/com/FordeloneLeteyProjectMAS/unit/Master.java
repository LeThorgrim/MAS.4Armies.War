package com.FordeloneLeteyProjectMAS.unit;

// Faire 4 singleton Master pour chaque faction

import java.util.Set;

public class Master extends Unit {

    //no knowledge constructor
    public Master(int x, int y, FactionType factionType) {
        super(x, y, factionType, UnitType.MASTER);
    }

    //normal constructor
    public Master(int x, int y, Set<KnowledgeType> knownKnowledge, FactionType factionType) {
        super(x, y, knownKnowledge, factionType, UnitType.MASTER);
    }

    @Override
    public void moveUnit(int x, int y) {
        // Masters do not move
        return;
    }

    @Override
    public void interactWithUnit(Unit otherUnit) {
        // SAME FACTION : shares 100% knowledge
        if (this.getFactionType() == otherUnit.getFactionType()) {
            Set<KnowledgeType> combinedKnowledge = this.getKnownKnowledge();
            combinedKnowledge.addAll(otherUnit.getKnownKnowledge());
            //this.setKnownKnowledge(combinedKnowledge); //here or elsewhere ?
            otherUnit.setKnownKnowledge(combinedKnowledge);
        }
        // OTHER (ALLY;ENEMY;NEUTRAL) :
        // Shouldn't happen as they aren't supposed to move to the zone of another faction)
        else {
            return;
        }
    }

}
