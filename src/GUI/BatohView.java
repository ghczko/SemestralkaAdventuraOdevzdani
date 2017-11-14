/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 *
 * @author tomasbalogh
 */
public class BatohView extends ListView implements Observer {

   private IHra hra;
   private ObservableList<Object> data = FXCollections.observableArrayList();

     /*
     * @param ihra
     */
    public BatohView(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        hra.getHerniPlan().getBatoh().registerObserver(this);
//        seznamVeci = new HashMap<String, Vec>();
       
        this.setItems(data);
        this.setPrefWidth(200);
        update();
    }

    /**
     * Metoda vymaže vše z panelu a následně aktualizuje obsah při každé změně.
     * batohu.
     */
    @Override
    public void update() {  
        
       Map<String, Vec>seznamVeci = hra.getHerniPlan().getBatoh().getObsahBatohu();
        data.clear();
        for (String x : seznamVeci.keySet()) {
            Vec pom = seznamVeci.get(x);
            ImageView picture = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/" + pom.getObrazek()), 100, 100, false, false));
            data.add(picture);
        }
    }

    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     *
     * @param hra
     */
    public void newGame(IHra hra) {
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }

  
}
