package com.FordeloneLeteyProjectMAS.map;

public class Tile {
    private int x;
    private int y;
    private TileZoneType zoneType;

    public Tile(){
        this.x = 0;
        this.y = 0;
        this.zoneType = TileZoneType.FACTION_NONE;
    }

    public Tile(int x, int y, TileZoneType zoneType) {
        this.x = x;
        this.y = y;
        this.zoneType = zoneType;
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

    public TileZoneType getZoneType() {
        return zoneType;
    }

    public void setZoneType(TileZoneType zoneType) {
        this.zoneType = zoneType;
    }
}
