package game.physics;

import game.Game;
import game.LevelState;
import static game.LevelState.music;
import game.character.Hero;
import game.character.Player;
import game.enemy.EnemyFly;
import game.enemy.EnemyWizard;
import game.enemy.EnemyWorm;
import game.enemy.MagicBall;
import game.enemy.Plant;
import game.level.Finish;
import game.level.Heart;
import game.level.Level;
import static game.level.Level.levelID;
import game.level.LevelObject;
import game.level.Objective;
import game.level.tile.Tile;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class Physics {
     private final float gravity = 0.0015f;
 
    public void handlePhysics(Level level, int delta){
        handleCharacters(level,delta);
        handleLevelObjects(level,delta);
    }
     private boolean checkCollision(LevelObject obj, Tile[][] mapTiles) throws java.lang.ArrayIndexOutOfBoundsException{
        if(obj instanceof MagicBall){
            return false;
        }
        ArrayList<Tile> tiles = obj.getBoundingShape().getTilesOccupying(mapTiles);
        for(Tile t : tiles){
            if(t.getBoundingShape() != null){
                if(t.getBoundingShape().checkCollision(obj.getBoundingShape())){
                    return true;
                }
            }
        }
        return false;
    }
      private boolean isOnGround(LevelObject obj, Tile[][] mapTiles){
        ArrayList<Tile> tiles = obj.getBoundingShape().getGroundTiles(mapTiles);
        obj.getBoundingShape().movePosition(0, 1);
 
        for(Tile t : tiles){
            if(t.getBoundingShape() != null){
                if(t.getBoundingShape().checkCollision(obj.getBoundingShape())){
                    obj.getBoundingShape().movePosition(0, -1);
                    return true;
                }
            }
        }
        obj.getBoundingShape().movePosition(0, -1);
 
        return false;
    }
      
      private void handleCharacters(Level level, int delta){
        
          for(Hero c : level.getCharacters()){
            if(!c.isMoving()){
                c.decelerate(delta);
            }
            handleGameObject(c,level,delta);
            if(c instanceof Player){
 
                ArrayList<LevelObject> removeQueue = new ArrayList<LevelObject>();
                for(LevelObject obj : level.getLevelObjects()){
 
                    if(obj instanceof Objective){
                        if(obj.getBoundingShape().checkCollision(c.getBoundingShape())){
                            Game.NASIONA++;
                            Game.bestscore();
                            removeQueue.add(obj);
                        }
                    }
                      if(obj instanceof Heart){
                        if(obj.getBoundingShape().checkCollision(c.getBoundingShape())){
                            Game.LIFE+=3;
                            removeQueue.add(obj);
                        }
                    }
                    if(obj instanceof Finish){
                        if(obj.getBoundingShape().checkCollision(c.getBoundingShape())){
                            level.levelID++;
                            level.backgroundID++;
                            Game.NASIONA+=(Game.TIME/1000)/10;
                            try {
                                level.updateLevel();
                                if(levelID==5){
                                    music=new Music("res/music/fight.ogg");
                                    music.setVolume(0.1f);
                                    music.loop();
                                }
                            } catch (SlickException ex) {
                                Logger.getLogger(Physics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                            }

                        }   
                    }
                      if(obj instanceof MagicBall){
                        if(obj.getBoundingShape().checkCollision(c.getBoundingShape())){
                            
                            Game.LIFE--;
                            if(Game.LIFE>0){
                                 LevelState.fail.play();
                             }
                            c.setY(300);
                            c.setX(100);
                        }
                    }
                      if(obj instanceof Plant){
                        if(obj.getBoundingShape().checkCollision(c.getBoundingShape())){
                           
                            Game.LIFE--;
                            if(Game.LIFE>0){
                                LevelState.fail.play();
                             }
                            c.setY(300);
                            c.setX(100);
                        }
                    }
                      if(obj instanceof EnemyFly){
                        if(obj.getBoundingShape().checkCollision(c.getBoundingShape())){
                            
                            Game.LIFE--;
                            if(Game.LIFE>0){
                                LevelState.fail.play();
                            }
                            c.setY(300);
                            c.setX(100);
                        }
                    }
                     if(obj instanceof EnemyWizard){
                       if(obj.getBoundingShape().checkCollision(c.getBoundingShape())
                               && ((Player)c).getLastY()+32 <= obj.getY()){
                            Game.WIZARD--;
                            ((EnemyWizard)obj).attacked();
                        }
                         if(obj.getBoundingShape().checkCollision(c.getBoundingShape())
                                 && ((Player)c).getLastY()+32 > obj.getY()){
                            Game.LIFE--;
                             if(Game.LIFE>0){
                                LevelState.fail.play();
                             }
                            c.setY(300);
                            c.setX(100);
                        }
                    }
                    if(obj instanceof EnemyWorm){
                        if(obj.getBoundingShape().checkCollision(c.getBoundingShape())
                                && ((Player)c).getLastY()+32 <= obj.getY()){
                            Game.NASIONA++;
                            Game.bestscore();
                            removeQueue.add(obj);
                        }
                        if(obj.getBoundingShape().checkCollision(c.getBoundingShape())
                                && ((Player)c).getLastY()+32 > obj.getY()){
                            Game.LIFE--;
                            if(Game.LIFE>0){
                                LevelState.fail.play();
                            }
                            c.setY(300);
                            c.setX(100);
                        }
                    }
                }

                level.removeObjects(removeQueue);
            }
 
        }
          
    }
      private void handleLevelObjects(Level level, int delta){
        ArrayList<LevelObject> removeQueue = new ArrayList<LevelObject>();
        for(LevelObject obj : level.getLevelObjects()){
            if(handleGameObject(obj,level,delta)==true)
                removeQueue.add(obj);
        }
        level.removeObjects(removeQueue);
        
    }
      private boolean handleGameObject(LevelObject obj, Level level, int delta){
 
        if(!(obj instanceof MagicBall)){
                obj.setOnGround(isOnGround(obj,level.getTiles()));
        }
        if(!obj.isOnGround())
        obj.applyGravity(gravity * delta);
        
        float x_movement = obj.getXVelocity()*delta;
        float y_movement   = obj.getYVelocity()*delta;

        try {
        obj.setX(obj.getX() + x_movement);
        if(checkCollision(obj, level.getTiles())) {
            obj.setX(obj.getX() - x_movement);
        }
        obj.setY(obj.getY() + y_movement);
        if(checkCollision(obj, level.getTiles())) {
            obj.setY(obj.getY() - y_movement);
            obj.setYVelocity(0);
        }
        }
        catch(java.lang.ArrayIndexOutOfBoundsException e) {
            if(obj instanceof Player){
                
            Game.LIFE--;
            if(Game.LIFE>0){
                LevelState.fail.play();
                }
            obj.setY(300);
            obj.setX(100);
            }
            if(obj instanceof MagicBall){

                 return true;
            }
        }
        return false;
    }
}
