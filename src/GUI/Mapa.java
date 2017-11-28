/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.Hra;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 *
 * @author xzenj02, tomasbalogh
 */
public class Mapa extends AnchorPane implements Observer {

    private IHra hra;
    private Circle tecka;
    private ImageView pabloImageView;

    public Mapa(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
/*
    *Metoda nastavi mapu a panacka a zavola metodu update
    */
    private void init() {

        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.png"), 600, 400, false, true));
        pabloImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/pablo.png"), 30, 40, false, true));
        



        this.getChildren().addAll(obrazekImageView, pabloImageView);
        update();
    }
        /**
     *
     * @param novaHra
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     */
    public void newGame(IHra novaHra){
        hra.getHerniPlan().removeObserver(this);
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
 /*
    * Metoda aktualizuje mapu
    */
    @Override
    public void update() {
        this.setTopAnchor(pabloImageView, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(pabloImageView, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }

}
