package edu.icet.controller;

import edu.icet.model.Expense;
import edu.icet.repository.ExpenseRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController {

    @FXML
    private TableColumn<?, ?> colAmo;

    @FXML
    private TableColumn<?, ?> colCate;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDes;

    @FXML
    private TableView<Expense> tblexpense;

    @FXML
    private TextField txtAmo;

    @FXML
    private TextField txtCate;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtDes;

    private ExpenseRepository expenseRepository = new ExpenseRepository();


    public void initialize(URL url, ResourceBundle resourceBundle) {

        colDes.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCate.setCellValueFactory(new PropertyValueFactory<>("category"));
        colAmo.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadTableData();
    }
    @FXML
    void btnAdd(ActionEvent event) {
        try {
            String description = txtDes.getText();
            String category = txtCate.getText();
            double amount = Double.parseDouble(txtAmo.getText());
            var date = txtDate.getValue();

            if (description.isEmpty() || date == null) {
                showAlert("Error", "Please fill all fields!");
                return;
            }

            Expense expense = new Expense(0, description, category, amount, date);
            expenseRepository.save(expense);

            loadTableData();
            clearFields();

            showAlert("Success", "Expense Added Successfully!");

        } catch (NumberFormatException e) {
            showAlert("Error", "Amount must be a number!");
        }
    }

    private void loadTableData() {
        ObservableList<Expense> list = FXCollections.observableArrayList(expenseRepository.getAllExpenses());
        tblexpense.setItems(list);
    }

    private void clearFields() {
        txtDate.setValue(null);
        txtDes.clear();
        txtCate.clear();
        txtAmo.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

}
