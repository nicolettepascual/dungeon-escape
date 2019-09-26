package DungeonEscape.states;

import DungeonEscape.Handler;
import DungeonEscape.graphics.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class WrongEndState extends State{
    
    public WrongEndState(Handler handler) {
        super(handler); 
    }
    
    public void tick(){
        if(handler.getKeyManager().exit)
            System.exit(0);
    }
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        g.drawImage(Assets.lose, 145, 100, 400, 250, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.PLAIN, 30));
        g.drawString("PRESS [Q] key to QUIT", 160, 450);
        GradientPaint gp = new GradientPaint(20, 100, Color.red, 200, 10, Color.blue);             
        g2d.setPaint(gp);
        g.drawString("Hint Word: R A I N B O W!!!", 110, 600);
    } 
}