package br.imd;

import java.io.IOException;

import br.imd.dao.PatientDao;
import br.imd.model.Address;
import br.imd.model.Patient;
import br.imd.view.PatientFormController;
import br.imd.view.GenericController;
import br.imd.view.PatientListController;
import br.imd.view.RootController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
	private Stage primaryStage;
	private VBox rootLayout;
	private GenericController controller;

	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Chansey");

        initRootLayout();
	}
	
	public void initRootLayout() {
        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (VBox) loader.load();

            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            RootController controller = loader.getController();
            controller.setMainApp(this);
            controller.setRoot(rootLayout);
            controller.setHbox((HBox) rootLayout.getChildren().get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showView(String viewPath, GenericController otherViewController){
		try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(viewPath));
            AnchorPane view = (AnchorPane) loader.load();

            // Define o person overview dentro do root layout.
            HBox hbox = (HBox) rootLayout.getChildren().get(1);
            hbox.getChildren().remove(1);
            hbox.getChildren().add(1, view);
            
            controller = loader.getController();
            controller.setMainApp(this);
            otherViewController.setOtherViewController(controller);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		launch(args);
	}
}
