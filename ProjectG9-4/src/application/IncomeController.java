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

public class IncomeController {
	@FXML
	public TextField Input;
	@FXML
	public void increase(){
		ProjectModel.incomeIncrease(Integer.valueOf(Input.getText()));
		Input.clear();
	}
	@FXML
	public void decrease(){
		ProjectModel.incomeDecrease(Integer.valueOf(Input.getText()));
		Input.clear();
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
	public void toView(ActionEvent event) throws IOException{
		AnchorPane mainPane = FXMLLoader.load(getClass().getResource("View.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	@FXML
	public void toSpend(ActionEvent event) throws IOException{
		AnchorPane mainPane = FXMLLoader.load(getClass().getResource("Spend.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
}
