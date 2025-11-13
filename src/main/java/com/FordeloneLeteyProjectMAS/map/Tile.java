package com.FordeloneLeteyProjectMAS.map;

public class Tile {
    private int x;
    private int y;
    private TileZoneType zoneType;
    private TileType tileType;

    public Tile(){
        this.x = 0;
        this.y = 0;
        this.zoneType = TileZoneType.FACTION_NONE;
        this.tileType = TileType.EMPTY;
    }

    public Tile(int x, int y, TileZoneType zoneType, TileType tileType) {
        this.x = x;
        this.y = y;
        this.zoneType = zoneType;
        this.tileType = tileType;
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

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
