package recipeManager.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import recipeManager.gui.elements.CookBookManagerPane;
import recipeManager.manegment.Manager;

public class Main extends Application {
	public static void initializeGUI(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// create & configure initial pane
		final int MIN_FRAME_HEIGHT = 200;
		final int MIN_FRAME_WIDTH = 750;

		CookBookManagerPane cookBooks = new CookBookManagerPane();
		cookBooks.setPrefHeight(MIN_FRAME_HEIGHT + 500);
		cookBooks.setPrefWidth(MIN_FRAME_WIDTH + 300);

		//
		Scene viewer = new Scene(cookBooks);
		primaryStage.setScene(viewer);
		primaryStage.setTitle("Recipe Manager");
		primaryStage.setMinHeight(MIN_FRAME_HEIGHT);
		primaryStage.setMinWidth(MIN_FRAME_WIDTH);
		primaryStage.show();

		// configure manager
		Manager.setCookBookViewer(viewer);
		Manager.setPrimaryStage(primaryStage);

	}
}
