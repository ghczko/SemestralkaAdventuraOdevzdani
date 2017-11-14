/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazProzkoumej implementuje do hry příkaz prozkoumej a ukáže popis věcí
 *
 * @author    Tomáš Balogh
 * @version   1.00.000
 */
public class PrikazProzkoumej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prozkoumej";
    
    private HerniPlan hPlan;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazProzkoumej(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }
    /**
     * Zjistí popis věcí
     * 
     * @return popis věci
     */
    //== Nesoukromé metody (instancí i třídy) ======================================
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "nevím, co mám prozkoumat";
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = hPlan.getAktualniProstor().odeberVec(nazevVeci);
        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        
        hPlan.getAktualniProstor().vlozVec(vec);
        
        return nazevVeci + ": " + vec.getPopis();
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
