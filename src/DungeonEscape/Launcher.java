package DungeonEscape;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Launcher {
    public static void main(String[] args) throws Exception {
       Game game = new Game("DUNGEON ESCAPE", 700,700);
       game.start();
       
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src\\DungeonEscape\\graphics\\res\\music\\BGM.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(inputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        Thread.sleep(10000000); 
    }
}