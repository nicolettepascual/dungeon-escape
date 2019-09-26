package DungeonEscape.entities;

import DungeonEscape.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {

        protected Handler handler;
	protected float x, y;
	protected int width, height;
        protected int open;
        protected boolean active = true;
        protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y, int width, int height){
		this.handler = handler;
                this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
                
                bounds = new Rectangle(0,0, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
        
        public abstract void opened();
        
        public void opening(){
            active = false;
            opened();
        }

        public boolean checkEntityCollisions(float xOffset, float yOffset){
            for(Entity e : handler.getWorld().getEntityManager().getEntities()){
                if(e.equals(this))
                    continue;
                if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                       return true;
            }
            return false;
        } 
        
        //Collision bounds of the entity
       public Rectangle getCollisionBounds(float xOffset, float yOffset){
           return new Rectangle((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), bounds.width, bounds.height);
        }
        
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
        
        public int getOpen(){
            return open;
        }
        
        public void setOpen(int open){
            this.open = open;
        }
        
        public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}