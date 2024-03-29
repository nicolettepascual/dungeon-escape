package DungeonEscape;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import DungeonEscape.display.Display;
import DungeonEscape.graphics.Assets;
import DungeonEscape.graphics.GameCamera;
import DungeonEscape.input.KeyManager;
import DungeonEscape.states.GameState;
import DungeonEscape.states.MenuState;
import DungeonEscape.states.State;

public class Game implements Runnable{
    private Display display;
    private final int width, height; 
    public String title;
    private boolean running = false;
    
    private Thread thread; 
    
    private BufferStrategy bs;
    private Graphics g;
    
    //States
    public State gameState;
    public State menuState;
    
    //Input
    private final KeyManager keyManager;
    
    //Camera
    private GameCamera gameCamera;
    
    //Handler
    private Handler handler;
    
    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();
        
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0,0);
       
        
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(menuState);
    }
    
    int x = 0;
    
    private void update(){
       keyManager.tick();
        
        if(State.getState() != null)
           State.getState().tick();
       
    }
    
    private void render(){ 
        bs = display.getCanvas().getBufferStrategy(); 
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        
        if(handler.getKeyManager().exit)
            System.exit(0);
        
        //Clear the Screen
        g.clearRect(0, 0, width, height);
        
        //START DRAWING HERE    
        if(State.getState() != null)
           State.getState().render(g);
        //STOP DRAWING
        bs.show(); 
        g.dispose();
    }
    
    public void run() { 
        init();
        int fps = 60; 
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now; 
        long lastTime = System.nanoTime(); 
        long timer = 0; 
        int ticks = 0;
        
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) /timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            if(delta >= 1){
                update();
                render();
                ticks++;
                delta--;
            }
        }
        stop();
    }
    
    public KeyManager getKeyManager(){
        return keyManager;
    }
    
    public GameCamera getGameCamera(){
        return gameCamera;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try{
            thread.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}