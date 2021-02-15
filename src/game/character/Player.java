package game.character;
import game.enums.Facing;
import game.level.tile.Tile;
import game.physics.AABoundingRect;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
 
public class Player extends Hero {
    private float lastX;
    private float lastY;
    public Player(float x, float y) throws SlickException{
        super(x,y);
        setSprite(new Image("res/textures/player.png"));
        setMovingAnimation(new Image[]{new Image("res/textures/player_1.png"),new Image("res/textures/player_2.png"),
                                       new Image("res/textures/player_3.png"),new Image("res/textures/player_4.png")}
                                       ,125);
        boundingShape = new AABoundingRect(x+3, y, 26, 32);
        accelerationSpeed = 0.001f;
        maximumSpeed = 0.15f;
        maximumFallSpeed = 0.3f;
        decelerationSpeed = 0.001f;
    }
    public void updateBoundingShape(){
        boundingShape.updatePosition(x+3,y);
    }
    
    public void setX(float f){
        lastX = x;
        x = f;
        updateBoundingShape();
    }
 
    public void setY(float f){
        lastY = y;
        y = f;
        updateBoundingShape();
    }
    
    public float getLastX() {
        return lastX;
    }
    
    public float getLastY() {
        return lastY;
    }
    
}