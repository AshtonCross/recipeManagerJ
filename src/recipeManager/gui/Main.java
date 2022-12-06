package recipeManager.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import recipeManager.gui.elements.CookBookManagerPane;
import recipeManager.manegment.CookBookManager;

public class Main extends Application {
	public static void initializeGUI(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		final int MIN_FRAME_HEIGHT = 200;
		final int MIN_FRAME_WIDTH = 750;

		// open a cook book for testing
		CookBookManager.openCookBook("test.cb");
		CookBookManager.openCookBook("this.cb");
		CookBookManager.openCookBook("lole.cb");
		CookBookManager.openCookBook("othertest.cb");

		CookBookManagerPane cookBooks = new CookBookManagerPane();
		cookBooks.setPrefHeight(MIN_FRAME_HEIGHT);
		cookBooks.setPrefWidth(MIN_FRAME_WIDTH);
		//cookBooks.heightProperty().bind(primaryStage.heightProperty());


		Scene scene = new Scene(cookBooks);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Recipe Manager");
		primaryStage.setMinHeight(MIN_FRAME_HEIGHT);
		primaryStage.setMinWidth(MIN_FRAME_WIDTH);
		primaryStage.show();
	}
}
