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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author xzenj02
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
    private ExitView exitView;
    private Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        this.setStage(primaryStage);
       
        
        
        BorderPane borderPane = new BorderPane();
        BorderPane bocniLista = new BorderPane();
        
        batohView = new BatohView(ihra);
        exitView = new ExitView(ihra);
        mapa = new Mapa(ihra);
        menuLista = new MenuLista(ihra, this);
        
        // Text s prubehem hry
        centralText = new TextArea();
        centralText.setText(ihra.vratUvitani());
        centralText.setEditable(false);
        borderPane.setCenter(centralText);
        borderPane.setBackground(Background.EMPTY);
        
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
        
        //dolni lista s elementy
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.setBackground(Background.EMPTY);
        dolniLista.getChildren().addAll(zadejPrikazLabel,zadejPrikazTextArea);
        
        bocniLista.setLeft(batohView);
        bocniLista.setRight(exitView);
        
        
        
        borderPane.setLeft(mapa);
        borderPane.setRight(bocniLista);
        borderPane.setBottom(dolniLista);
        borderPane.setTop(menuLista);
        
        
        Scene scene = new Scene(borderPane, 2000, 1000, Color.DARKGREY);
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

}
