package DungeonEscape.worlds;

import DungeonEscape.entities.Player;
import DungeonEscape.entities.statics.EntityManager;
import DungeonEscape.Handler;
import DungeonEscape.items.ItemManager;
import DungeonEscape.tiles.Tile;
import DungeonEscape.utils.Utils;
import java.awt.Graphics;
import DungeonEscape.entities.statics.chests.RedChest;
import DungeonEscape.entities.statics.chests.OrangeChest;
import DungeonEscape.entities.statics.chests.YellowChest;
import DungeonEscape.entities.statics.chests.GreenChest;
import DungeonEscape.entities.statics.chests.BlueChest;
import DungeonEscape.entities.statics.chests.IndigoChest;
import DungeonEscape.entities.statics.chests.VioletChest;

public class World {
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tilesArray;
    
    //Enities
    private EntityManager entityManager;
    
    //ItemManager
    private ItemManager itemManager;
    
    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        itemManager = new ItemManager(handler);
       
       entityManager.addEntity(new RedChest(handler, 2660, 470));
       entityManager.addEntity(new OrangeChest(handler, 2590, 470)); 
       entityManager.addEntity(new YellowChest(handler, 1025, 500)); 
       entityManager.addEntity(new GreenChest(handler, 2992, 1190)); 
       entityManager.addEntity(new BlueChest(handler, 1920, 1300)); 
       entityManager.addEntity(new IndigoChest(handler, 835, 640)); 
       entityManager.addEntity(new VioletChest(handler, 65, 1910)); 
        
        loadWorld(path);
        
       entityManager.getPlayer().setX(spawnX);
       entityManager.getPlayer().setY(spawnY);
    }
    
    public void tick(){
        itemManager.tick();
        entityManager.tick();
        
    }
    
    public void render(Graphics g){                    
    int xStart = (int)Math.max(0, handler.getGameCamera().getxOffset()/Tile.TILEWIDTH); 
    int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) /Tile.TILEWIDTH + 1); 
    int yStart = (int)Math.max(0, handler.getGameCamera().getyOffset()/Tile.TILEHEIGHT);;
    int yEnd = (int)Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
        
    for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                getTile(x,y).render(g, (int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), 
                        (int)(y * Tile.TILEHEIGHT- handler.getGameCamera().getyOffset()));
            }
        }
    itemManager.render(g);
    entityManager.render(g);
    }
    
    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.floorTile;
        
        Tile t = Tile.tiles[tilesArray[x][y]]; 
        if(t == null)
            return Tile.floorTile;
        return t;
    }
    
    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        
        width = Utils.parseInt(tokens[0]); 
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        
        tilesArray = new int[width][height];
            for(int y = 0; y < height; y++){
                for(int x =0; x < width; x++){
                    tilesArray[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
                }
            }
    }
    //GETTERS AND SETTER
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
   public EntityManager getEntityManager() {
	return entityManager;
    }

    public Handler getHandler() {
	return handler;
    }

    public void setHandler(Handler handler) {
	this.handler = handler;
    }

    public ItemManager getItemManager() {
	return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
	this.itemManager = itemManager;
    }
}