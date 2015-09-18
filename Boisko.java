import greenfoot.*;
import java.awt.*;
import javax.swing.*;
import java.io.IOException;

/**
 * Write a description of class Kosz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boisko extends World
{

    private static double szerokosc = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static double wysokosc = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private Uzytkownik uzytkownik;
    private Pasek bar;
    private Kosz kosz;
    private Gracz gracz;
    private Statystyki statystyki;
    
    /**
     * Constructor for objects of class Kosz.
     * 
     */
    public Boisko()
    { 
        super((int)szerokosc*2/3,(int)wysokosc*2/3,1);
        String nazwaUzytkownika = JOptionPane.showInputDialog("Wprowadz nazwe uzytkownika:");

        statystyki = Statystyki.getInstance();
        uzytkownik = new Uzytkownik(nazwaUzytkownika);
        bar = new Pasek();
        kosz = new Kosz();
        gracz = new Gracz();
        addObject(uzytkownik, (int)szerokosc*2/6, 20);
        addObject(bar, (int)szerokosc*2/6, ((int)wysokosc*2/3)-20);
        addObject(kosz, ((int)szerokosc*2/3)/8, ((int)wysokosc*2/3)/2);
        addObject(gracz, ((int)szerokosc*14/3)/8, (((int)wysokosc*2/3)/2)+50);
       
       
    }
    
    public Uzytkownik getUser() {
        return uzytkownik;
    }
    
    public Statystyki getStats() {
        return statystyki;
    }
}
