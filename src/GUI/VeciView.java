/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Map;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logika.IHra;
import logika.Prostor;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 *
 * @author tomasbalogh
 */
public class VeciView extends ListView implements Observer  {
     private IHra hra;
     private ObservableList<Object> data = FXCollections.observableArrayList();
     
     public VeciView(IHra hra){
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        this.setItems(data);
        this.setPrefHeight(110);
        update();
        }
        
    
   
    /**
     * @param args the command line arguments
     */
   @Override
    public void update() {  
        data.clear();
        Map<String, Vec>seznamVeci = hra.getHerniPlan().getAktualniProstor().getVeci();
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
