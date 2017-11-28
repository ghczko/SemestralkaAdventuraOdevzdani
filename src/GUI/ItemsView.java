/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
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
 *
 * @author tomasbalogh
 */
public class ItemsView extends AnchorPane implements Observer  {
     private IHra hra;
    private ObservableList<Vec> veci;
    private Main main;
     
    /*
     * @param ihra
     * @param main
     * nastaveni konstruktoru
     */
     public ItemsView(IHra hra, Main main){
       this.hra = hra;
        this.main = main;
        hra.getHerniPlan().registerObserver(this);  
        hra.getHerniPlan().getAktualniProstor().registerObserver(this);
        init();
     }
        /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     *
     * @param hra
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     */
       public void newGame(IHra hra) {
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }
       /**
     * Metoda aktualizuje vse co je v panelu tim ze to vymaze a pote prida. Dale se stara o click to action a o use case vezmi
     * 
     */
     private void init(){
      veci = FXCollections.observableArrayList();
        ListView<Vec> listPredmetu = new ListView<>(veci);
        listPredmetu.setOrientation(Orientation.HORIZONTAL);
        listPredmetu.setStyle("-fx-background-insets: 0 ; -fx-background-color: transparent;");
        listPredmetu.setPrefSize(600, 110);
        listPredmetu.setCellFactory(param -> new ListCell<Vec>() {
            private ImageView imageView = new ImageView();
            @Override
            protected void updateItem(Vec item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNazev() == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(item.getObrazekFile());
                    imageView.setFitHeight(100);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
                this.setOnMousePressed(event -> {
                    main.zpracujPrikazMain("vezmi "+item.getNazev());
                    update();
                });
            }
        });
        VBox veciLayout = new VBox();
        Label labelVeci = new Label("Obsah prostoru:");
        veciLayout.getChildren().addAll(labelVeci, listPredmetu);
        this.getChildren().addAll(veciLayout);
        update();
         
         
          
        }
     
  
     /*
    * Metoda aktualizuje veci
    */
   @Override
    public void update() {  
       veci.setAll();
        veci.addAll(hra.getHerniPlan().getAktualniProstor().getVeci().values());
    }
  
    
}
