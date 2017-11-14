/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Instance třídy {@code PrikazStatus} implementuje přikaz ukazStatus a vypíše proměné...
*
 * @author    Tomáš Balogh
 * @version   1.00.000
 */
public class PrikazStatus implements IPrikaz
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
    private static final String NAZEV = "ukazStatus";
    private Batoh batoh;
    /***************************************************************************
     * konstruktor batohu
     */
    public PrikazStatus(Batoh batoh)
    {
        this.batoh = batoh;
     }
     // 
     /**
     *  vypíše kolik máš v peněžence, kolik máš nábojů a kolik benzínu.
     *  
     *  @return hodnoty proměných
     */
     public String proved(String... parametry){ 
           return "V penezence mas "+ batoh.getPenezenka() + " pesos \nV zásobníku máš " 
           + batoh.getZasobnik() + " nábojů\nV nadrzi mas "+ batoh.getBenzin() + "litrů";
    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev(){
       return NAZEV;
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
    //         PrikazStatus instance = new PrikazStatus();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
