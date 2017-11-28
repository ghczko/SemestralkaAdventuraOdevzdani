/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.BatohView;
import GUI.ExitView;

import GUI.Mapa;
import GUI.MenuLista;
import GUI.PersonView;

import GUI.ItemsView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author xzenj02, tomasbalogh
 */
public class Main extends Application {

    private TextArea centralText;
    private IHra ihra = new Hra();
    private Hra hra;
    
    public void setHra(IHra ihra) {
        this.ihra = ihra;
    }
    
    private TextField zadejPrikazTextArea;
    
    private Mapa mapa;
    private MenuLista menuLista;
    private BatohView batohView;
    private PersonView osobyView;
    private ExitView exitView;
    private Stage stage;
    private ItemsView veciView;
  
    
    
    
     /*
    *metoda nastavi scenu aplikace
    */              
    @Override
    public void start(Stage primaryStage) {
        this.setStage(primaryStage);
       
        
        
        BorderPane borderPane = new BorderPane();
        BorderPane pravaLista = new BorderPane();
        BorderPane levaLista = new BorderPane();
        BorderPane prikazyLista = new BorderPane();
        BorderPane PostavaPane = new BorderPane();
        BorderPane prostorPane = new BorderPane();
        BorderPane borderPane2 = new BorderPane();
        
        batohView = new BatohView(ihra, this);
        exitView = new ExitView(ihra, this);
        veciView = new ItemsView(ihra, this);
        mapa = new Mapa(ihra);
        menuLista = new MenuLista(ihra, this);
        osobyView = new PersonView(ihra, this);
             
                
        // Text s prubehem hry
        centralText = new TextArea();
        centralText.setText(ihra.vratUvitani());
        centralText.setEditable(false);
        centralText.setPrefHeight(630);
        centralText.setStyle("-fx-background-insets: 0 ;-fx-background-color: transparent;");
        VBox centralTextLayout = new VBox();
        Label labelCentralText = new Label("Výpis textového rozhraní");
        
        centralTextLayout.getChildren().addAll(labelCentralText, centralText);
        borderPane2.setTop(centralTextLayout);
        borderPane.setCenter(borderPane2);
        borderPane.setStyle("-fx-background-color: white;");
        
        
        //label s textem zadej prikaz
        Label zadejPrikazLabel = new Label("Zadej prikaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        // text area do ktere piseme prikazy
        zadejPrikazTextArea = new TextField("...");
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String vstupniPrikaz = zadejPrikazTextArea.getText();
                String odpovedHry = ihra.zpracujPrikaz(vstupniPrikaz);
                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");
                
                zadejPrikazTextArea.setText("");
                
                if (ihra.konecHry()) {
                    zadejPrikazTextArea.setEditable(false);
                    centralText.appendText(ihra.vratEpilog());
                }
            }
        });
        
//             nastavení scény
        
        
   
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.setBackground(Background.EMPTY);
        dolniLista.getChildren().addAll(zadejPrikazLabel,zadejPrikazTextArea);
        
        pravaLista.setLeft(getBatohView());
        pravaLista.setRight(getExitView());
        
        prostorPane.setTop(getOsobyView());
        prostorPane.setCenter(getVeciView());
      
        levaLista.setTop(mapa);
        levaLista.setCenter(prostorPane);
        
        
        
        borderPane.setLeft(levaLista);
        borderPane.setRight(pravaLista);
        borderPane.setBottom(dolniLista);
        borderPane.setTop(menuLista);
        
        
        Scene scene = new Scene(borderPane, 2000, 750, Color.WHITE);
        primaryStage.setTitle("Adventura");

        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
    }

    public TextArea getCentralText() {
        return centralText;
    }

    public Mapa getMapa() {
        return mapa;
    }
     public IHra getHra() {
        return hra;
    }
     
     
 
 
    /**
     * 
     * @param prikaz
     * Zpracuje String příkazu, vypíše odpověď. Pokus hra skončila, vypne aplikaci.
     */
    public void zpracujPrikazMain(String prikaz){
        String odpovedHry1 = ihra.zpracujPrikaz(prikaz);
        
        centralText.appendText("\n" + prikaz + "\n");
        centralText.appendText("\n" + odpovedHry1 + "\n");
        if(ihra.konecHry()){
            zadejPrikazTextArea.setEditable(false);
           
            centralText.appendText(ihra.vratEpilog());
            System.exit(0);
             
        }
    };    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * @return the batohView
     */
    public BatohView getBatohView() {
        return batohView;
    }

    /**
     * @return the osobyView
     */
    public PersonView getOsobyView() {
        return osobyView;
    }

    /**
     * @return the exitView
     */
    public ExitView getExitView() {
        return exitView;
    }

    /**
     * @return the veciView
     */
    public ItemsView getVeciView() {
        return veciView;
    }

}
