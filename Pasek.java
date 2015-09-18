import greenfoot.*;
import java.awt.*;

/**
 * Write a description of class Pasek here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pasek extends Actor
{
    int max = 100;
    int szerokosc = 200;
    int wysokosc = 15;
    int pozycja = 1;
    int pixelPrzezPozycje = (int)szerokosc/max;
    
    
    /**
     * Act - do whatever the Pasek wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        update();
        pozycja++;
        zresetujPasek(pozycja);
    }  
    
    public void update() {
        setImage(new GreenfootImage(szerokosc +2, wysokosc+2));
        GreenfootImage bar = getImage();
        bar.setColor(Color.WHITE);
        bar.drawRect(0, 0, szerokosc, wysokosc);
        if(pozycja < 35 || pozycja > 65) {
            bar.setColor(Color.RED);
            bar.fillRect(1,1,pozycja*pixelPrzezPozycje,wysokosc);
        } else {
           bar.setColor(Color.GREEN);
           bar.fillRect(1,1,pozycja*pixelPrzezPozycje,wysokosc); 
        }
        
    
    }
    
    public void zresetujPasek(int position) {
     if(position == 100) {
         this.pozycja = 1;
     }
                
    }
    
    public int wezPozycje() {
        return pozycja;
    }
}
