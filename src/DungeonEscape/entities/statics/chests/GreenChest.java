package DungeonEscape.entities.statics.chests;

import DungeonEscape.entities.statics.StaticEntity;
import DungeonEscape.Handler;
import DungeonEscape.graphics.Assets;
import DungeonEscape.items.Item;
import DungeonEscape.tiles.Tile;
import java.awt.Graphics;

public class GreenChest extends StaticEntity{

    public GreenChest(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        
        bounds.x = 3;
	bounds.y = (int) (height / 2f);
	bounds.width = width - 6;
	bounds.height = (int) (height - height / 2f);
    }

    @Override
    public void tick() {    
    }
    
    public void opened(){
    handler.getWorld().getItemManager().addItem(Item.GreenchestItem.createNew((int) x, (int) y));
    }
    
    @Override
    public void render(Graphics g) {
       g.drawImage(Assets.chest, (int)(x - handler.getGameCamera().getxOffset()), (int)(y  - handler.getGameCamera().getyOffset()), width, height, null);  
    } 
}