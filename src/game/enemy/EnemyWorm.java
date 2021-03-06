package game.enemy;

import game.enums.Facing;
import game.level.LevelObject;
import game.physics.AABoundingRect;
import game.physics.BoundingShape;
import java.util.HashMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class EnemyWorm extends LevelObject{
    private Animation animation;
    protected Facing facing;
      protected HashMap<Facing,Image> sprites;
    protected HashMap<Facing,Animation> movingAnimations;
    float accelerationSpeed = 0.001f;
    float maximumSpeed = 0.15f;
    float decelerationSpeed = 0.001f;
    float maxdistance=100;
    protected boolean                   moving = false;
    public EnemyWorm(float x, float y) throws SlickException {
        super(x, y);
        startX=x;
        
        setSprite(new Image("res/textures/worm.png"));
        setMovingAnimation(new Image[]{new Image("res/textures/worm.png"),new Image("res/textures/worm_2.png"),
                                              new Image("res/textures/worm_3.png"),new Image("res/textures/worm_4.png")}
                                              ,125);
 

        facing = Facing.LEFT;
    }

    public void updateBoundingShape(){
        boundingShape.updatePosition(x,y);
    }
    public void moveleft(int delta){
        if(x_velocity > -maximumSpeed){
            x_velocity -= accelerationSpeed*delta;
            if(x_velocity < -maximumSpeed){
                x_velocity = -maximumSpeed;
            }
        }
        moving = true;
        facing = Facing.LEFT;
    }
    public void moveright(int delta){
        if(x_velocity < maximumSpeed){
            x_velocity += accelerationSpeed*delta;
            if(x_velocity > maximumSpeed){
                x_velocity = maximumSpeed;
            }
        }
        moving = true;
        facing = Facing.RIGHT;
    }
    public void update(int delta){
        if(facing == Facing.LEFT){
            moveleft(delta);
            
           if(startX-getX()>maxdistance){
               facing=Facing.RIGHT;
           }
        }
        else{
            moveright(delta);
            if(getX()>startX){
                facing=Facing.LEFT;
            }
                
        }
    }
           protected void setSprite(Image i){
        sprites = new HashMap<Facing,Image>();
        sprites.put(Facing.RIGHT, i);
        sprites.put(Facing.LEFT , i.getFlippedCopy(true, false));
    }
        protected void setMovingAnimation(Image[] images, int frameDuration){
        movingAnimations = new HashMap<Facing,Animation>();
        movingAnimations.put(Facing.RIGHT, new Animation(images,frameDuration));
 
        Animation facingLeftAnimation = new Animation();
        for(Image i : images){
            facingLeftAnimation.addFrame(i.getFlippedCopy(true, false), frameDuration);
        }
        movingAnimations.put(Facing.LEFT, facingLeftAnimation);
 
    }
    public void render(float offset_x, float offset_y) {
         if(movingAnimations != null && moving){
            movingAnimations.get(facing).draw(x-2-offset_x,y-2-offset_y);                
        }else{            
            sprites.get(facing).draw(x-2-offset_x, y-2-offset_y);          
        }
    }
}
