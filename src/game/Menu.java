
package game;
import static game.LevelState.level;
import game.level.Level;
import game.serialization.SaveData;
import game.serialization.SaveLoad;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class Menu extends BasicGameState {
    Image playNow;
    Image exitGame;
    Image load;
    Image help;
    Image scores;
    Image background;
    public Menu(int state){

    }
    public void init(GameContainer gc,StateBasedGame sbg)throws SlickException{
         playNow=new Image("res/textures/graj.png");
          exitGame=new Image("res/textures/koniec.png");
          load=new Image("res/textures/wczytaj.png");
          scores=new Image("res/textures/wyniki.png");
          help=new Image("res/textures/pomoc.png");
          background=new Image("res/textures/tłopanelstartowy.png");
    }
    public void render(GameContainer gc,StateBasedGame sbg,Graphics g)throws SlickException{
    background.draw(0,0);
    playNow.draw(550,220);
    load.draw(540,300);
    scores.draw(550,380);
    help.draw(550,460);
    exitGame.draw(550,540);
 
}
    public void update(GameContainer gc,StateBasedGame sbg,int delta)throws SlickException{
    int posX=Mouse.getX();
    int posY=Mouse.getY();
    if((posX>550&&posX<742)&&(posY>439&&posY<500)){
        if(Mouse.isButtonDown(0)){
        sbg.enterState(1);
        LevelState.getLevel().levelID=0;
        LevelState.getLevel().updateLevel();
    }}
    if((posX>540&&posX<760)&&(posY>358&&posY<420)){
        if(Mouse.isButtonDown(0)){
        SaveData data;
        data=SaveLoad.load();
        LevelState.getLevel().levelID=data.map;
        LevelState.getLevel().backgroundID=data.background;
        Game.LIFE=data.life;
        Game.NASIONA=data.seeds;
        LevelState.getLevel().updateLevel(data.playerx, data.playery);
        Game.TIME=data.time;
        level.reloadObjects(data.tab, data.positionx, data.positiony);
        
        sbg.enterState(1);
         
    }}
    if((posX>540&&posX<760)&&(posY>278&&posY<340)){
        if(Mouse.isButtonDown(0)){
        Help.nr_menu=false;
        sbg.enterState(5);
    }}
    if((posX>550&&posX<742)&&(posY>198&&posY<260)){
        if(Mouse.isButtonDown(0)){
        Help.nr_menu=false;
        sbg.enterState(6);
    }}
    if((posX>550&&posX<742)&&(posY>118&&posY<180)){
        if(Mouse.isButtonDown(0)){
        System.exit(0);
    }}
}
    public int getID(){
        return 0;
    }
}