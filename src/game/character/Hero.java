package game.character;

import game.LevelState;
import game.enums.Facing;
import game.level.LevelObject;
import java.util.HashMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
public abstract class Hero extends LevelObject{
    
    protected Image sprite;
    protected Facing facing;
    protected HashMap<Facing,Image> sprites;
    protected HashMap<Facing,Animation> movingAnimations;
    protected long                      lastTimeMoved;
    protected boolean                   moving = false;
    protected float                     accelerationSpeed = 1;
    protected float                     decelerationSpeed = 1;
    protected float                     maximumSpeed = 1;
 
 
    public Hero(float x, float y) throws SlickException{
        super(x,y);
        facing = Facing.RIGHT;
        setSprite(new Image("res/textures/placeholder_sprite.png"));
    }
 
    public float getX(){
        return x;
    }
 
    public float getY(){
        return y;
    }
    @Override
    public void render(float offset_x, float offset_y){
        if(movingAnimations != null && moving){
            movingAnimations.get(facing).draw(x-2-offset_x,y-2-offset_y);                
        }else{            
            sprites.get(facing).draw(x-2-offset_x, y-2-offset_y);          
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
         public boolean isMoving(){
        return moving;
    }
 
    public void setMoving(boolean b){
        moving = b;
    }
    public void decelerate(int delta) {
        if(x_velocity > 0){
            x_velocity -= decelerationSpeed * delta;
            if(x_velocity < 0)
                x_velocity = 0;
        }else if(x_velocity < 0){
            x_velocity += decelerationSpeed * delta;
            if(x_velocity > 0)
                x_velocity = 0;
        }
    }
 
    public void jump(){
        if(onGround) {
            y_velocity = -0.6f;
        LevelState.sound.play();
        }
    }
 
    public void moveLeft(int delta){

        if(x_velocity > -maximumSpeed){
            x_velocity -= accelerationSpeed*delta;
            if(x_velocity < -maximumSpeed){
                x_velocity = -maximumSpeed;
            }
        }
        moving = true;
        facing = Facing.LEFT;
    }
 
    public void moveRight(int delta){
        if(x_velocity < maximumSpeed){
            x_velocity += accelerationSpeed*delta;
            if(x_velocity > maximumSpeed){
                x_velocity = maximumSpeed;
            }
        }
        moving = true;
        facing = Facing.RIGHT;
    }


}
