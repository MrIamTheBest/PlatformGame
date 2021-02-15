package game.controller;
 
import game.LevelState;
import game.character.Player;
 
import org.newdawn.slick.Input;
 
public class MouseAndKeyBoardPlayerController extends PlayerController {
    public MouseAndKeyBoardPlayerController(Player player) {
        super(player);
    }
 
    public void handleInput(Input i, int delta) {
        handleKeyboardInput(i,delta);
    }
 
   private void handleKeyboardInput(Input i, int delta){
        if(i.isKeyDown(Input.KEY_A) || i.isKeyDown(Input.KEY_LEFT)){
            player.moveLeft(delta);
        }else if(i.isKeyDown(Input.KEY_D) || i.isKeyDown(Input.KEY_RIGHT)){
            player.moveRight(delta);
        }else{
            player.setMoving(false);
        }
 
        if(i.isKeyDown(Input.KEY_SPACE)){
           
            player.jump();
            
        }
    }
}