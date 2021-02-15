package game.enemy;
import game.level.LevelObject;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MagicBall extends LevelObject{
    private Animation animation;
   private float velocity= 0.3f;
    public MagicBall(float x, float y,float targetx,float targety) throws SlickException{
         super(x, y);

      animation = new Animation(new Image[]{new Image("res/textures/pocisk.png"),new Image("res/textures/pocisk_2.png"),
                                              new Image("res/textures/pocisk_3.png"),new Image("res/textures/pocisk_4.png")}
                                              ,new int[]{200,125,125,200});
 
        animation.setPingPong(true);
        float angle=(float) Math.atan2(targety-y, targetx-x);
        x_velocity=(float) Math.cos(angle)*velocity;
        y_velocity=(float)Math.sin(angle)*velocity;
    }
     public void applyGravity(float gravity){
      
    }
    

         public void render(float offset_x, float offset_y) {
        animation.draw(x-2-offset_x,y-2-offset_y);
    }

}
