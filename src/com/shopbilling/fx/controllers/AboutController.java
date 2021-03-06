/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopbilling.fx.controllers;

import com.shopbilling.utils.TabContent;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vishal
 */
public class AboutController implements TabContent {

    private Stage mainWindow;
    
    private TabPane tabPane;
    
    private double version = 1.0;
    
    private static final String DEVELOPED_BY = "Vishal Kumbhar / Adhir Shishupal";
    
    private static final String MOBILE = "+91 8149880299 / +91 9579616107";
    
    private static final String EMAIL_ID = "Kumbharvish@gmail.com";
    
    private static final String MY_STORE ="My Store Billing";
    
    private static final String COPYRIGHT ="Copyright � 2017";
    
    @FXML
    private Text txtTitle;

    @FXML
    private Text txtVersion;

    @FXML
    private Label lblDevelopedBy;
    
    @FXML
    private Text txtCopyRight;

    @FXML
    private Label lblEmailId;

    @FXML
    private Label lblMobileNo;

    @FXML
    private Button btnClose;

    @Override
    public boolean shouldClose() {
        return true;
    }

    @Override
    public void putFocusOnNode() {
        btnClose.requestFocus();
    }

    @Override
    public boolean loadData() {
    	populateControls();
        return true;
    }

    @Override
    public void setMainWindow(Stage stage) {
        mainWindow = stage;
    }

    @Override
    public void setTabPane(TabPane pane) {
        tabPane = pane;
    }

    private void populateControls() {
         txtTitle.setText(MY_STORE);
         txtVersion.setText("Version " + version);
         txtCopyRight.setText(COPYRIGHT);
         lblDevelopedBy.setText(DEVELOPED_BY);
         lblEmailId.setText(EMAIL_ID);
         lblMobileNo.setText(MOBILE);
    }

    @FXML
    private void onCloseTabAction(ActionEvent event) {
        final Tab currentTab = tabPane.getSelectionModel().getSelectedItem();
        tabPane.getTabs().remove(currentTab);
    }

	@Override
	public boolean saveData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void invalidated(Observable observable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeTab() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateInput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
}
