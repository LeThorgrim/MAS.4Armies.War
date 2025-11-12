package com.FordeloneLeteyProjectMAS.unit;

public enum KnowledgeType {
    GANDALF("All we have to decide is what to do with the time that is given us."),
    ARAGORN("I do not fear death."),
    LEGOLAS("A red sun rises. Blood has been spilled this night."),
    GIMLI("Faithless is he that says farewell when the road darkens."),
    FRODO("I will take the Ring, though I do not know the way."),
    SAM("There’s some good in this world, Mr. Frodo, and it’s worth fighting for."),
    BOROMIR("One does not simply walk into Mordor."),
    GALADRIEL("Even the smallest person can change the course of the future."),
    ELROND("Put aside the Ranger. Become who you were born to be."),
    ARWEN("I would rather share one lifetime with you than face all the ages of this world alone."),
    THEODEN("So it begins."),
    EOWYN("I am no man!"),
    SARUMAN("The old world will burn in the fires of industry."),
    SMEAGOL("Sneaky little hobbitses!"),
    BILBO("I think I’m quite ready for another adventure."),
    THORIN("If more of us valued food and cheer and song above hoarded gold, it would be a merrier world."),
    BALIN("There’s one dwarf yet in Moria who still draws breath!"),
    GOLLUM("My precious!"),
    SAURON("You cannot hide. I see you."),
    TREEBEARD("Do not be hasty, that is my motto.");

    private final String quote;

    KnowledgeType(String quote) {
        this.quote = quote;
    }

    public String getQuote() {
        return quote;
    }
}
