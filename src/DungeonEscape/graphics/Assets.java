package DungeonEscape.graphics;

import java.awt.image.BufferedImage;

public class Assets {
    
    private static final int width = 32, height = 32;
    public static BufferedImage floor, wall, red, orange, yellow, green, blue, indigo, violet, chest, title, win, lose;
    public static BufferedImage[] player_down, player_up, player_left, player_right, player_stand;
    
    public static void init(){
       SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("\\res\\textures\\sprite.png"));
       
       title = sheet.crop(width * 3,height * 2, 153, 100);
       win = sheet.crop(0, height * 6 , 160, 90);
       lose = sheet.crop(0,height * 9 ,167,100);
       
       player_down = new BufferedImage[3]; 
       
       player_down[0] = sheet.crop(0, height * 2, width, height);
       player_down[1] = sheet.crop(width, height * 2, width, height);
       player_down[2] = sheet.crop(width * 2, height * 2, width, height);
       
       
       player_up = new BufferedImage[3];
       
       player_up[0] = sheet.crop(0, height * 5, width, height);
       player_up[1] = sheet.crop(width, height * 5, width, height);
       player_up[2] = sheet.crop(width * 2, height * 5, width, height);
       
       player_left = new BufferedImage[3];
       
       player_left[0] = sheet.crop(0, height * 3, width, height);
       player_left[1] = sheet.crop(width, height * 3, width, height);
       player_left[2] = sheet.crop(width * 2, height * 3, width, height);
              
       player_right = new BufferedImage[3];
       
       player_right[0] = sheet.crop(0, height * 4, width, height);
       player_right[1] = sheet.crop(width, height * 4, width, height);
       player_right[2] = sheet.crop(width * 2, height * 4, width, height);
       
       player_stand = new BufferedImage[1];
       player_stand[0] = sheet.crop(width, height * 2, width, height);
       
       floor = sheet.crop(0, 0, width, height);
       wall = sheet.crop(width, 0, width, height);
       chest = sheet.crop(width * 2, 0, width, height);
       
       red = sheet.crop(0, height, width, height);
       orange = sheet.crop(width, height, width, height);
       yellow = sheet.crop(width * 2, height, width, height);
       green = sheet.crop(width * 3, height, width, height);
       blue = sheet.crop(width * 4, height, width, height);
       indigo = sheet.crop(width * 5, height, width, height);
       violet = sheet.crop(width * 6, height, width, height);  
    }
}