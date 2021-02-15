package game;
import static game.LevelState.music;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class Winner extends BasicGameState {
    Image back;
    Image background;
    public Winner(int state){
       
    }
    public void init(GameContainer gc,StateBasedGame sbg)throws SlickException{
        back=new Image("res/textures/powrót.png");
        background=new Image("res/textures/winner.png");  
        LevelState.music_over=new Sound("res/music/GameOver.ogg");
    }
    public void render(GameContainer gc,StateBasedGame sbg,Graphics g)throws SlickException{
    background.draw(0,0);
    back.draw(450,600);
}
    public void update(GameContainer gc,StateBasedGame sbg,int delta)throws SlickException{
    int posX=Mouse.getX();
    int posY=Mouse.getY();
    LevelState.music.stop(); 
    if((posX>450&&posX<818)&&(posY>58&&posY<120)){
        if(Mouse.isButtonDown(0)){
        music=new Music("res/music/music.ogg");
        music.setVolume(0.1f);
        music.loop();
        sbg.enterState(0);
    }}
}
    public int getID(){
        return 3;
    }
}