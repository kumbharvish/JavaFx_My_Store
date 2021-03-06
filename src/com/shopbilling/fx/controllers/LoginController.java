package com.shopbilling.fx.controllers;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.shopbilling.dto.MyStoreDetails;
import com.shopbilling.dto.UserDetails;
import com.shopbilling.fx.main.Global;
import com.shopbilling.fx.main.MyStoreFxSplash;
import com.shopbilling.fx.model.WindowState;
import com.shopbilling.services.AppLicenseServices;
import com.shopbilling.services.MyStoreServices;
import com.shopbilling.services.UserServices;
import com.shopbilling.utils.PDFUtils;
import com.shopbilling.utils.TabContent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginController {

	private final static Logger logger = Logger.getLogger(MyStoreFxSplash.class);

	private final static String APPLICATION_HOME_TITTLE = "My Store";
	
	private final static String APPLICATION_LOGIN_TITTLE = "Login";
	 
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
    private Label lblShopName;
    
    @FXML
    private Label lblLicenseValideUpto;
    
    @FXML
    void doLogin(ActionEvent event) {
			String user = txtUserName.getText();
			String pwd = txtPassword.getText();
			if ("".equals(user)|| "".equals(pwd)){
				errorMessage.setText("Please Enter UserName / Password !");
			}else{
				UserDetails userDetails = UserServices.validateUser(user, pwd);
				if(userDetails!=null){
					Stage st = (Stage)btnLogin.getScene().getWindow();
		            st.close();
		            // Open Home Window
		            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/shopbilling/fx/views/Home.fxml"));
				        Parent root = null;
				        try {
				            root = fxmlLoader.<BorderPane>load();
				        } catch (IOException e) {
				        	e.printStackTrace();
				            logger.error("Error in loading the Home page view file : ", e);
				        }
				        final Scene scene = new Scene(root, 850, 700);
				        addKeyFilter(scene);
				        Stage stage = new Stage();
				        stage.setScene(scene);
				        HomeController homeController = fxmlLoader.getController();
				        homeController.MainWindow = stage;
				        homeController.userDetails = userDetails;
				        stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/images/shop32X32.png")));
				        stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/images/shop48X48.png")));
				        stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/images/shop64X64.png")));
				        
				        final WindowState s = Global.getDefaultWindowState();
				        stage.setX(s.getXPos());
				        stage.setY(s.getYPos());
				        stage.setWidth(s.getWidth());
				        stage.setHeight(s.getHeight());
				        
				       //stage.getProperties().put("hostServices", getHostServices());
				        stage.setTitle(APPLICATION_HOME_TITTLE);
				        stage.setMaximized(true);
				        stage.show();
				        stage.setOnCloseRequest((WindowEvent event2) -> {
				            if (!homeController.closeAllTabs()) {
				                event2.consume();
				                return;
				            }});

				}else{
					errorMessage.setText("Incorrect Username / Password");
				}
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
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        stage.setX(bounds.getMinX() + bounds.getWidth() / 2 - 300 / 2);
        stage.setY(bounds.getMinY() + bounds.getHeight() / 2 - 447 / 2);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.setTitle(APPLICATION_LOGIN_TITTLE);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/images/shop32X32.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/images/shop48X48.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/images/shop64X64.png")));
        MyStoreDetails mystore = MyStoreServices.getMyStoreDetails();
        if(mystore!=null) {
        	lblShopName.setText(mystore.getStoreName());
        }else {
        	lblShopName.setText("Store Name Here");
        }
        
        try {
			lblLicenseValideUpto.setText(PDFUtils.dec(PDFUtils.getAppDataValues("APP_SECURE_KEY").get(0)));
		} catch (Exception e) {
			logger.error("LogginController.show-->"+e);
			e.printStackTrace();
		}
        stage.show();
    	//Update Last Run
  		AppLicenseServices.updateLastRun();
    }

	 private void addKeyFilter(final Scene scene) {

	        final KeyCombination f4Key = new KeyCodeCombination(KeyCode.F4,
	                KeyCombination.SHORTCUT_DOWN);

	        final KeyCombination leftKey = new KeyCodeCombination(KeyCode.LEFT,
	                KeyCombination.SHORTCUT_DOWN);
	        final KeyCombination leftNumPadKey = new KeyCodeCombination(KeyCode.KP_LEFT,
	                KeyCombination.SHORTCUT_DOWN);

	        final KeyCombination rightKey = new KeyCodeCombination(KeyCode.RIGHT,
	                KeyCombination.SHORTCUT_DOWN);
	        final KeyCombination rightNumPadKey = new KeyCodeCombination(KeyCode.KP_RIGHT,
	                KeyCombination.SHORTCUT_DOWN);

	        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {

	            //if shortcut(a.k.a. ctrl) + F4 key combination was pressed
	            if (f4Key.match(event)) {
	                final TabPane tabPane = (TabPane) scene.lookup("#tabPane");

	                if (!tabPane.getSelectionModel().isEmpty()) {
	                    final Tab tab = tabPane.getSelectionModel().getSelectedItem();
	                    TabContent controller = (TabContent) tab.getProperties().get("controller");
	                    if (controller.shouldClose()) {
	                        tabPane.getTabs().remove(tab);
	                    }
	                }
	                event.consume();
	            } else if (leftKey.match(event) || leftNumPadKey.match(event)) {
	                /*    control+ left arrow key is pressed
	                 shift to the previous tab (The movement is circular) */
	                final TabPane tabPane = (TabPane) scene.lookup("#tabPane");
	                if (tabPane.getTabs().size() > 1 && !tabPane.getSelectionModel().isEmpty()) {
	                    if (tabPane.getSelectionModel().getSelectedIndex() == 0) {
	                        tabPane.getSelectionModel().selectLast();
	                    } else {
	                        tabPane.getSelectionModel().selectPrevious();
	                    }
	                }
	                event.consume();
	            } else if (rightKey.match(event) || rightNumPadKey.match(event)) {
	                /*    control+ right arrow key is pressed
	                 shift to the next tab (The movement is circular) */
	                final TabPane tabPane = (TabPane) scene.lookup("#tabPane");
	                if (tabPane.getTabs().size() > 1 && !tabPane.getSelectionModel().isEmpty()) {
	                    if (tabPane.getSelectionModel().getSelectedIndex() == 
	                            tabPane.getTabs().size() - 1) { //last tab
	                        tabPane.getSelectionModel().selectFirst();
	                    } else {
	                        tabPane.getSelectionModel().selectNext();
	                    }
	                }
	                event.consume();
	            }
	        });
	        
	        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
	           if (event.getTarget()instanceof Button && event.getCode() == KeyCode.ENTER) {
	               Button button =  (Button) event.getTarget();
	                if (!button.isDisabled()) {
	                    button.fire();
	                }
	                event.consume();
	           }
	        });
	        
	         scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
	           if (event.getTarget()instanceof CheckBox && event.getCode() == KeyCode.ENTER) {
	               CheckBox checkBox =  (CheckBox) event.getTarget();
	                if (!checkBox.isDisabled()) {
	                    checkBox.setSelected(!checkBox.isSelected());
	                }
	                event.consume();
	           }
	        });
	    }
}
