package application;

import java.io.IOException;

import application.model.ProjectModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewController {

	@FXML
	public ListView<String> listview;
	@FXML
	public void load(){
		ObservableList<String> print = FXCollections.observableArrayList(ProjectModel.load());
		listview.getItems().setAll(print);
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
	public void toSpend(ActionEvent event) throws IOException{
		AnchorPane mainPane = FXMLLoader.load(getClass().getResource("Spend.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
}
