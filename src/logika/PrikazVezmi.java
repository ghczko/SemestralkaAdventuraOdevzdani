/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazVezmi implementuje do hry příkaz vezmi
*
 * @author    Tomáš Balogh
 * @version   1.00.000
 */
public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi";
    
    private Batoh batoh;
    private HerniPlan herniPlan;
    

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     */
    public PrikazVezmi(HerniPlan hPlan, Batoh batohh)
    {
        this.herniPlan = hPlan;
        this.batoh = batohh;
    }
    /**
     * vezme věc z prostoru a vloží ji do batohu
     * 
     * @return popis co zrovna metoda vzala
     */
    //== Nesoukromé metody (instancí i třídy) ======================================
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "nevím, co mám sebrat";
        }
        
        String nazevVeci = parametry[0];
        Vec vec = herniPlan.getAktualniProstor().odeberVec(nazevVeci);

        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        
        if (!vec.isPrenositelna()) {
            herniPlan.getAktualniProstor().vlozVec(vec);
            return "věc '" + nazevVeci + "' fakt neuneseš";
        }
        
        // Je třeba dokončit vložení věci do batohu (až bude tato třída existovat).
        
         if (batoh.jePlny()) {
               herniPlan.getAktualniProstor().vlozVec(vec);
               return "batoh je plny";
           }
          if (nazevVeci.equals("náboje")){
            batoh.setZasobnik(5);
            return "Dobil jsi si zásobník";
            }
          if (nazevVeci.equals("benzín")){
            batoh.setBenzin(5);
            return "Dočerpal jsi nádrž";
            }
           batoh.vlozVec(vec);
           batoh.notifyObservers();
           herniPlan.getAktualniProstor().notifyObservers();
           
           return "věc " + nazevVeci + " jsi uložil do kamionu";
          
        
       
    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
