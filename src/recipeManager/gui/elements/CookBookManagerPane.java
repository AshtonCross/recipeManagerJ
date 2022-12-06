package recipeManager.gui.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CookBookManagerPane extends BorderPane {
	CookbookSelect cookBooks = new CookbookSelect();
	
	public CookBookManagerPane() {
		// set up cookbooks
		refreshCookBookPanel();
		cookBooks.setAlignment(Pos.BOTTOM_CENTER);
		cookBooks.setMinWidth(cookBooks.BUTTON_WIDTH);
		cookBooks.setMaxWidth(cookBooks.BUTTON_WIDTH);
		
		// set up reading space
		Pane readingPane = new Pane();
		
		Rectangle test = new Rectangle();
		test.heightProperty().bind(readingPane.heightProperty());
		test.widthProperty().bind(readingPane.widthProperty());
		test.setFill(Color.LIGHTGREY);
		
		readingPane.getChildren().add(test);
		this.setCenter(readingPane);
		
		// set up information pane (will manage the text displayed)
		
		// give buttons function
	}
	
	private void refreshCookBookPanel() {
		if (cookBooks.countCookBooks() == 0) {
			Text notFound = new Text("Press \"import\" to begin working with cook books!");
			notFound.maxWidth(cookBooks.BUTTON_WIDTH);
			this.setLeft(notFound);
		} else {
			this.setLeft(cookBooks);
		}
	}
	
}
