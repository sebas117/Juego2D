package Entities;

import Tile.Tile;
import gfx.Screen;
import level.Level;
import gfx.HUD;

public abstract class Mob extends Entity {
    
    protected String name;
    protected int speed;
    private HUD hud;
    protected int numSteps = 0;
    protected boolean isMoving;
    protected int movingDir = 1; //0(up), 1(down), 2(left), 3(right)

    public Mob(Level level, String name, int x, int y, int speed) {
        super(level);
        this.name = name;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }
    
    public void move(int xa, int ya){                                          // how many units in each direction
        if(xa!=0 & ya!=0){                                                     //si el mob intenta moverse en el eje x y y al tiempo
            move(xa, 0);
            move(0, ya);
            numSteps --;
            return;
        }
        numSteps ++;
        //if(!hasCollided(xa, ya)){
        if(true){
            if(ya<0) movingDir = 0;
            if(ya>0) movingDir = 1;
            if(xa<0) movingDir = 2;
            if(xa>0) movingDir = 3;
            
            x += xa * speed;
            y += ya * speed;
        }
    }
    
    public abstract boolean hasCollided(int xa, int ya); 
    
    protected boolean isSolid(int xa, int ya, int x, int y){
        if(level == null) return false;
        Tile lastTile = level.getTile((this.x+x)>>3, (this.y+y)>>3);
        Tile newTile = level.getTile((this.x+x+xa)>>3, (this.y+y+ya)>>3);
        if(!lastTile.equals(newTile) && newTile.isSolid()) return true;
        return false;
    }
        
    public abstract void tick();

    public abstract void render(Screen screen);
    
    public String getName(){
        return name;
    }

}