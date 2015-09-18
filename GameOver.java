import greenfoot.*;
import java.awt.*;

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends Actor
{
    /**
     * Act - do whatever the GameOver wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        pokazGameOver();
    } 
    
    public void pokazGameOver() {   
            GreenfootImage gameOver = new GreenfootImage("GAME OVER", 100, Color.RED, Color.BLACK);
            setImage(gameOver);
            Greenfoot.stop();
    }
}
