package application;

import java.io.IOException;

import application.model.ProjectModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SpendController {
	@FXML
	public TextField budget;
	@FXML
	public TextField expense;

	@FXML
	public void spend(){
		String temp1 = budget.getText();
		int temp2 = Integer.valueOf(expense.getText());
		ProjectModel.spend(temp1, temp2);
		budget.clear();
		expense.clear();
	}
	@FXML
	public void reset(){
		ProjectModel.reset();
	}

	@FXML
	public void toMenu(ActionEvent event) throws IOException{
		AnchorPane mainPane = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	@FXML
	public void toIncome(ActionEvent event) throws IOException{
		AnchorPane mainPane = FXMLLoader.load(getClass().getResource("Income.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	@FXML
	public void toView(ActionEvent event) throws IOException{
		AnchorPane mainPane = FXMLLoader.load(getClass().getResource("View.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
}
