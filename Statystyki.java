import greenfoot.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Collections;

/**
 * Write a description of class Statystyki here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Statystyki
{
    private static Statystyki instancja = null;
    private Map<String,String> wszystkieWyniki = new HashMap<String, String>();
    private Map<String, Integer> gracze = new HashMap<String, Integer>();
    private String nazwaPliku = "GameStats.txt";
    private String sciezkaPliku = System.getProperty("user.home");
    //constructor
    private Statystyki() throws IOException {
        czytajStatystykiZPliku(nazwaPliku);       
    }

    // singleton - return instancja of class Statystyki
    public static Statystyki getInstance() {
        if(instancja == null) {
            try {
                instancja = new Statystyki();
            } catch (IOException e) {
                System.out.println("IOException caught during file operations");
            }
        }
        return instancja;
    } 

         /**
     * Read stats from file.
     *
     * @param nazwaPliku the file name
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void czytajStatystykiZPliku(String nazwaPliku) throws IOException{
            List<String> lista = new ArrayList<String>();
            BufferedReader br = null;
            FileReader czytajPlik = null;
            File file = null;
            try {

                file = new File(sciezkaPliku + File.separator + nazwaPliku);
                if (file.exists() && file.isFile()) {
                    czytajPlik = new FileReader(file);
                } else {
                    if (file.createNewFile()) {
                        wszystkieWyniki.put("PlayerWithHighestScore", "none");
                        wszystkieWyniki.put("HighestScore", "0");
                        return;
                    }
                }

                br = new BufferedReader(czytajPlik);
                String linia;
                //Read first line - overal Statystyki
                if ((linia = br.readLine()) != null) {
                    //put wszystkieWyniki to temp ArrayList
                    lista = Arrays.asList(linia.split(";"));
                }
                //put overal stats to HashMap Key:Value
                for (int i = 0; i < lista.size(); ++i) {
                    wszystkieWyniki.put((lista.get(i).split("="))[0], (lista.get(i).split("="))[1]);
                }
                
                while((linia = br.readLine()) != null) {
                    gracze.put(linia.split("=")[0], Integer.parseInt(linia.split("=")[1]));
                }
            } finally {
                if (br != null) {
                    br.close();
                }
            }         
        }
     
     
     /**
     * Gets the player wynik.
     *
     * @param nazwaGracza the player name
     * @return the player wynik
     */
    public int wezWynikGracza(String nazwaGracza) {
         Integer wynik  = gracze.get(nazwaGracza);
         if (wynik != null) {
             return wynik.intValue();
         }
         return 0;
     }
     
     /**
     * Sets the player wynik.
     *
     * @param nazwaGracza the player name
     * @param wynik the wynik
     */
    public void ustawWynikGracza(String nazwaGracza, int nowyWynik) {
         // if new wynik is lover than old wynik leave old one
         int staryWynik = wezWynikGracza(nazwaGracza);
         if (nowyWynik > staryWynik ) {
             gracze.put(nazwaGracza, nowyWynik);
         }
     }
	 
	 /**
     * Gets the highest wynik.
     *
     */
    public String wezNajwyzszyWynik() {
        
        return wszystkieWyniki.get("PlayerWithHighestScore") + ":" + wszystkieWyniki.get("HighestScore");
     }
     
     /**
     * Save stats to file.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void zapiszStatystykiDoPliku() throws IOException{
        BufferedWriter bw = null;
        FileWriter fileWriter = null;
        File file = null;
        // before saving statistics to file update high wynik
        aktualizujNajwyzszyWynik();
            
        try {

                file = new File(sciezkaPliku + File.separator + nazwaPliku);
                if (file.exists() && file.isFile()) {
                    fileWriter = new FileWriter(file);
                }

                bw = new BufferedWriter(fileWriter);
                StringBuilder stringBuilder = new StringBuilder();  
                // write first line to file with overal statistics
                for (String key : wszystkieWyniki.keySet()) {  
                 if (stringBuilder.length() > 0) {  
                  stringBuilder.append(";");  
                 }  
                 String value = wszystkieWyniki.get(key);  
                  stringBuilder.append(key);  
                  stringBuilder.append("=");  
                  stringBuilder.append(value);  
 
                }  

                bw.write(stringBuilder.toString());
                bw.newLine();
                stringBuilder.setLength(0);
                // save individual gracze wynik to file (one line per one player and wynik)
                for (String key : gracze.keySet()) {  
                    if (stringBuilder.length() > 0) {  
                      stringBuilder.append(";");  
                     }  
                    String value = gracze.get(key).toString();   
                    stringBuilder.append(key);  
                    stringBuilder.append("=");  
                    stringBuilder.append(value); 
                    bw.write(stringBuilder.toString());
                    bw.newLine();
                    stringBuilder.setLength(0);
                }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }         
    }

    /**
     * Update high wynik in overal statistics.
     */
    private void aktualizujNajwyzszyWynik() {

         Map<String, Integer> map = sortujPoWynikach(gracze); 
         // sorted hashmap
          Set<Entry<String, Integer>> set = map.entrySet();
          Iterator<Entry<String, Integer>> iterator = set.iterator();
          if (iterator.hasNext()) {
               Map.Entry<String, Integer> me2 = iterator.next();
               wszystkieWyniki.put("PlayerWithHighestScore", me2.getKey());
               wszystkieWyniki.put("HighestScore", me2.getValue().toString());
          }
    }
    
    /**
     * Sort by scores.
     *
     * @param gracze the gracze
     * @return the hash map
     */
    private HashMap<String, Integer> sortujPoWynikach(Map<String, Integer> gracze) { 
        Set<Entry<String, Integer>> zbior = gracze.entrySet();
        //convert set () of Entry to LinkedList
        List<Entry<String, Integer>> lista = new LinkedList<Entry<String, Integer>>(zbior);
        // Defined Custom Comparator
        Collections.sort(lista, new Comparator<Entry<String, Integer>>() {
                @Override
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                   return ((Comparable<Integer>) (o2).getValue())
                      .compareTo((o1).getValue());
                }
           });

        //sorted list -> HasMap
        // using LinkedHashMap to preserve the insertion order
        HashMap<String, Integer> posortowanaMapa = new LinkedHashMap<String, Integer>();
        Iterator<Entry<String, Integer>> iterator = lista.iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, Integer> wpis = iterator.next();
            posortowanaMapa.put(wpis.getKey(), wpis.getValue()); 
        } 
        return posortowanaMapa;
      }
}
