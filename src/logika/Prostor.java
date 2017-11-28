package logika;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import utils.Observer;
import utils.Subject;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Tomáš Balogh
 * @version ZS 2016/2017
 */
public class Prostor implements Subject
{

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci;  // seznam věcí v prostoru
    private Postava postavicka;  // pomocná proměná pro metody
    private Map<String, Postava> seznamPostav;
    private Set<Vec> setVeci;
    private double posLeft;
    private double posTop;
     private final List<Observer> listObserveru = new ArrayList<>();
    //seznam postav v prostoru
    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis, double posLeft, double posTop) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new HashMap<>();
        
        this.posLeft = posLeft;
        this.posTop = posTop;
        seznamPostav = new HashMap<String, Postava>();
    }
  public double getPosLeft() {
        return posLeft;
    }

    public double getPosTop() {
        return posTop;
    }
    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
        + popisVychodu() + "\n"
        + popisVeci() + "\n"
        + SeznamPostav();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "vychody:";
        for (Prostor sousedni : vychody) {
            vracenyText += ", " + sousedni.getNazev();
        }
        return vracenyText;
    }
    /**
     * Vrací textový řetězec seznamu věcí, například:
     * "věci: pistole ".
     *
     * @return seznam věcí - názvy věcí
     */
    private String popisVeci() {
        String vracenyText = "veci:";
        for (String nazev : veci.keySet()) {
            vracenyText += " " + nazev;
        }

        return vracenyText;
    }
    /**
     * Vrací textový řetězec seznamu postav, například:
     * "jsou zde tyto postavy: zloděj ".
     *
     * @return seznam postav - názvy postav
     */
    public String SeznamPostav() {
        String nazvy = "jsou zde tyto postavy: ";

        for (String jmenoPostav : seznamPostav.keySet()){
            postavicka = seznamPostav.get(jmenoPostav);
            if (postavicka.getZije()){
                nazvy += jmenoPostav + " ";
            }
        }
        if (nazvy == "jsou zde tyto postavy: "){
            return "jsou zde tyto postavy: nikdo";
        }
        return nazvy;
    }
    /**
     * Vrací postavu, podle jména, 
     *
     * @return objekt postava 
     */
    public Postava najdiPostavu(String jmeno)
    {
        return seznamPostav.get(jmeno);
    }
    /**
     * Vrací zjistí, jestli nějaká postava nebrání v průchodu
     *
     * @return true/false průchodnost 
     */
    public boolean getPruchod() {
        boolean projit = true;
        postavicka = null;
        for (String jmenoPostavy : seznamPostav.keySet()){
            postavicka = seznamPostav.get(jmenoPostavy);
            if (postavicka.getProjit() == false){
                projit = false;
            }
        }
        return projit;
    }
    /**
     * nastaví hodnotu, průchodnosti
     * tato funkce existuje, jen kvůli nastavení průchodnosti do USA(random)
     * 
     */
    public void setPruchod(boolean pruchod) {
        boolean projit = true;
        postavicka = null;
        for (String jmenoPostavy : seznamPostav.keySet()){
            postavicka = seznamPostav.get(jmenoPostavy);
            postavicka.setProjit(pruchod);

        }
    }
    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
            .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
            .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    /**
     * nastaví postavu do prostoru
     *
     * 
     */
    public void vlozPostavu(Postava postava)
    {
        seznamPostav.put(postava.getJmeno(), postava);
    }
    /**
     * vloží věc do prostoru
     * 
     */
    public void vlozVec(Vec vec) {
        veci.put(vec.getNazev(), vec);
    }
    
     public Postava getPostava(String jmeno){
        return seznamPostav.get(jmeno);
    } 
    public Map<String, Postava> getPostavy() {
        return seznamPostav;
    }
    
    /**
     * Vrací, co se odstranilo z prostoru
     *
     * @return Vec vec co se odstranilo
     */
    public Vec odeberVec(String nazev) {
        return veci.remove(nazev);
    }
    public Postava odeberPostavu(String jmeno){
        return seznamPostav.remove(jmeno);
    }
    public Map<String, Vec> getVeci() {
        return veci;
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
        for (Observer observer : listObserveru) {
            observer.update();
        }
    }
}
