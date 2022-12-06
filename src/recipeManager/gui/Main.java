package recipeManager.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import recipeManager.gui.elements.*;
import recipeManager.manegment.CookBookManager;

public class Main extends Application {
	public static void initializeGUI(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// open a cook book for testing
		
		BorderPane cookBookSelection = new BorderPane();
		
		
		
		CookBookManager.openCookBook("test.cb");
		CookBookManager.openCookBook("othertest.cb");
		
		//FileChooser fileChooser = new FileChooser();
		
		CookbookSelect cookBookSelect = new CookbookSelect();

		cookBookSelection.setCenter(cookBookSelect);
		Scene scene = new Scene(cookBookSelect);
		primaryStage.setScene(scene);
		primaryStage.setTitle("test");
		primaryStage.show();
	}
}
