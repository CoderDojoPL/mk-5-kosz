import greenfoot.*;


/**
 * Write a description of class Pilka here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pilka extends Actor
{
    double v0;
    double sx = 0;
    double sy = 0;
    double v1;
    double v2;
    double angle = 0.1745329252;//10 degrees
    double g = 1.81D;
    double t,t1;
    int direction =0;
    int counter =0;
    int hmax = 0;
    
   
    public void ustawMiare(double angle) {
        this.angle = angle;
    }
    
    public void rzucPilke() { 
        
        Kosz kosz = (Kosz)getWorld().getObjects(Kosz.class).get(0);
        int x = kosz.getX();
        int y = kosz.getY();
        turnTowards(x, y);
    }
    
    public void rzucPilke(double angle) {      
            t = System.currentTimeMillis()/1000;
            countV1(25,angle);
            countV2(2,angle); 
            hmax = calculateHmax(v1);
            
    }
     
    public double countV1(double v0, double angle) {
        this.v1 = v0 * Math.sin(angle);
        return v1;
    }
    
    public double countV2(double v0, double angle) {
        this.v2 = v0 * Math.cos(angle);
        return v2;
    }
    
    public double getNewX( double v2, double t) {    
        sx = v2;
        return sx;
    }
    
    public int calculateHmax(double v1) {
           int hmax = (int)(Math.pow(v1, 2.0)/(2*g)); 
           return hmax;
    }
    
    public double getNewY( double v1, double t) {
        v1=v1 + g*t;
        sy = (v1*t + (g * Math.pow(t,2.0))/2);
        return sy;
    }
    
    
    public void animujLotPilki() {
      t1 = System.currentTimeMillis()/1000;
      

      int roznica = Math.abs(hmax);
            if( getY() < roznica) {
                direction = 1;
                
            }
            if(direction == 0) {
                setLocation(getX()-(int)getNewX(v2,t1-t),getY()-(int)getNewY(v1,t1-t));
            } else {
                setLocation(getX()-(int)getNewX(v2,t1-t),getY()+(int)getNewY(v1,t1-t));
            }
        
        t = t1;  
    }
    
    public boolean czyPunkt() {
        Kosz kosz = (Kosz)getWorld().getObjects(Kosz.class).get(0);
        if (Math.abs(kosz.getX() - getX()) < 60 && Math.abs(kosz.getY() - getY()) < 60) {
            Uzytkownik uzytkownik = ((Boisko)getWorld()).getUser();
            uzytkownik.dodajPunkt();        
            getWorld().removeObject(this);          
            return true;
        } else if(getX() < 15) {
           Uzytkownik uzytkownik = ((Boisko)getWorld()).getUser();
           uzytkownik.podniesLicznikBledow();
           getWorld().removeObject(this);
        }
        return false;
    }
    
   
    
   
    

}
