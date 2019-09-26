package DungeonEscape.states;

import DungeonEscape.Handler;
import DungeonEscape.graphics.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class RightEndState extends State{
    
    public RightEndState(Handler handler) {
        super(handler); 
    }
    
    public void tick(){
        if(handler.getKeyManager().start)
            System.exit(0);
    }
    
    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        g.drawImage(Assets.win, 145, 100, 400, 250, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.PLAIN, 30));
        g.drawString("Press [ENTER] key to CLOSE", 110, 450);
        g.drawString("\u00a9 BCS21 - GROUP 8", 190, 600);
    }
}