/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;

/**
 *
 * @author xzenj02, tomasbalogh
 */
public class MenuLista extends MenuBar{
    
    private IHra ihra;
    private Hra hra;
    private Main main;
 /*
     * @param ihra
     * @param main
     * nastaveni konstruktoru
     */
    public MenuLista(IHra ihra, Main main){
        this.ihra = ihra;
        this.main = main;
        init();
    }
    /*
    *   nadefinovani menulisty
    */
    private void init(){
        
        Menu novySoubor = new Menu("Adventura");
        Menu napoveda = new Menu("Help");
        
        MenuItem novaHra = new MenuItem("Nova hra");
        //, new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/ikona.png")))
        
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        MenuItem konecHry = new MenuItem("Konec hry");
        
        novySoubor.getItems().addAll(novaHra, konecHry);
        
        MenuItem oProgramu = new MenuItem("O programu");
        MenuItem napovedaItem = new MenuItem("Napoveda");
        
        napoveda.getItems().addAll(oProgramu, napovedaItem);
        
        this.getMenus().addAll(novySoubor, napoveda);
        /*
        *nastaveni akce po stisknuti tlacitka konechry
        */
        konecHry.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        /*
        *nastaveni akce po stisknuti tlacitka novahra
       
        */
        novaHra.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ihra = new Hra();
                
                main.getMapa().newGame(ihra);
                System.out.print("test");
                main.getBatohView().newGame(ihra);
                main.getExitView().novaHra(ihra);
                main.getOsobyView().newGame(ihra);
                main.getVeciView().newGame(ihra);
                
                main.setHra(ihra);
                main.getCentralText().setText(ihra.vratUvitani());
            }
        });
        /*
        *nastaveni akce po stisknuti tlacitka o programu
       
        */
        oProgramu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);
                
                oProgramuAlert.setTitle("O pragramu");
                oProgramuAlert.setHeaderText("Super adventura XYZ");
                oProgramuAlert.setContentText("Loren ipsum");
                oProgramuAlert.initOwner(main.getStage());
                
                oProgramuAlert.showAndWait();
            }
        });
        /*
        *nastaveni akce po stisknuti tlacitka napoveda
       
        */
        napovedaItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Stage stage = new Stage();
                stage.setTitle("Napovea");
                
                WebView webView = new WebView();
                
                webView.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
                
                stage.setScene(new Scene(webView, 500,500));
                stage.show();
            
            }
        });
        
        
    }
    
}
