
package game;
import static game.LevelState.level;
import game.enemy.EnemyFly;
import game.enemy.EnemyWizard;
import game.enemy.EnemyWorm;
import game.enemy.Plant;
import game.level.Finish;
import game.level.Heart;
import game.level.Level;
import game.level.Objective;
import game.serialization.SaveData;
import game.serialization.SaveLoad;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class MenuExit extends BasicGameState {
    Image playNow;
    Image exitGame;
    Image save;
    Image help;
    Image scores;
    Image background;
    public MenuExit(int state){

    }
    public void init(GameContainer gc,StateBasedGame sbg)throws SlickException{
         playNow=new Image("res/textures/graj.png");
          exitGame=new Image("res/textures/koniec.png");
          save=new Image("res/textures/zapisz.png");
          scores=new Image("res/textures/wyniki.png");
          help=new Image("res/textures/pomoc.png");
          background=new Image("res/textures/tłopanelstartowy.png");
    }
    public void render(GameContainer gc,StateBasedGame sbg,Graphics g)throws SlickException{
    background.draw(0,0);
    playNow.draw(550,220);
    save.draw(550,300);
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
    }}
    if((posX>550&&posX<742)&&(posY>358&&posY<420)){
        if(Mouse.isButtonDown(0)){
        SaveData save=new SaveData();
        save.background=Level.backgroundID;
        save.life=Game.LIFE;
        save.map=Level.levelID;
        save.seeds=Game.NASIONA;
        save.time=Game.TIME;
        save.tab=new String[LevelState.getLevel().getLevelObjects().size()];
        save.positionx=new float[LevelState.getLevel().getLevelObjects().size()];
        save.positiony=new float[LevelState.getLevel().getLevelObjects().size()];
        for(int i=0;i<LevelState.getLevel().getLevelObjects().size();i++){
            save.positionx[i]=LevelState.getLevel().getLevelObjects().get(i).getX();
            save.positiony[i]=LevelState.getLevel().getLevelObjects().get(i).getY();
            if(LevelState.getLevel().getLevelObjects().get(i) instanceof EnemyWorm){
                save.tab[i]="EnemyWorm";
                save.positionx[i]=LevelState.getLevel().getLevelObjects().get(i).getStartX();
            }
            if(LevelState.getLevel().getLevelObjects().get(i) instanceof EnemyWizard){
                save.tab[i]="Wizard";
                save.positionx[i]=LevelState.getLevel().getLevelObjects().get(i).getStartX();
            }
            if(LevelState.getLevel().getLevelObjects().get(i) instanceof Objective){
                save.tab[i]="Objective";
            }
            if(LevelState.getLevel().getLevelObjects().get(i) instanceof Plant){
                save.tab[i]="Plant";
            }
            if(LevelState.getLevel().getLevelObjects().get(i) instanceof EnemyFly){
                save.tab[i]="Fly";
                save.positionx[i]=LevelState.getLevel().getLevelObjects().get(i).getStartX();
            }
            if(LevelState.getLevel().getLevelObjects().get(i) instanceof Heart){
                save.tab[i]="Heart";
            }
            if(LevelState.getLevel().getLevelObjects().get(i) instanceof Finish){
                save.tab[i]="Finish";
            }
        }
        save.playerx=level.getCharacters().get(0).getX();
        save.playery=level.getCharacters().get(0).getY();
        SaveLoad.save(save);
    }}
     if((posX>540&&posX<760)&&(posY>278&&posY<340)){
        if(Mouse.isButtonDown(0)){
        Help.nr_menu=true;
        sbg.enterState(5);
    }}
     if((posX>550&&posX<742)&&(posY>198&&posY<260)){
        if(Mouse.isButtonDown(0)){
        Help.nr_menu=true;
        sbg.enterState(6);
    }}
    if((posX>550&&posX<742)&&(posY>118&&posY<180)){
        if(Mouse.isButtonDown(0)){
        System.exit(0);
    }}
}
    public int getID(){
        return 4;
    }
}