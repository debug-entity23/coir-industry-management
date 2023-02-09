package controller;


import model.Buyer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class DashbordExportController {
    public int count;

    @FXML
    private TextField idTxt;
    @FXML
    private TextField addressTxt;
    @FXML
    private TextField contactNumberTxt;
    @FXML
    private TextField salaryTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private Button saveBtn;
    @FXML
    private Label employeeCountLbl;
    @FXML
    private TableView<Buyer> table;
    @FXML
    private TableColumn<Buyer, String> buyerId;
    @FXML
    private TableColumn<Buyer, String> buyerName;
    @FXML
    private TableColumn<Buyer, String> buyerEmail;
    @FXML
    private TableColumn<Buyer, String> buyerAddress;
    @FXML
    private TableColumn<Buyer, String> buyerContact;
    @FXML
    private TableColumn<Buyer, String> buyerUpdate;
    @FXML
    private TableColumn< ?, ?> buyerDelete;
    @FXML
    private Button showBtn;


    public void updateEmployeeCount(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test", "root", "952452");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(buyer_id) FROM buyer");

            if (resultSet.next()) {
                count = resultSet.getInt(1);
                System.out.println(count);
                employeeCountLbl.setText(count+" ");
            }

            connection.close();
        } catch (Exception e) {
            employeeCountLbl.setText("Error: " + e.getMessage());
        }

    }



    @FXML
    void saveClick(ActionEvent event) {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test", "root", "952452");
            String sql = "INSERT INTO buyer ( buyer_id, buyer_name, address,email,contact_number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, idTxt.getText());
            stmt.setString(2, nameTxt.getText());
            stmt.setString(3, addressTxt.getText());
            stmt.setString(4, salaryTxt.getText());
            stmt.setString(5, contactNumberTxt.getText());

            int rows = stmt.executeUpdate();
            System.out.println(rows + " row(s) inserted.");
            con.close();
            updateEmployeeCount();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //insert data into table
    public void populateTableView(){
        ObservableList<Buyer> list = FXCollections.observableArrayList();
        String query="SELECT * FROM buyer";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test", "root", "952452");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Buyer buyer = new Buyer();
                buyer.setBuyer_id(resultSet.getString(2));
                buyer.setBuyer_name(resultSet.getString(3));
                buyer.setEmail(resultSet.getString(5));
                buyer.setAddress(resultSet.getString(4));
                buyer.setContact_number(resultSet.getString(6));

                list.add(buyer);

                buyerId.setCellValueFactory(new PropertyValueFactory<>("buyer_id"));
                buyerName.setCellValueFactory(new PropertyValueFactory<>("buyer_name"));
                buyerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                buyerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                buyerContact.setCellValueFactory(new PropertyValueFactory<>("contact_number"));


                table.setItems(list);
            }



            connection.close();
        } catch (Exception e) {
            System.out.println("not");
        }
    }

    //end

    @FXML
    void showClick(ActionEvent event) {
        System.out.println("bbbbbbbbbb");
        populateTableView();

    }

    @FXML
    void initialize() {

        updateEmployeeCount();
        populateTableView();

        assert idTxt != null : "fx:id=\"addressTxt\" was not injected: check your FXML file 'DashbordExport.fxml'.";
        assert contactNumberTxt != null : "fx:id=\"contactNumberTxt\" was not injected: check your FXML file 'DashbordExport.fxml'.";
        assert employeeCountLbl != null : "fx:id=\"employeeCountLbl\" was not injected: check your FXML file 'DashbordExport.fxml'.";
        assert addressTxt != null : "fx:id=\"idTxt\" was not injected: check your FXML file 'DashbordExport.fxml'.";
        assert nameTxt != null : "fx:id=\"nameTxt\" was not injected: check your FXML file 'DashbordExport.fxml'.";
        assert salaryTxt != null : "fx:id=\"salaryTxt\" was not injected: check your FXML file 'DashbordExport.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'DashbordExport.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'DashbordExport.fxml'.";

    }



}
