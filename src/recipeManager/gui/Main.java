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
		//CookBookManager.openCookBook("test.cb");
		
		CookBookManagerPane cookBooks = new CookBookManagerPane();
		cookBooks.setMinHeight(500);
		cookBooks.setMinWidth(600);
		
		Scene scene = new Scene(cookBooks);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Recipe Manager");
		primaryStage.setMinHeight(500);
		primaryStage.setMinWidth(750);
		primaryStage.show();
	}
}
