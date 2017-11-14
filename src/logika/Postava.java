/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Instance třídy {@code Postava} představují ...
*
 * @author    Tomáš Balogh
 * @version   1.00.000
 */
public class Postava
{
    //== Datové atributy (statické i instancí)=================================
    private String jmeno;
    private String veta;
    private boolean umrit;
    private boolean uplatit;
    private boolean projit;
    private boolean uplaceno;
    private boolean zije;
    /***************************************************************************
     * Konstruktor nastaví jméno a proslov postav
     */
    public Postava(String jmeno, String veta, boolean uplatit, boolean umrit, boolean uplaceno, boolean zije, boolean projit)
    {
        this.jmeno = jmeno;
        this.veta = veta;
        this.uplatit = uplatit;
        this.umrit = umrit;
        this.uplaceno = uplaceno;
        this.zije = zije;
        this.projit = projit;
    }
    
    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     * Metoda vrací jméno postavy.
     * 
     * @return   String jméno postavy.
     */
    public String getJmeno() {
        return jmeno; 
    }
    
    /**
     * Metoda vrací proslov postavy.
     * 
     * @return   String proslov postavy.
     */
    public String getVetu() {
        return veta; 
    }
    /**
     * Metoda vrací jestli jde postava uplatit.
     * 
     * @return   boolean uplatnosti.
     */
    public boolean getUplatit() {
        return uplatit; 
    }
    /**
     * Metoda nastaví hodnotu uplatinosti
     */
    public void setUplatit(boolean uplatit) {
        this.uplatit=uplatit; 
    }
    //--------------------------
    /**
     * Metoda vrací jestli jde postava zabít.
     * 
     * @return   boolean umrnosti
     */
    public boolean getUmrit() {
        return umrit; 
    }
     /**
     * Metoda nastaví hodnotu umrtnosti
     */
    public void setUmrit(boolean umrit) {
        this.umrit=umrit; 
    }
    //--------------------------
     /**
     * Metoda vrací jestli jde přes postavu projít.
     * 
     * @return   boolean projít
     */
    public boolean getProjit() {
        return projit; 
    }
    /**
     * Metoda nastaví hodnotu průchodu
     */
    public void setProjit(boolean projit) {
        this.projit=projit; 
    }
    //--------------------------
     /**
     * Metoda vrací jestli postava je uplacena.
     * 
     * @return   boolean uplaceno
     */
     public boolean getUplaceno() {
        return uplaceno; 
    }
     /**
     * Metoda nastaví hodnotu uplaceno
     */
    public void setUplaceno(boolean uplaceno) {
        this.uplaceno=uplaceno; 
    }
    //--------------------------
     /**
     * Metoda vrací jestli postava žije.
     * 
     * @return   boolean uplaceno
     */
     public boolean getZije() {
        return zije; 
    }
     /**
     * Metoda nastaví hodnotu žije
     */
    public void setZije(boolean zije) {
        this.zije=zije; 
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
    //         Postava instance = new Postava();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
