package logika;


/**
 * Třída Hra - třída představující logiku adventury.
 * 
 * Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan,
 * která inicializuje místnosti hry a vytváří seznam platných příkazů a instance tříd
 * provádějící jednotlivé příkazy. Vypisuje uvítací a ukončovací text hry. Také
 * vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Tomas balogh
 * @version    ZS 2016/2017
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private Batoh batoh;
    private boolean konecHry = false;
   

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        batoh = new Batoh(); 
        herniPlan = new HerniPlan(batoh);
           
        batoh.setPenezenka(1000);
        batoh.setBenzin(6);
        batoh.setZasobnik(3);
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan,batoh, this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazVezmi(herniPlan,batoh));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazStatus(batoh));
        platnePrikazy.vlozPrikaz(new PrikazUplatit(batoh, herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZabij(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazUkazBatoh(batoh));
        platnePrikazy.vlozPrikaz(new PrikazProdej(herniPlan,batoh, this));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítejte!\n" +
               "Toto je příběh o pašování kokainu do USA\nHraješ za Papla Escobara.\n" +
               "Napište 'napoveda', pokud si nevíte rady, jak hrát dál.\n" +
               "Hra skončí, když prodáte kokain ve skladu.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Dík, že jste si zahráli.  Ahoj.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
  
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
    
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
       
        return textKVypsani;
    }
    //konecHry = true;
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní IPrikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
     /**
      *  Metoda vrátí odkaz na batoh.
      *  
      *  @return     odkaz na batoh
      */
     public Batoh getBatoh(){
        return batoh;
     }
      /**
      *  Metoda vrátí odkaz na hru.
      *  
      *  @return     odkaz na hru
      */
     public Hra getHra(){
        return this;
        }
    
}
