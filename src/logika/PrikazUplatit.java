/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy {@code PrikazUplatit} implementuje do hry příkaz Uplatit
 *
 * @author    Tomáš Balogh
 * @version   1.00.000
 */
public class PrikazUplatit implements IPrikaz
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
    private static final String NAZEV = "uplat";
    private Batoh batoh;
    private HerniPlan plan;
    /***************************************************************************
     *
     */
    public PrikazUplatit(Batoh batoh, HerniPlan plan)
    {
        this.batoh = batoh;
        this.plan = plan;
    }

    /**
     * Metoda změní hodnotu uplaceno u postavy, která je jako parametr
     * 
     * @return text o provedení uplacení
     */
    public String proved(String... parametry){
        if (parametry.length == 0) {
            return "Nevím koho uplatit...";
        }
        String jmeno = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Postava postava = aktualniProstor.najdiPostavu(jmeno);
        if (postava == null) {
            return "Ten tu není!";
        }
        else if(batoh.getPenezenka()<=0){
        return jmeno +" nemůžeš uplatit, nemáš peníze"; 
        }
        else{
            int konto;
            konto = batoh.getPenezenka();
            konto -=500;
            postava.setProjit(true);
            batoh.setPenezenka(konto);
            return jmeno + " jsi uplatit a v penežence máš už jen: " + batoh.getPenezenka();
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
    //         PrikazUplatit instance = new PrikazUplatit();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
