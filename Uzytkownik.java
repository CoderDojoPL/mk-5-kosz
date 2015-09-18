import greenfoot.*;
import java.awt.*;

/**
 * Write a description of class Uzytkownik here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Uzytkownik extends Actor
{
    
    String nickname = "";
    int wynik = 0;
    int licznikBledow = 0;
    /**
     * Act - do whatever the Uzytkownik wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setImage(new GreenfootImage("Gracz: " + this.nickname + " - Score: " + wynik + "; Highest wynik: " +
        Statystyki.getInstance().wezNajwyzszyWynik(), 24, Color.GREEN, Color.BLACK));
    }
    
    public Uzytkownik(String nickname) {
     this.nickname = nickname;
     this.wynik = 0;
    }
    
    public void dodajPunkt() {
     wynik++;    
    }
    
    public void podniesLicznikBledow() {
     licznikBledow++;    
    }
    
    public int getScore() {
        return wynik;
    }
    
    public int wezLicznikBledow() {
        return licznikBledow;
    }
    
    public String getNickName() {
        return nickname;
    }
    
    
}
