/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 * @author tomasbalogh
 * BatohView funguje jako zobrazeni co ma uzivatel v batohu
 */
    public class BatohView extends AnchorPane implements Observer {
        private ObservableList<Vec> predmety;
        private IHra hra;
        private Main main;
        private ObservableList<Object> data = FXCollections.observableArrayList();

     /*
     * @param ihra
     * @param main
     * nastaveni konstruktoru
     */
    public BatohView(IHra hra, Main main) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        this.main = main;
        hra.getHerniPlan().getBatoh().registerObserver(this);
          init();
        this.setPrefWidth(200);
        update();
    }

    /**
     * Metoda aktualizuje vse co je v panelu tim ze to vymaze a pote prida. nastavuje i velikost 
     * batohu.
     * 
     */
    
    public void init(){
    predmety = FXCollections.observableArrayList();
    ListView<Vec> listItems = new ListView<>(predmety);
    listItems.setPrefHeight(630);
    listItems.setStyle("-fx-background-insets: 0 ; -fx-background-color: transparent;");
    listItems.setCellFactory((ListView<Vec> param) -> new ListCell<Vec>() {
    private final ImageView imageView = new ImageView();
    @Override
    protected void updateItem(Vec item, boolean empty) {
    super.updateItem(item, empty);
                if (empty || item == null || item.getNazev() == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(item.getObrazekFile());
                    imageView.setFitHeight(100);
                    setGraphic(imageView);
                }
                this.setOnMousePressed(event -> {
                    main.zpracujPrikazMain("prodej " +item.getNazev());                
                    hra.getHerniPlan().notifyObservers();
                    update();
                    
                });            
    }
    });
    VBox batohLayout = new VBox();
    Label labelBatoh = new Label("Obsah batohu:");
    batohLayout.getChildren().addAll(labelBatoh, listItems);
    this.getChildren().addAll(batohLayout);
    update();
    }
    /*
    * Metoda aktualizuje obsah batohu
    */
    @Override
    public void update() {  
        predmety.setAll();
        predmety.addAll(hra.getHerniPlan().getBatoh().getObsahBatohu().values());
    }

    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     *
     * @param hra fadsfj ajdsifojdfi
     */
    public void newGame(IHra hra) {
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }

  
}
