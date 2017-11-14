package logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *  
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Riha
 * @version    ZS 2016/2017
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Batoh batoh;
    private Hra hra;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     *  @param batoh je batoh, ve kterem budou ulozeny veci 
     *  @param hra je hra, kterou hrajeme a pouzijeme ji na zmenu konce hry
     */    
    public PrikazJdi(HerniPlan plan, Batoh batoh, Hra hra) {
        this.plan = plan;
        this.batoh = batoh;
        this.hra = hra;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];
        int benzin = batoh.getBenzin();
        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        boolean pruchod = plan.getAktualniProstor().getPruchod();
        boolean nepruchod = !pruchod; // takto to řeším, abych se zbavil problémů PWD
        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }

        if (smer.equals("hranice") && !batoh.obsahujeVec("brambory")){
            return "Takhle se přes hranice nedostaneš... vrat se pro kamufláž(brambory)\n";
        }
        if(smer.equals("USA")){
            sousedniProstor.setPruchod(getRandomBoolean());
            if (!sousedniProstor.getPruchod())
            {
                hra.setKonecHry(true);
                return "\nJelikož tě chytli američtí celníci, tak jsi zatčen \n a hra skončila";
            }
        }

        if(benzin > 0 && pruchod)
        {
            plan.setAktualniProstor(sousedniProstor);
            benzin = benzin -1;
            batoh.setBenzin(benzin);
            return sousedniProstor.dlouhyPopis();
        }
        else if(benzin < 0){
                return "nemáš dost benzinu\n";
            }
            else if (nepruchod){
                return "Někdo ti brání v cestě.\n";
            }
            else{
                return "nemůžeš dál\n";
                }
    }

    public static boolean getRandomBoolean() {
        return Math.random() < 0.70;
        //cim vyšší číslo tím větší pravděpodobnost průchodu
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
