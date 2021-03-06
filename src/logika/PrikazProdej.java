/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.media.AudioClip;



/*******************************************************************************
 * Instance třídy {@code PrikazProdej} implementuje do hry příkaz prodej
*
 * @author    Tomáš Balogh
 * @version   1.00.000
 */
public class PrikazProdej implements IPrikaz
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
    private static final String NAZEV = "prodej";
    private HerniPlan herniPlan;
    private Batoh batoh;
    private Hra hra;
    private Vec zbozi;
    private AudioClip winNoise;
    /***************************************************************************
     *konstruktor
     */
    public PrikazProdej(HerniPlan herniPlan, Batoh batoh, Hra hra)
    {
        this.herniPlan = herniPlan;
        this.batoh = batoh;
        this.hra = hra;
    }
    /**
     * vezme věc (parametr) z batohu a přepíše hodnotu v proměné peněženky. 
     * 
     * 
     */
    
    public void init(String hlaska){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Konec hry");
                        alert.setHeaderText(hlaska);
                        winNoise = new AudioClip(this.getClass().getResource("/zdroje/win.mp3").toString());
                        winNoise.play();
                        ButtonType buttonTypeOne = new ButtonType("KONEC");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == buttonTypeOne){
                        System.exit(0);
                        }
    }
    public String proved(String... parametry){
        if (parametry.length < 1) {
            return "nevím, co mám prodat";
        }
        String nazevVeci = parametry[0];
        
        if (parametry.length < 1) {
            // pokud chybí druhé slovo (prodejni vec), tak ....
            return "Nevím, co chceš prodat!";
        }
        System.out.println(nazevVeci);
        
        if(herniPlan.getAktualniProstor().getNazev().equals("Sklad"))
        {   
            zbozi = batoh.odeberVec(nazevVeci);
            if (zbozi.getNazev().equals("kokain"))
            {batoh.setPenezenka(1000000);
            hra.setKonecHry(true);
            String konec = "Gratuluji Paplo! Prodal jsi " + zbozi.getNazev() + " za cenu " + batoh.getPenezenka() +
            " pesos.";
            init(konec);
            return konec;
            }
            else if(zbozi.getNazev().equals("brambory")){
            batoh.setPenezenka(5000);
            hra.setKonecHry(true);
            String konec = "Gratuluji Paplo! Prodal jsi " + zbozi.getNazev() + " za cenu " + batoh.getPenezenka() +
            " pesos.";
            init(konec);
            return konec;
            
            }else{
            return "To, co jsi vybral, tak nelze prodat.\n";
            }
            
        }
        else{
            return "můžeš prodávat jen v USA ve skladu.\n";
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
    //         PrikazProdej instance = new PrikazProdej();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
