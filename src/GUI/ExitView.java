/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import logika.IHra;
import logika.Prostor;
import main.Main;
import utils.Observer;

/**
 *
 * @author tomasbalogh
 */
public class ExitView extends AnchorPane implements Observer  {
    
    private IHra hra;
    private ObservableList<Prostor> exits;
    private Main main;

    /**
     * konstuktor k nadefinov8n9 exitview
     *
     * @param hra konstuktor k nadefinov8n9 exitview
     * @param main konstuktor k nadefinov8n9 exitview
     */
    public ExitView(IHra hra, Main main) {
        this.hra = hra;
        this.main = main;
        init();
    }
    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     *
     * @param hra Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     */
  public void novaHra(IHra hra) {
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }
  
/*
  * vytvoreni listview a nstaveni click to action..
  */
    public void init() {
       
        exits = FXCollections.observableArrayList();
        ListView<Prostor> listExits = new ListView<>(exits);
        listExits.setPrefHeight(630);
        listExits.setStyle("-fx-background-insets: 0 ; -fx-background-color: transparent;");
        listExits.setCellFactory(param -> new ListCell<Prostor>() {
            @Override
            protected void updateItem(Prostor item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNazev() == null) {
                    setText(null);
                } else {
                    setText(item.getNazev());
                    setFont(Font.font(20));
                                     
                }
                this.setOnMousePressed(event -> {
                    main.zpracujPrikazMain("jdi "+item.getNazev());
                    update();
                });
            }

        });
        VBox ExitLayout = new VBox();
        Label labelExit = new Label("Východy:");
        ExitLayout.getChildren().addAll(labelExit, listExits);
        this.getChildren().addAll(ExitLayout);
        update();
    }

    
    
    @Override
    public void update() {
        exits.setAll();
        exits.addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
    }

    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     *
     * @param hra
     */
  
    
}
