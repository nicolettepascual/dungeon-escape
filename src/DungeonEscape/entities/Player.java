package DungeonEscape.entities;

import DungeonEscape.Handler;
import DungeonEscape.graphics.Animation;
import DungeonEscape.graphics.Assets;
import DungeonEscape.inventory.Inventory;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class Player extends Creature {
    
    private Inventory inventory;
    //Animation
    private Animation animDown, animUp, animLeft, animRight,animStand;
    
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
                
                //CONTROLS THE BOUNDING RECTANGLE OF THE PLAYER
                bounds.x = 7;
                bounds.y = 20;
                bounds.width = 50;
                bounds.height = 40;
                
                
                //Animations
                animDown = new Animation(200, Assets.player_down);
                animUp = new Animation(200, Assets.player_up);
                animLeft = new Animation(200, Assets.player_left);
                animRight = new Animation(200, Assets.player_right);
                animStand = new Animation(200, Assets.player_stand);
                
                inventory = new Inventory(handler);
        }
        
       
        
	@Override
	public void tick() {
                //animations
                animDown.tick();
                animUp.tick();
                animLeft.tick();
                animRight.tick();
                animStand.tick();
                
                //movements
		getInput();
		move();
                handler.getGameCamera().centerOnEntity(this);
                
                //Opening of Chest
                checkOpen();
                
                inventory.tick();
	
        }
        
        public void opened(){}
        
        private void checkOpen(){
            Rectangle cb = getCollisionBounds(0, 0);
            Rectangle op = new Rectangle();
            int opSize = 20;
            op.width = opSize;
            op.height = opSize;
            
            if(handler.getKeyManager().open){
            op.x = cb.x + cb.width / 2 - opSize/2;  
            op.y = cb.y - opSize;}
            else
                return;
            for(Entity e: handler.getWorld().getEntityManager().getEntities()){
                if(e.equals(this))
                    continue;
                if(e.getCollisionBounds(0,0).intersects(op)){
                    e.opening();
                    return;
                }
            }
        }
        
	
	private void getInput(){
		xMove = 0;
		yMove = 0;		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}

	@Override
	public void render(Graphics g) {          
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y  - handler.getGameCamera().getyOffset()), width, height, null);
                inventory.render(g);
        }
        
        private BufferedImage getCurrentAnimationFrame(){
            if(xMove < 0){ //x-axis LEFT
                return animLeft.getCurrentFrame();
            }else if(xMove > 0){ //x-axis RIGHT
                return animRight.getCurrentFrame();
            }else if(yMove < 0){
                return animUp.getCurrentFrame();
            }else if(yMove > 0){
                return animDown.getCurrentFrame();
            }
            return animStand.getCurrentFrame();
        }
        
        public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
}