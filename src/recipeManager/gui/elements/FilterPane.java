package recipeManager.gui.elements;

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
		for (String filter : Manager.getTagSet())
			tagString += filter + " ";
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
