package game.level;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Objective extends LevelObject {
 
    protected Animation animation;
 
    public Objective(float x, float y) throws SlickException {
        super(x, y);
        animation = new Animation(new Image[]{new Image("res/textures/nasionko.png"),new Image("res/textures/nasionko_2.png"),
                                              new Image("res/textures/nasionko_3.png"),new Image("res/textures/nasionko_4.png")}
                                              ,new int[]{200,125,125,200});
 
        animation.setPingPong(true);
 
    }
    
    public void render(float offset_x, float offset_y) {
        animation.draw(x-2-offset_x,y-2-offset_y);
    }
 
}