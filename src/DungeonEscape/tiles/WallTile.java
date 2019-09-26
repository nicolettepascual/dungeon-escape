package DungeonEscape.tiles;

import DungeonEscape.graphics.Assets;

public class WallTile extends Tile{
    
    public WallTile(int id) {
        super(Assets.wall, id);
    }
    
    public boolean isSolid(){
        return true;
    }
}