import greenfoot.*;

/**
 * Write a description of class PilkaNiecelna here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PilkaNiecelna extends Pilka
{
    /**
     * Act - do whatever the PilkaNiecelna wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       turnTowards(0,getWorld().getHeight());
       move(8);
       czyPunkt();
      
    }
     
    
    public PilkaNiecelna() {
        
    }  
    
    
}
