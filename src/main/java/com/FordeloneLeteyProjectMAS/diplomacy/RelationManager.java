package com.FordeloneLeteyProjectMAS.diplomacy;

import com.FordeloneLeteyProjectMAS.unit.FactionType;

import java.util.HashSet;
import java.util.Set;

public class RelationManager {
    // RelationManager is a second singleton that holds :
    // bilateral relations between each faction modelled by Relation objects

    private static RelationManager instance = null;
    private final Set<Relation> relations = new HashSet<>();

    private RelationManager(){
        FactionType[] factions = FactionType.values();
        for (int i = 0; i < factions.length; i++) {
            for (int j = i + 1; j < factions.length; j++) {
                relations.add(new Relation(factions[i], factions[j]));
                System.out.println("[RelationManager] Relation created between " + factions[i] + " and " + factions[j]);
            }
        }
    }

    public static RelationManager getInstance() {
        if (instance == null) {
            instance = new RelationManager();
            System.out.println("[RelationManager] Instance created");
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
        System.out.println("[RelationManager] Instance reset");
    }

    public Relation getRelation(FactionType f1, FactionType f2) {
        return relations.stream()
                .filter(r -> r.contains(f1, f2))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[RelationManager] Relation not found"));
    }

    public void declareWar(FactionType f1, FactionType f2) {
        Relation r = getRelation(f1, f2);
        r.setAtWar();
    }

    public void formAlliance(FactionType f1, FactionType f2) {
        Relation r = getRelation(f1, f2);
        r.setAllied();
    }

    public void declareNeutrality(FactionType f1, FactionType f2) {
        Relation r = getRelation(f1, f2);
        r.setNeutral();
    }
}
