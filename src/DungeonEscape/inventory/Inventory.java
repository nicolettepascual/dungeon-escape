package DungeonEscape.inventory;

import DungeonEscape.Handler;
import DungeonEscape.items.Item;
import DungeonEscape.states.RightEndState;
import DungeonEscape.states.State;
import DungeonEscape.states.WrongEndState;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Inventory{
        
        public State WrongEndState, RightEndState;
        private Queue<Integer> BaseQueue, KeyQueue;
        private Handler handler;
	private boolean active = false;
	
	public Inventory(Handler handler) {
		this.handler = handler;
                
                BaseQueue = new LinkedList<>();
                KeyQueue = new LinkedList<>();
                WrongEndState = new WrongEndState(handler);  
                RightEndState = new RightEndState(handler);
                
                for(int i=0; i <= 6; i++){
                    BaseQueue.add(i);
                }
                
                
	}
        
	public void tick(){}
	
	public void render(Graphics g){
		if(!active)
			return;
	}
	
	// Inventory methods
	
	public void addItem(Item item){
                KeyQueue.add(item.getId()); 
                    
                Iterator<Integer> kq = KeyQueue.iterator(); 
                if(KeyQueue.size() == 7){    
                    while(kq.hasNext()){
                        if(! (BaseQueue.poll() == KeyQueue.poll()) )
                        {
                            State.setState(WrongEndState); 
                            break;
                        } else
                            State.setState(RightEndState); 
                    }
                }
	}

	// GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
