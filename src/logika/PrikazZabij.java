/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy {@code PrikazZabij} implementuje do hry příkaz zabij
*
 * @author    Tomáš Balogh
 * @version   1.00.000
 */
public class PrikazZabij implements IPrikaz
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
    private static final String NAZEV = "zabij";
    private Batoh batoh;
    private HerniPlan plan;
    /***************************************************************************
     *
     */
    public PrikazZabij(HerniPlan plan, Batoh batoh)
    {
        this.batoh = batoh;
        this.plan = plan;
    }
    /**
     * Metoda provede změnu hodnoty proměné u postavy, která je v parametru. 
     * 
     * @return text o provedení zabití
     */
    public String proved(String... parametry){
        if (parametry.length == 0) {
            return "Nevím koho zabit...";
        }
        String jmeno = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Postava postava = aktualniProstor.najdiPostavu(jmeno);
        if (postava == null) {
            return "Ten tu není!";
        }
        else if (batoh.getZasobnik() > 0 && postava.getUmrit() && batoh.obsahujeVec("pistole")){
            int naboje = batoh.getZasobnik();
            naboje -= 1;
            postava.setProjit(true);
            postava.setZije(false);
            batoh.setZasobnik(naboje);
            return jmeno + " jsi zabil\n";
        }
        else if (batoh.obsahujeVec("pistole")){
        return "nemáš ho jak zabít\nChybí ti zbraň";
        }
        else if (batoh.getZasobnik() <= 0 ){
        return "nemáš náboje\n";
        }
        else{
        return "nemůžeš ho zabít\n";
        }
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
    //         PrikazZabij instance = new PrikazZabij();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
