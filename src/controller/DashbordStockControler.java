package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DashbordStockControler {
    @FXML
    private BorderPane borderPane1;
    @FXML
    private AnchorPane mainPain1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label meterLbl;

    @FXML
    private Button productionBtn;

    @FXML
    private Button rawMaterialBtn;

    @FXML
    private TextField meterTxt;

    @FXML
    private TextField levelSet;

   

    @FXML
    private AnchorPane mainPain;



    @FXML
    void productionClick(ActionEvent event) throws IOException {
//        System.out.println("ok");
//
   //   mainPain= FXMLLoader.load(getClass().getResource("/view/DashbordExport.fxml"));
    //  boderPane.setCenter(mainPain);
        Parent load = FXMLLoader.load(getClass().getResource("/view/ProductionStock.fxml"));
        mainPain1.getChildren().clear();
        mainPain1.getChildren().add(load);

    }




    @FXML
    void rawMaterialClick(ActionEvent event) {

    }

    public void metreSet(){
        Random rand=new Random();

        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(1000);
                    int number=rand.nextInt(101);
                    meterTxt.setText(number+"");
                    if(number>80){
                        levelSet.setText("Dannger Level High");

                    }else{
                        levelSet.setText("Dannger Level Low");
                    }
                }catch(Exception e){}



            }
        }).start();

    }

    @FXML
    void initialize() {
        metreSet();

        assert meterLbl != null : "fx:id=\"meterLbl\" was not injected: check your FXML file 'DashbordStock.fxml'.";
        assert productionBtn != null : "fx:id=\"productionBtn\" was not injected: check your FXML file 'DashbordStock.fxml'.";
        assert rawMaterialBtn != null : "fx:id=\"rawMaterialBtn\" was not injected: check your FXML file 'DashbordStock.fxml'.";

    }

}
