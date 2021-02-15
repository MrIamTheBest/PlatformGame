
package game;
import static game.LevelState.level;
import game.character.Player;
import game.level.Level;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class Help extends BasicGameState {
    Image back;
    Image background;
    public static boolean nr_menu=false;
    public Help(int state){

    }
    public void init(GameContainer gc,StateBasedGame sbg)throws SlickException{
          back=new Image("res/textures/powrót.png");
          background=new Image("res/textures/tłopomoc.png");
    }
    public void render(GameContainer gc,StateBasedGame sbg,Graphics g)throws SlickException{
    background.draw(0,0);
    back.draw(450,600);
}
    public void update(GameContainer gc,StateBasedGame sbg,int delta)throws SlickException{
    int posX=Mouse.getX();
    int posY=Mouse.getY();
    if((posX>450&&posX<818)&&(posY>58&&posY<120)){
        if(Mouse.isButtonDown(0)){
        if(nr_menu==false){    
        sbg.enterState(0);
        }
        else
         sbg.enterState(4);   
    }}
}
    public int getID(){
        return 6;
    }
}