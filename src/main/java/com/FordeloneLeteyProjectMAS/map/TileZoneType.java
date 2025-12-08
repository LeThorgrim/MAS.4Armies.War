package com.FordeloneLeteyProjectMAS.map;

// Mainly used to define faction zones on the map (and some logics)

import com.FordeloneLeteyProjectMAS.unit.FactionType;

public enum TileZoneType {
    FACTION_NONE(null),
    FACTION_HQ_ORC(FactionType.FACTION_ORC),
    FACTION_HQ_ELF(FactionType.FACTION_ELF),
    FACTION_HQ_HUMAN(FactionType.FACTION_HUMAN),
    FACTION_HQ_GOBLIN(FactionType.FACTION_GOBLIN);

    private final FactionType faction;

    TileZoneType(FactionType faction) {
        this.faction = faction;
    }

    public FactionType getFaction() {
        return faction;
    }
}
