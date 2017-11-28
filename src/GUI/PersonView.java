/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.net.URL;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import logika.IHra;
import logika.Postava;
import main.Main;
import utils.Observer;

/**
 *
 * @author tomasbalogh
 */
public class PersonView extends AnchorPane implements Observer {
    
    private IHra hra;
    private ObservableList<Postava> osoby;
    private Main main;
    private AudioClip moneyNoise;
    private AudioClip gunNoise;
    private AudioClip plataOPlomo;
    /*
     * @param ihra
     * @param main
     * nastaveni konstruktoru
     */
   
    public PersonView(IHra hra, Main main){
        this.hra = hra;
        this.main = main;
        hra.getHerniPlan().registerObserver(this);
        init();
    }

    /**
     * Nastaví sledování nové hry.
     * @param novaHra Nastaví sledování nové hry.
     */
    public void newGame(IHra novaHra){
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }
/**
     * Metoda aktualizuje vse co je v panelu tim ze to vymaze a pote prida. vytvari alert zda chcete osobu uplatit nebo zabit
     * 
     */
    private void init(){
        osoby = FXCollections.observableArrayList();
        ListView<Postava> listPersons = new ListView<>(osoby);
        listPersons.setOrientation(Orientation.HORIZONTAL);
        listPersons.setStyle("-fx-background-insets: 0 ;  -fx-background-color: transparent;");
        listPersons.setPrefSize(600, 100);
        listPersons.setCellFactory((ListView<Postava> param) -> new ListCell<Postava>() {
            @Override
            protected void updateItem(Postava item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getJmeno() == null) {
                    setText(null);
                } else {
                    setText(item.getJmeno());
                }
                this.setOnMousePressed((MouseEvent event) -> {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Vyber akci");
                alert.setHeaderText("Chceš postavu " + item.getJmeno() + " zabít nebo uplatit?");
                alert.setContentText("Vyber si:");
                plataOPlomo = new AudioClip(this.getClass().getResource("/zdroje/plataoplomo.mp3").toString());
                        plataOPlomo.play();
                ButtonType buttonTypeOne = new ButtonType("Uplat");
                ButtonType buttonTypeTwo = new ButtonType("Zabij");
                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOne){
                    main.zpracujPrikazMain("uplat " + item.getJmeno());
                    hra.getHerniPlan().getAktualniProstor().odeberPostavu(item.getJmeno());
                    hra.getHerniPlan().notifyObservers();
                     moneyNoise = new AudioClip(this.getClass().getResource("/zdroje/money.mp3").toString());
                        moneyNoise.play();
                    update();
                } else {
                    main.zpracujPrikazMain("zabij " + item.getJmeno());
                    gunNoise = new AudioClip(this.getClass().getResource("/zdroje/gun.mp3").toString());
                    gunNoise.play();
                    hra.getHerniPlan().getAktualniProstor().odeberPostavu(item.getJmeno());
                    hra.getHerniPlan().notifyObservers();  
                    update();
                }
                });
            }
        });
        VBox PersonLayout = new VBox();
        Label labelExit = new Label("Osoby:");
        PersonLayout.getChildren().addAll(labelExit, listPersons);
        this.getChildren().addAll(PersonLayout);
        update();
    }
 /*
    * Metoda aktualizuje osoby
    */
    @Override
    public void update() {
        osoby.setAll();
        osoby.addAll(hra.getHerniPlan().getAktualniProstor().getPostavy().values());
    }
}
