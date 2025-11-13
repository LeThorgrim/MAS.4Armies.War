package com.FordeloneLeteyProjectMAS.map;

// Map is a n*m grid of Tiles
// Note: modifications will have to be made to Tile objects

public class Map {
    // n*m grid of tiles who are stored in a 1D array
    private final int width;
    private final int height;
    private final Tile[] tiles;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width * height];

        // Initialize all tiles to default values
        for (int i = 0; i < tiles.length; i++) {
            int x = i % width;
            int y = i / width;
            tiles[i] = new Tile(x, y, TileZoneType.FACTION_NONE, TileType.EMPTY);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public Tile getTileAt(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Tile coordinates out of bounds");
        }
        return tiles[y * width + x];
    }
}
