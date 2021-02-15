package game;
import game.character.Player;
import game.controller.MouseAndKeyBoardPlayerController;
import game.controller.PlayerController;
import game.enemy.EnemyFly;
import game.enemy.EnemyWizard;
import game.enemy.EnemyWorm;
import game.enemy.MagicBall;
import game.level.Level;
import static game.level.Level.levelID;
import game.level.LevelObject;
import game.physics.Physics;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Color;
public class LevelState extends BasicGameState {
    public static Level  level;
    String startinglevel;
    private Player player;
    public static Music music;
    public static Sound music_over;
    public static Sound sound;
    public static Sound fail;
    public static Sound winner;
    private PlayerController playerController;
    private Physics physics = new Physics();
     public static boolean needToMapUpdate = false;
    public LevelState(int state){
    }
    @Override
    public void init(GameContainer gc,StateBasedGame sbg)throws SlickException{
        sound=new Sound("res/music/Jump.ogg");
        fail=new Sound("res/music/Fail.ogg");
       winner=new Sound("res/music/winner.ogg");
         updateGameStatus(gc);
          
    }
    @Override
    public void render(GameContainer gc,StateBasedGame sbg,Graphics g)throws SlickException{
        g.scale(Game.SCALE, Game.SCALE);
        level.render();
        g.setColor(Color.black);
        g.drawString("Zebrane nasiona: " + Game.NASIONA, 20, 20);
        g.drawString("Zycie: " + Game.LIFE, 20, 40);
        g.drawString("Czas: " + Game.TIME/1000, 900, 20);
        if(level.levelID==5){
        g.drawString("Zycie czarodzieja: " + Game.WIZARD, 20, 60);
        }
        
}
    @Override
    public void update(GameContainer gc,StateBasedGame sbg,int delta)throws SlickException{
        Game.TIME=Game.TIME-delta;
        playerController.handleInput(gc.getInput(), delta);
        Input input=gc.getInput();
        for(LevelObject obj: level.getLevelObjects()){
             if(obj instanceof EnemyWorm){
                 ((EnemyWorm)obj).update(delta);
             }
              if(obj instanceof EnemyWizard){
                 ((EnemyWizard)obj).update(delta);
             }
              if(obj instanceof EnemyFly){
                 ((EnemyFly)obj).update(delta);
             }
        }
        
        if(level.getNewLevelObject() != null){
            level.addLevelObject(level.getNewLevelObject());
            level.addNewLevelObject(null);
        }
        
         physics.handlePhysics(level, delta);
         if(input.isKeyDown(Input.KEY_ESCAPE)){
             sbg.enterState(4);
         }
         if(Game.TIME<=0){
              music_over.play(1.0f, 1.0f);
             sbg.enterState(2);
             needToMapUpdate = true;
         }
         if(Game.LIFE==0){
             music_over.play(1.0f, 1.0f);
             sbg.enterState(2);
             needToMapUpdate = true;
         }
          if(Game.WIZARD==0){
             winner.play(1.0f, 1.0f);
             Game.NASIONA=Game.NASIONA+(Game.NASIONA/1000)/10;
             sbg.enterState(3);
             needToMapUpdate = true;
         }
         if (needToMapUpdate) {
            updateGameStatus(gc);
        }
}
    @Override
    public int getID(){
        return 1;
    }
    public static Level getLevel(){
        return level;
    }
    private void updateGameStatus(GameContainer gc) throws SlickException {
        Game.LIFE=3;
        Game.NASIONA=0;
        music=new Music("res/music/music.ogg");
        music.setVolume(0.1f);
        music.loop();
        player = new Player(100,300);
        level = new Level(startinglevel, player);
        level.backgroundID=1;
        playerController = new MouseAndKeyBoardPlayerController(player);
         physics = new Physics();
        needToMapUpdate = false;
    }
}