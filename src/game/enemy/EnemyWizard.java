package game.enemy;

import game.enums.Facing;
import game.level.Level;
import game.level.LevelObject;
import game.physics.AABoundingRect;
import game.physics.BoundingShape;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class EnemyWizard extends LevelObject{
    private Animation animation;
     protected Facing facing;
    private boolean fly=true;
    private int rest=0;
    private int resttime=0;
    private int magicballtime=1000;
    private static Random los=new Random();
    private float starty;
      protected HashMap<Facing,Image> sprites;
    protected HashMap<Facing,Animation> movingAnimations;
    float accelerationSpeed = 0.001f;
    float maximumSpeed = 0.15f;
    float decelerationSpeed = 0.001f;
    float maxdistance=1000;
    private Level level;
    protected boolean                   moving = false;
    public EnemyWizard(float x, float y,Level level) throws SlickException {
        super(x, y);
        resttime=(20+los.nextInt(10))*1000;
        startX=x;
        starty=y;
        this.level=level;
        setSprite(new Image("res/textures/wizard.png"));
setMovingAnimation(new Image[]{new Image("res/textures/wizard.png"),new Image("res/textures/wizard_2.png"),
                                              new Image("res/textures/wizard_3.png"),new Image("res/textures/wizard_4.png")}
                                              ,125);
 
        facing = Facing.LEFT;
    }
    public void updateBoundingShape(){
        boundingShape.updatePosition(x,y);
    }
    public void attacked(){
           rest=0;
           resttime=(20+los.nextInt(10))*1000;
           fly=true;
           
           setY(starty);
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
     public void applyGravity(float gravity){
      if(fly==false) {
      if(y_velocity < maximumFallSpeed){
            y_velocity += gravity;
            if(y_velocity > maximumFallSpeed){
                y_velocity = maximumFallSpeed;
            }
        }
      }
      else
        y_velocity=0;  
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
       rest=rest+delta;
       magicballtime=magicballtime-delta;
       if(rest>resttime+7000){
           rest=0;
           resttime=(20+los.nextInt(10))*1000;
           fly=true;
           setY(starty);
       }
       if(rest>resttime){
           x_velocity=0;
           fly=false;
       }
       else{
           if(magicballtime<0){
               magicballtime=1000;
               try {
                   level.addNewLevelObject(new MagicBall(x,y,level.getCharacters().get(0).getX(),level.getCharacters().get(0).getY()));
               } catch (SlickException ex) {
                   Logger.getLogger(EnemyWizard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
               }
           }
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
