import greenfoot.*;
import java.io.IOException;

/**
 * Write a description of class Gracz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gracz extends Actor
{
    private GameOver gameOver;
    /**
     * Act - do whatever the Gracz wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
         if(Greenfoot.isKeyDown("space") && czyJestPilka()) {
             dodajPilke();
         }
         pokazGameOver();
         
    } 
    
    public void dodajPilke() {
        Pilka pilka;
        Pasek bar = (Pasek)getWorld().getObjects(Pasek.class).get(0);
        if (bar.wezPozycje() >= 35 && bar.wezPozycje() <= 65) {
            pilka = new PilkaOK();    
        } else {
           pilka = new PilkaNiecelna(); 
        }
        getWorld().addObject(pilka, (int)(getWorld().getWidth()*0.80272328), (int)(getWorld().getHeight()*0.5484375));
    }
    
    public boolean czyJestPilka() {
        if (getWorld().getObjects(Pilka.class).size() == 0) {
            return true;
        }
        return false;
    }
    
    public void pokazGameOver() {
        Uzytkownik uzytkownik = (Uzytkownik)(getWorld().getObjects(Uzytkownik.class).get(0));
       
        if(uzytkownik.wezLicznikBledow() > 2) {
             
        try {
           Statystyki statystyki = Statystyki.getInstance();
           statystyki.ustawWynikGracza(uzytkownik.getNickName(), uzytkownik.getScore());
           statystyki.zapiszStatystykiDoPliku();
        } catch (IOException e) {
            System.out.println("IOException caught during file operations");
        }
            gameOver = new GameOver();
            getWorld().addObject(gameOver,(int)getWorld().getWidth()*3/6, (int)getWorld().getWidth()/3);
        }
    }
}
