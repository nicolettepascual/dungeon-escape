package DungeonEscape.states;

import java.awt.Graphics;

import DungeonEscape.worlds.World;
import DungeonEscape.Handler;

public class GameState extends State {
	
	private World world;
        
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "src\\DungeonEscape\\graphics\\res\\worlds\\world2.txt");
                handler.setWorld(world);
        }
	
	@Override
	public void tick() {
                world.tick();       
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}
}