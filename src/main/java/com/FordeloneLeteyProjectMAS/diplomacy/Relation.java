package com.FordeloneLeteyProjectMAS.diplomacy;

import com.FordeloneLeteyProjectMAS.unit.FactionType;

public class Relation {
    private final FactionType a;
    private final FactionType b;

    private boolean atWar;
    private boolean allied;

    public Relation(FactionType a, FactionType b) {
        if (a == b) {
            throw new IllegalArgumentException("[Relation] A faction cannot have a relation with itself");
        }
        this.a = a;
        this.b = b;
        this.atWar = false;
        this.allied = false;
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
        return atWar;
    }
    public void setAtWar(boolean atWar) {
        this.atWar = atWar;
    }

    public boolean isAllied() {
        return allied;
    }
    public void setAllied(boolean allied) {
        this.allied = allied;
    }
}

