/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import javafx.scene.image.Image;
import main.Main;



/*******************************************************************************
 * Instance třídy Vec představují ...
*
 * @author    Tomáš Balogh
 * @version   1.00.000
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String popis;
    private boolean prenositelna;
    private String obrazek;
    private Image obrazekFile;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, String popis, boolean prenositelna, String obrazek)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
        this.obrazek = obrazek;
        this.obrazekFile = new Image(Main.class.getResource("/zdroje/" + obrazek).toString(), 100, 100, false, false);
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    public String getNazev() {  // získá název veci
        return nazev;
    }
    
    public String getPopis() { // získá popis věci
        return popis;
    }
    
    public boolean isPrenositelna() { // zjisti jestli je věc přenositelná
        return prenositelna;
    }
    
    public String getObrazek() {
        return obrazek;
    }
    public Image getObrazekFile(){
    return obrazekFile;
    }

    // Možná bude potřeba přidat settery pro atributy 'popis' a 'prenositelna'.
    // Atribut 'nazev' by se měnit neměl.

    //== Soukromé metody (instancí i třídy) ========================================

}
