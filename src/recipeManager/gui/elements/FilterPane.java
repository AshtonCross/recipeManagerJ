/*
 * FilterPane.java
 * 
 * The filter pane is the pane used for the filter window. 
 * 
 * TODO: Make it so that way tags are counted as well, so that 
 * the tags may be displayed from most to least popular, and that
 * they also are sat next to a number showing how many recipes have 
 * that tag. example:
 * 
 * German (23), Armenian (5), Italian (4), French(2)
 */

package recipeManager.gui.elements;

import java.util.Iterator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import recipeManager.manegment.Manager;

public class FilterPane extends VBox {
	private Stage stage;

	public FilterPane() {
		Label label = new Label("Enter in tags, seperated by spaces:");
		TextField tags = new TextField();
		this.setPadding(new Insets(10, 10, 10, 10));

		String currentTags = "";
		for (String tag : Manager.getFilter()) {
			currentTags += tag + " ";
		}
		tags.setText(currentTags);

		Button btFilter = new Button("Apply Filter");

		// add tag list view
		TextArea tagSetView = new TextArea();
		tagSetView.setMaxWidth(300);
		tagSetView.setEditable(false);

		String tagString = "";
		
		Iterator<String> iterator = Manager.getTagSet().descendingIterator();
		for (int i = 0; i < Manager.getTagSet().size(); ++i) {
			tagString += iterator.next();
			
			if (i != (Manager.getTagSet().size() - 1)) {
				tagString += ",  ";
			}
		}
		
		tagSetView.setText(tagString);
		tagSetView.setWrapText(true);

		this.setAlignment(Pos.TOP_CENTER);
		this.getChildren().addAll(label, tags, btFilter, tagSetView);

		btFilter.setOnAction(e -> {
			Manager.getFilter().clear();

			for (String tag : tags.getText().trim().toUpperCase().split(" ")) {
				if (tag.length() < 2)
					continue;

				Manager.getFilter().add(tag);
			}

			Manager.getButtonsControl().updateButtons();
			this.kill();
		});

	}

	public void setStage(Stage s) {
		stage = s;
	}

	public void kill() {
		stage.close();
	}
}
