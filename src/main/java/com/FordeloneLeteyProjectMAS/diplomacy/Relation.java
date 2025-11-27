package com.FordeloneLeteyProjectMAS.diplomacy;

import com.FordeloneLeteyProjectMAS.unit.FactionType;

public class Relation {
    private final FactionType a;
    private final FactionType b;

    private RelationType relationType;

    public Relation(FactionType a, FactionType b) {
        if (a == b) {
            throw new IllegalArgumentException("[Relation] A faction cannot have a relation with itself");
        }
        this.a = a;
        this.b = b;
        this.relationType = RelationType.NEUTRAL;
    }

    public boolean contains(FactionType f1, FactionType f2) {
        return (a == f1 && b == f2) || (a == f2 && b == f1);
    }

    public FactionType getA() {
        return a;
    }
    public FactionType getB() {
        return b;
    }

    public boolean isAtWar() {
        return this.relationType == RelationType.ENEMY;
    }
    public void setAtWar() {
        this.relationType = RelationType.ENEMY;
    }

    public boolean isAllied() {
        return this.relationType == RelationType.ALLY;
    }
    public void setAllied() {
        this.relationType = RelationType.ALLY;
    }

    public boolean isNeutral(){
        return this.relationType == RelationType.NEUTRAL;
    }
    public void setNeutral() {
        this.relationType = RelationType.NEUTRAL;
    }
}

