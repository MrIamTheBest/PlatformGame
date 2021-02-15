package game.level;
import game.Game;
import game.character.Hero;
import game.character.Player;
import game.enemy.EnemyFly;
import game.enemy.EnemyWizard;
import game.enemy.EnemyWorm;
import game.enemy.Plant;
import game.level.tile.AirTile;
import game.level.tile.SolidTile;
import game.level.tile.Tile;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
 
public class Level {
  private Player player;
  private TiledMap map;
  public static int levelID=0;
  public static int backgroundID=1;
   public Image background;
   private ArrayList<LevelObject> levelObjects;
   private LevelObject newLevelObject=null;
  private Tile[][] tiles;
    private ArrayList<Hero> hero;
    
    public Level(String level, Player player) throws SlickException{
        map = new TiledMap("res/levels/" + Integer.toString(levelID) + ".tmx");
        hero = new ArrayList<Hero>();
        levelObjects = new ArrayList<LevelObject>();
        this.player = player;
        addCharacter(player);
        loadTileMap();
        loadObjects();
        background=new Image("res/textures/poziom1.png"); 
    }
       public void updateLevel() throws SlickException{
        map = new TiledMap("res/levels/" + Integer.toString(levelID) + ".tmx");
        hero = new ArrayList<Hero>();
        levelObjects = new ArrayList<LevelObject>();
        addCharacter(player);
        if(levelID==5){
        Game.TIME=1000000; 
        }
        else if(levelID==0){
        Game.TIME=50000; 
        }
        else
        Game.TIME=100000;
        player.setX(100);
        player.setY(300);
        loadTileMap();
        loadObjects();
        background=new Image("res/textures/poziom" + Integer.toString(backgroundID) + ".png"); 
       }
       public void updateLevel(float px, float py) throws SlickException{
        map = new TiledMap("res/levels/" + Integer.toString(levelID) + ".tmx");
        hero = new ArrayList<Hero>();
        levelObjects = new ArrayList<LevelObject>();
        addCharacter(player);
        if(levelID==5){
        Game.TIME=1000000; 
        }
        else if(levelID==0){
        Game.TIME=50000; 
        }
        else
        Game.TIME=100000;
        player.setX(px);
        player.setY(py);
        loadTileMap();
        loadObjects();
        background=new Image("res/textures/poziom" + Integer.toString(backgroundID) + ".png"); 
       }
    public void addCharacter(Hero c){
        hero.add(c);
    }
 
    public ArrayList<Hero> getCharacters(){
        return hero;
    }
    public void addLevelObject(LevelObject obj){
        levelObjects.add(obj);
    }
    
    public void addNewLevelObject(LevelObject obj){
        newLevelObject = obj;
    }
    
    public LevelObject getNewLevelObject(){
        return newLevelObject;
    }
    
   public ArrayList<LevelObject> getLevelObjects(){
        return levelObjects;
    }
 
    public Tile[][] getTiles(){
        return tiles;
    }
    public void reloadObjects(String tab[],float positionx[],float positiony[]){
       levelObjects.clear();
       
       for(int i=0;i<tab.length;i++){
           if(tab[i] != null) {
                try {
                    switch(tab[i]){
                        case "Objective":
                        addLevelObject(new Objective(positionx[i],positiony[i]));
                        break;
                        case "EnemyWorm":
                        addLevelObject(new EnemyWorm(positionx[i],positiony[i]));
                        break;
                        case "Wizard":
                        addLevelObject(new EnemyWizard(positionx[i],positiony[i],this));
                        break;
                        case "Fly":
                        addLevelObject(new EnemyFly(positionx[i],positiony[i]));
                        break;
                        case "Finish":
                        addLevelObject(new Finish(positionx[i],positiony[i]));
                        break;
                        case "Heart":
                        addLevelObject(new Heart(positionx[i],positiony[i]));
                        break;
                        case "Plant":
                        addLevelObject(new Plant(positionx[i],positiony[i]));
                        break;
                    }
                } catch (SlickException ex) {
                    Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
           
            }
       }
    }
    private void loadObjects() throws SlickException{

        int objectAmount = map.getObjectCount(0);
        for(int i = 0; i < objectAmount; i++){
            switch(map.getObjectName(0, i)){
                case "Objective":
                    addLevelObject(new Objective(map.getObjectX(0, i),map.getObjectY(0, i)));
                    break;
                case "Enemy":
                    addLevelObject(new EnemyWorm(map.getObjectX(0, i),map.getObjectY(0, i)));
                    break;
                case "Finish":
                    addLevelObject(new Finish(map.getObjectX(0, i),map.getObjectY(0, i)));
                    break;
                     case "Plant":
                    addLevelObject(new Plant(map.getObjectX(0, i),map.getObjectY(0, i)));
                    break;
                     case "Fly":
                    addLevelObject(new EnemyFly(map.getObjectX(0, i),map.getObjectY(0, i)));
                    break;
                    case "Heart":
                    addLevelObject(new Heart(map.getObjectX(0, i),map.getObjectY(0, i)));
                    break;
                case "Wizard":
                    addLevelObject(new EnemyWizard(map.getObjectX(0, i),map.getObjectY(0, i),this));
                    break;
            }
        }
 
    }
 private void loadTileMap(){
        tiles = new Tile[map.getWidth()][map.getHeight()];
 
        int layerIndex = map.getLayerIndex("CollisionLayer");
 
        if(layerIndex == -1){
            System.err.println("Map does not have the layer \"CollisionLayer\"");
            System.exit(0);
        }
 
        for(int x = 0; x < map.getWidth(); x++){
            for(int y = 0; y < map.getHeight(); y++){
                int tileID = map.getTileId(x, y, layerIndex);
 
                Tile tile = null;
                switch(tileID) {
                    case 0:
                        tile = new AirTile(x,y);
                        break;
                    default:
                        tile = new SolidTile(x,y);
                        break;
                }
                tiles[x][y] = tile;
            }
        }
    }
  public void removeObject(LevelObject obj){
        levelObjects.remove(obj);
    }
 
    public void removeObjects(ArrayList<LevelObject> objects) {
        levelObjects.removeAll(objects);
    }
     
  public int getXOffset(){
        int offset_x = 0;
        int half_width = (int) (Game.WINDOW_WIDTH/Game.SCALE/2);
        int maxX = (int) (map.getWidth()*32)-half_width;
 
        if(player.getX() < half_width){
            offset_x = 0;
        }else if(player.getX() > maxX){
            offset_x = maxX-half_width;
        }else{
            offset_x = (int) (player.getX()-half_width);
        }
 
        return offset_x;
    }
   public int getYOffset(){
        int offset_y = 0;
 
        int half_heigth = (int) (Game.WINDOW_HEIGTH/Game.SCALE/2);
 
        int maxY = (int) (map.getHeight()*32)-half_heigth;
 
        if(player.getY() < half_heigth){
            offset_y = 0;
        }else if(player.getY() > maxY){
            offset_y = maxY-half_heigth;
        }else{
            offset_y = (int) (player.getY()-half_heigth);
        }
 
        return offset_y;
    }
   
    public void render(){
     int offset_x = getXOffset();
     int offset_y = getYOffset();
    
         background.draw(0,0);
        map.render(-(offset_x%32), -(offset_y%32), offset_x/32, offset_y/32, 33, 19);
 
       for(LevelObject obj : levelObjects){
            obj.render(offset_x, offset_y);
        }
        for(Hero c : hero){
            c.render(offset_x,offset_y);
        }
         
    }

 
}