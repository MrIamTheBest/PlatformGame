
package game.level;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Finish extends LevelObject{
     protected Animation animation;
    public Finish(float x, float y) throws SlickException {
        super(x, y);

        animation = new Animation(new Image[]{new Image("res/textures/flaga.png"),new Image("res/textures/flaga_2.png"),
                                              new Image("res/textures/flaga_3.png"),new Image("res/textures/flaga_4.png")}
                                              ,new int[]{200,125,125,200});
 
        animation.setPingPong(true);

    }
 
    public void render(float offset_x, float offset_y) {
        animation.draw(x-2-offset_x,y-2-offset_y);
    }
 
}
