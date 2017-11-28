/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;




import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import utils.Observer;
import utils.Subject;



/*******************************************************************************
 * Instance třídy {@code Batoh} představují ...
 *
 * @author    Tomáš Balogh
 * @version   1.00.000
 */
public class Batoh implements Subject
{
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    //== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ====================
    //== KONSTANTNÍ ATRIBUTY INSTANCÍ ==========================================
    //== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ====================================
    //== OSTATNÍ NESOUKROMÉ METODY TŘÍDY =======================================

    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    private Map <String, Vec> seznamVeci;  //seznam věcí v batohu(kamionu)
    
    private int penezenka; //číselná hodnota v peněžence
    private int benzin; //číselná hodnota v nádrži
    private int zasobnik; //číselná hodnota v zásobníku
    private final List<Observer> listObserveru = new ArrayList<>();
    /***************************************************************************
     * konstruktor 
     */
    public Batoh()   
    {
        seznamVeci = new HashMap<String, Vec>();  
    }

   /**
    * zjistí jestli je batoh plný
    * @return je batoh plný?
    */
    public boolean jePlny()  
    {
        return !(seznamVeci.size() < 5);
    }
    /**
     * vloží věc z parametru do batohu
     * @return vlozila se správně?
     * @param vec kterou chceme vlozit
     */
    public boolean vlozVec(Vec vec) {  
        if (!jePlny() && vec.isPrenositelna()) {
        seznamVeci.put(vec.getNazev(), vec);
        return true;
    }
    return false;
    }
    
    /**
     * odebere věc z batohu, která je v parametru funkce
     * @param nazev 
     * název veci co chceme odebrat
     * @return Vec 
     * co se odebrala
     */
    
    public Vec odeberVec(String nazev) {  //odebere věc z batohu, která je v parametru funkce
        return seznamVeci.remove(nazev);
    }

    public void setPenezenka(int penezenka){ // nastaví velikost peněženky
        this.penezenka = penezenka;
    }

    public int getPenezenka(){ // získá velikost peněženky
        return penezenka;
    }
    public void setBenzin(int benzin){ // nastaví velikost nádrže
        this.benzin = benzin;
    }

    public int getBenzin(){// získá velikost nádrže
        return benzin;
    }
    public void setZasobnik(int zasobnik){ // nastaví velikost zásobníku
        this.zasobnik = zasobnik;
    }

    public int getZasobnik(){// získá velikost zásobníku
        return zasobnik;
    }
    public String nazvyVeciVBatohu() {  // vypíše věci z batohu
        String nazvy = "věci v kamionu: ";
        for (String jmenoVeci : seznamVeci.keySet()){
            nazvy += jmenoVeci + " ";
        }
        return nazvy;
    }
    public boolean obsahujeVec (String jmeno) {  // vrátí boolean hodnotu jestli se věc nachází v batohu
        if (this.seznamVeci.containsKey(jmeno)) {
            return true;
        }
        return false;
    }
    
    public Map<String, Vec> getObsahBatohu() {
        return seznamVeci;
    }
    
       @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : listObserveru) {
            observer.update();
        }
    }
    
    
  
    
    
    //== ABSTRAKTNÍ METODY =====================================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================
    //== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ====================================
    //== SOUKROMÉ A POMOCNÉ METODY TŘÍDY =======================================
    //== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
    //== TESTOVACÍ METODY A TŘÍDY ==============================================
    //
    //     /********************************************************************
    //      * Testovací metoda.
    //      */
    //     public static void test()
    //     {
    //         Batoh instance = new Batoh();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }

   
    
}
