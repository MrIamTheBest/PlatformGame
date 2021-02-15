package game.level;

import game.physics.AABoundingRect;
import game.physics.BoundingShape;

public abstract class LevelObject {
 
    protected float x;
    protected float y;
    protected float startX;
    protected BoundingShape boundingShape;
 
    protected float    x_velocity = 0;
    protected float    y_velocity = 0;
    protected float    maximumFallSpeed = 1;
 
    protected boolean  onGround = true;
 
    public LevelObject(float x, float y){
        this.x = x;
        this.y = y;
        boundingShape = new AABoundingRect(x,y,32,32);
    }
 
    public void applyGravity(float gravity){
        if(y_velocity < maximumFallSpeed){
            y_velocity += gravity;
            if(y_velocity > maximumFallSpeed){
                y_velocity = maximumFallSpeed;
            }
        }
    }
 
    public float getYVelocity() {
        return y_velocity;
    }
 
    public void setYVelocity(float f){
        y_velocity = f;
    }
 
    public float getXVelocity(){
        return x_velocity;
    }
 
    public void setXVelocity(float f){
        x_velocity = f;
    }
 
    public float getX(){
        return x;
    }
 
    public float getY(){
        return y;
    }
 
    public void setX(float f){
        x = f;
        updateBoundingShape();
    }
 
    public void setY(float f){
        y = f;
        updateBoundingShape();
    }
 
    public void setStartX(float f) {
        startX = f;
    }
    
    public float getStartX() {
        return startX;
    }
    
    public void updateBoundingShape(){
        boundingShape.updatePosition(x,y);
    }
 
    public boolean isOnGround(){
        return onGround;
    }
 
    public void setOnGround(boolean b){
        onGround = b;
    }
 
    public BoundingShape getBoundingShape(){
        return boundingShape;
    }

   public abstract void render(float offset_x, float offset_y);
 
}