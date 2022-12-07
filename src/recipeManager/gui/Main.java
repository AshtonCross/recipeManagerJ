package recipeManager.gui;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import recipeManager.bookData.Recipe;
import recipeManager.gui.elements.CookBookManagerPane;
import recipeManager.manegment.Manager;

public class Main extends Application {
	public static void initializeGUI(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		final int MIN_FRAME_HEIGHT = 200;
		final int MIN_FRAME_WIDTH = 750;

		CookBookManagerPane cookBooks = new CookBookManagerPane();
		cookBooks.setPrefHeight(MIN_FRAME_HEIGHT);
		cookBooks.setPrefWidth(MIN_FRAME_WIDTH);
		// cookBooks.heightProperty().bind(primaryStage.heightProperty());

		Scene scene = new Scene(cookBooks);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Recipe Manager");
		primaryStage.setMinHeight(MIN_FRAME_HEIGHT);
		primaryStage.setMinWidth(MIN_FRAME_WIDTH);
		primaryStage.show();
	}
}
