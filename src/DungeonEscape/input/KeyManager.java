package DungeonEscape.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
    private boolean[] keys;
    public boolean up, down, left, right, open, start, exit;
    
    public KeyManager(){
        keys = new boolean[256];
    }
    
    public void tick(){
       
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        
        start = keys[KeyEvent.VK_ENTER];
        open = keys[KeyEvent.VK_SPACE];
        exit = keys[KeyEvent.VK_Q];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        keys[e.getKeyCode()] = false;
    }  
}