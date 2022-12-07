/*
 * CookBookManagerPane.java
 * 
 * This file contains the declaration of the CookBookManagerPane.
 * This pane is where the CookBookControls are added alongside 
 * how the main info panes are managed.
 * 
 * The information is handled from within the CookBookControls, but
 * this file handles the majority of the actual layout of the pane.
 */

package recipeManager.gui.elements;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import recipeManager.bookData.Recipe;

public class CookBookManagerPane extends BorderPane {
	CookBookControl cookBookControl = new CookBookControl();
	
	public CookBookManagerPane() {
		// set up cookbooks
		refreshCookBookPanel();
		cookBookControl.setAlignment(Pos.TOP_CENTER);
		cookBookControl.setMinWidth(cookBookControl.BUTTON_WIDTH + 7);
		cookBookControl.setMaxWidth(cookBookControl.BUTTON_WIDTH + 20);

		cookBookControl.setPadding(new Insets(10, 10, 10, 10));

		// set up reading space
		Pane readingPane = new Pane();

		Rectangle backdrop = new Rectangle();
		backdrop.heightProperty().bind(readingPane.heightProperty());
		backdrop.widthProperty().bind(this.widthProperty().subtract(cookBookControl.BUTTON_WIDTH));
		backdrop.setFill(Color.LIGHTGREY);

		readingPane.getChildren().add(backdrop);

		// set up information pane (will manage the text displayed)
		InformationPane info = cookBookControl.getInformationPane();

		info.loadRecipe(cookBookControl.getSelectedRecipe());
		
		info.setAlignment(Pos.TOP_LEFT);
		info.setPadding(new Insets(14, 14, 14, 14));

		readingPane.getChildren().add(info);

		this.setCenter(readingPane);

		// scroll bar & scroll function

		ScrollBar scrollBar = new ScrollBar();
		scrollBar.setOrientation(Orientation.VERTICAL);

		// TODO: figure out how to make this scale according to
		// the length of content featured (important)
		scrollBar.valueProperty().addListener(ov -> {
			info.setLayoutY(-(scrollBar.getValue() * info.getHeight() / scrollBar.getMax()));
		});

		this.setRight(scrollBar);

	}

	private void refreshCookBookPanel() {
		if (cookBookControl.countCookBooks() == 0) {
			Text notFound = new Text("Press \"New\" to make a cook book!");
			notFound.maxWidth(cookBookControl.BUTTON_WIDTH);
			this.setLeft(notFound);
		} else {
			this.setLeft(cookBookControl);
		}
	}

}
