package DungeonEscape.items;

import DungeonEscape.Handler;
import DungeonEscape.graphics.Assets;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Item {
    
    //Handler
    public static Item[] items = new Item[256];
    public static Item RedchestItem = new Item(Assets.red, "Red Key", 0);
    public static Item OrangechestItem = new Item(Assets.orange, "Orange Key", 1);
    public static Item YellowchestItem = new Item(Assets.yellow, "Yellow Key", 2);
    public static Item GreenchestItem = new Item(Assets.green, "Green Key", 3);
    public static Item BluechestItem = new Item(Assets.blue, "Blue Key", 4);
    public static Item IndigochestItem = new Item(Assets.indigo, "Indigo Key", 5);
    public static Item VioletchestItem = new Item(Assets.violet, "Violet Key", 6);
 
    //Class
    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;
    
    //bounding rectangle
    protected Rectangle bounds; 
    
    protected int x, y, count;
    protected boolean pickedUp = false;
    
    public Item(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1; 
        //Rectangle bounds of the key
        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
        
        items[id] = this;
    }
    
    public void tick(){                                              
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)){
            pickedUp = true; 
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
        }
    }
    
    public void render(Graphics g){
        if(handler == null)
            return;
        render(g, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()));
    }
    
    public void render(Graphics g, int x, int y){  
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }
    
    public Item createNew(int x, int y){ 
        Item i = new Item(texture, name, id);
        i.setPosition(x, y);
        return i;
    }
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }
    // Getters and Setters
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}
        
        public boolean isPickedUp() {
		return pickedUp;
	}
}