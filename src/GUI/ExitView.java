/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logika.IHra;
import logika.Prostor;
import utils.Observer;

/**
 *
 * @author tomasbalogh
 */
public class ExitView extends ListView implements Observer  {
    
 private IHra hra;
    private final ObservableList<String> options = FXCollections.observableArrayList();
    String value = " ";

    /**
     * Konstruktor, který zavoláním metody init, zaregistruje pozorovatele a
     * nadefinuje podobu ComboBoxu.
     *
     * @param hra
     */
    public ExitView(IHra hra) {
        this.hra = hra;
        init();
    }

    /**
     * Metoda vrací ComboBox a je použita ve třídě Main k zpřístupnění
     * ComboBoxu.
     *
     * @return Vrací Combobox.
     */
//    public ComboBox getComboBox() {
//        return this;
//    }

    public void init() {
        this.setItems(options);  
        this.setEditable(true);
        this.setPrefWidth(150);
        hra.getHerniPlan().registerObserver(this);
        update();
    }

    /**
     * Metoda aktualizuje seznam při přechodu z místnosti do místnosti.
     */
    @Override
    public void update() {
        options.clear();
        for (Prostor prostor : hra.getHerniPlan().getAktualniProstor().getVychody()) {
            options.add(prostor.getNazev());
        }
    }

    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     *
     * @param hra
     */
    public void novaHra(IHra hra) {
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }
    
}
