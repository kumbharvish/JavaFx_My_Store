package com.shopbilling.fx.controllers;

import javafx.scene.control.Label;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController {

	private Parent parent;
	
    @FXML
    private Button btnClose;

    @FXML
    private Button btnLogin;


    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;
    

    @FXML
    private Label errorMessage;
    
    @FXML
    void doLogin(ActionEvent event) {
    	if(txtUserName.getText().equals("admin")&& txtPassword.getText().equals("password")) {
    		try {
    		errorMessage.setText("");
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML/HomeScreen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("MY STORE");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.getIcons().add(new Image(LoginController.class.getResourceAsStream("/images/shop32X32.png")));
            stage.show();
            Stage st = (Stage)btnLogin.getScene().getWindow();
            st.close();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else {
    		errorMessage.setText("Incorrect Username/Password");
    	}
    }

    @FXML
    void doClose(ActionEvent event) {
    	System.exit(0);
    }
    
    @FXML
    public void initialize() {
        btnLogin.setDefaultButton(true);
    }

	public void show(Parent parent) {
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/shop32X32.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/shop48X48.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/shop64X64.png")));
        stage.show();

        /*Stage mainStage = (Stage)main.getScene().getWindow();
        mainStage.close();*/
    }


}
