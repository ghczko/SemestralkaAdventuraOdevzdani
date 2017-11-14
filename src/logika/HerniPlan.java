package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Tomáš Balogh
 * @version    ZS 2016/2017
 */
public class HerniPlan implements Subject{

    private Prostor viteznyProstor;
    private Batoh batoh;
    private Prostor aktualniProstor;
    private final List<Observer> listObserveru = new ArrayList<Observer>();
    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(Batoh batoh) {
        this.batoh = batoh;
        zalozProstoryHry();

       
    }

    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor medellin = new Prostor("Medellín","Medellín - Rodné město Pabla Escobara", 10, 10);
        Prostor vyrobna = new Prostor("výrobna","Výrobna kokainu, tvé skryté doupě", 20, 10);
        Prostor hranice = new Prostor("hranice","Hraniční přechod s Panamou", 30, 10);
        Prostor panama = new Prostor("Panama","Panama je zajímává tím, že přes ní vede průplav, kde se pohybuje spoustu nepřátel kartelu", 40, 10);
        Prostor honduras = new Prostor("Honduras","Honduras je chudá země, která ti slouží na doplnění zásob", 50, 10);
        Prostor mexiko = new Prostor("Mexiko","Mexiko - Jakmile jsi přijel do Mexika, jsi skoro u cíle, ale to ví i tvý nepřátelé...", 60, 10);
        Prostor hranicni_prechod = new Prostor("USA","Hranice s USA je velmi nebezpečná a nelze celníky uplatit",70, 10);
        Prostor sklad = new Prostor("Sklad", "Sklad, kde se dostává kokain mezi obyčejné lidi",80, 10);

        // přiřazují se průchody mezi prostory (sousedící prostory)

        medellin.setVychod(vyrobna);
        vyrobna.setVychod(hranice);
        hranice.setVychod(panama);
        panama.setVychod(honduras);
        honduras.setVychod(mexiko);
        mexiko.setVychod(hranicni_prechod);
        hranicni_prechod.setVychod(sklad);
        vyrobna.setVychod(medellin);
        hranice.setVychod(vyrobna);
        panama.setVychod(hranice);
        honduras.setVychod(panama);
        mexiko.setVychod(honduras);
        hranicni_prechod.setVychod(mexiko);

        // vytvoříme několik věcí
        Vec brambory = new Vec("brambory", "brambory si vem za účelem maskování kokainu", true, "brambory.jpg");
        Vec kokain = new Vec("kokain", "Tvoje zboží, co prodáváš", true, "kokain.jpg");
        Vec benzin = new Vec("benzín", "Benzin co potrebujes, aby jsi dojel do USA", true, "benzin.jpg");
        Vec pistole = new Vec("pistole", "Pistole na obranu tveho zbozi", true, "pistole.jpg");
        Vec naboje = new Vec("náboje", "Náboje do tvé pistole", true, "naboje.jpg");
        Vec socha  = new Vec("socha", "Socha tvé postavy Pabla Escobara", false, "socha.jpg");
        Vec stroj  = new Vec("stroj", "Stroj na výrobu kokainu", false, "stroj.jpg");
        Vec pruplav = new Vec("průplav", "průplav mezi oceany", false, "pruplav.jpg");
        Vec auto = new Vec("auto", "Auto celníků", false, "test.jpg");
        medellin.vlozVec(brambory);
        vyrobna.vlozVec(kokain);
        honduras.vlozVec(benzin);
        vyrobna.vlozVec(pistole);
        honduras.vlozVec(naboje);
        medellin.vlozVec(socha);
        vyrobna.vlozVec(stroj);
        panama.vlozVec(pruplav);
        hranicni_prechod.vlozVec(auto);

        

        //umistime postavy do prostoru   nazev/popis/uplatit/jestlimuzeumrit/uplaceno/zije/jestlimuzesprojit
        hranice.vlozPostavu(new Postava("celník", "Ahoj Paplo, ukaž mi náklad, co vezeš.", true, true,false, true, false));
        hranice.vlozPostavu(new Postava("zloděj", "Ahoj Paplo, dej sem zboží nebo je zabiju", false, true,false, true, false));
        mexiko.vlozPostavu(new Postava("zloděj", "Ahoj Paplo, dej sem zboží nebo je zabiju", false, true,false, true, false));
        mexiko.vlozPostavu(new Postava("zloděj", "Ahoj Paplo, dej sem zboží nebo je zabiju", false, true,false, true, false));
        panama.vlozPostavu(new Postava("celník", "Ahoj Paplo, ukaž mi náklad, co vezeš.", true, true, false, true, false));
        hranicni_prechod.vlozPostavu(new Postava("celnici", "Buenos días, show us your load - tyto celníky nemůžeš ani uplatit ani zabít \n zkus to znova a čekej na štěstí \n", false, false, false, true, false));
        aktualniProstor = medellin; 
        viteznyProstor = sklad;// hra začíná v domečku

    }
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       notifyObservers();
    }
    /**
     *  Metoda vrací odkaz na vítězný prostor.
     *
     *@return     vítězný prostor
     */
    public Batoh getBatoh(){
        return batoh;
    }
    public Prostor getViteznyProstor() {
        return viteznyProstor;
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }

}
