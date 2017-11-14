/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Instance třídy {@code PrikazUkazBatoh} provádí akce s objektem batoh
*
 * @author    Tomáš Balogh
 * @version   1.00.000
 */
public class PrikazUkazBatoh implements IPrikaz
{
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    //== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ====================
    //== KONSTANTNÍ ATRIBUTY INSTANCÍ ==========================================
    //== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ====================================
    //== OSTATNÍ NESOUKROMÉ METODY TŘÍDY =======================================
     private static final String NAZEV = "obsah_kamionu";
    private Batoh batoh;
    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /***************************************************************************
     * konstruktor
     */
    public PrikazUkazBatoh(Batoh batoh)
    {
        this.batoh = batoh;
    }
    /**
     *  Metoda vrací názvy věcí v kamionu
     *  
     *  @return nazev prikazu
     */
    public String proved(String... parametry){
    return batoh.nazvyVeciVBatohu();  // vypíše názvy věcí v kamionu
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
    //         PrikazUkazBatoh instance = new PrikazUkazBatoh();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
