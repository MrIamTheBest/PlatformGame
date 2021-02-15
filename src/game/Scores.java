
package game;
import static game.Help.nr_menu;
import static game.LevelState.level;
import java.awt.Font;
import game.level.Level;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class Scores extends BasicGameState {
    Image back;
    Image background;
    public float myFont,attributes;
    public Scores(int state){

    }
    public void init(GameContainer gc,StateBasedGame sbg)throws SlickException{
          back=new Image("res/textures/powrót.png");
          background=new Image("res/textures/tłoscore.png");
    }
    public void render(GameContainer gc,StateBasedGame sbg,Graphics g)throws SlickException{
    background.draw(0,0);
    back.draw(450,600);
    
    Font awtFont = new Font("Serif", Font.PLAIN, 50);
    TrueTypeFont font = new TrueTypeFont(awtFont, false);
        g.setFont(font);
        g.setColor(Color.red);
        g.drawString(""+Game.getbestscore(), 600, 175);
        g.drawString("" + Game.NASIONA, 600, 300);
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
        return 5;
    }
}