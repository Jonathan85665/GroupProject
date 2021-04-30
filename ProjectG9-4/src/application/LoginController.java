package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private Button button;
	@FXML
	private PasswordField password;
	@FXML
	private TextField userName;
	@FXML
	private Label error;

	public void userlog(ActionEvent event) throws IOException{
		checkLogin(event);
	}
	private void checkLogin(ActionEvent event) throws IOException{
		if(userName.getText().equals("abc123") && password.getText().equals("password")) { // username must be "abc123" and password must be "password" for it too move on to next step
			AnchorPane mainPane = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(mainPane);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		}
		else if(userName.getText().isEmpty() && password.getText().isEmpty()) {
			error.setText("Please Enter Data");
		}
	}
}


