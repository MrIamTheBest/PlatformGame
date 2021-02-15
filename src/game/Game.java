package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import static java.lang.System.in;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
    public static final int menu=0;
    public static final int levelstate=1;
    public static final int gameover=2;
    public static final int winner=3;
    public static final int menuexit=4;
    public static final int scores=5;
    public static final int help=6;
    
    public static final int     WINDOW_WIDTH= 1280;
    public static final int     WINDOW_HEIGTH=WINDOW_WIDTH / 16 * 9;
    public static final boolean FULLSCREEN= false;
    public static final float  SCALE=(float) (1.25*((double)WINDOW_WIDTH/1280));
    public static final String GAME_NAME= "Ogrodnik";
    public static int  NASIONA= 0;
     public static int  LIFE= 3;
     public static int WIZARD=5;
     public static int TIME=100000;
    
    public Game() {
        super(GAME_NAME);
        this.addState(new game.Menu(menu));
        this.addState(new game.Winner(winner));
        this.addState(new game.LevelState(levelstate));
        this.addState(new game.GameOver(gameover));
        this.addState(new game.Scores(scores));
        this.addState(new game.Help(help));
        this.addState(new game.MenuExit(menuexit));
    }
 
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        
        this.enterState(menu);
 
    }
    public static void bestscore(){
        if(!(new File("res/highscore").isFile())) {
            try{
                PrintWriter writer = new PrintWriter("res/highscore", "UTF-8");
                writer.println("0");
                writer.close();
            } catch (IOException e) {
              
            }
        }
            try{

            BufferedReader br = new BufferedReader(new FileReader("res/highscore"));
            String strLine;
           
            while ((strLine = br.readLine()) != null)   {
              int i = Integer.parseInt(strLine);
              
              if(i<Game.NASIONA){
                  i=Game.NASIONA;
                  Writer wr = new FileWriter("res/highscore");
                  wr.write(String.valueOf(i));
                  wr.close();
              }
            }
            in.close();
            }catch (Exception e){
              System.err.println("Error: " + e.getMessage());
            }finally{
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
               
        
    }
    public static int getbestscore(){
       int i = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("res/highscore"));
            String strLine;
            while ((strLine = br.readLine()) != null)   {
              i = Integer.parseInt(strLine);
            }

            in.close();
            }catch (Exception e){
              System.err.println("Error: " + e.getMessage());
            }finally{
           try {
               in.close();
           } catch (IOException ex) {
               Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
           }
            }
       return i;
    }
    public static void main(String[] args) throws SlickException, IOException {
        
         try{
         AppGameContainer app = new AppGameContainer(new Game());
         app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGTH, FULLSCREEN);
         app.setTargetFrameRate(60);
         app.setShowFPS(false);
         app.start();
          }
        catch(SlickException e){
            e.printStackTrace();
        }
    }
 
}