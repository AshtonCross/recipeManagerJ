package recipeManager.gui.elements;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import recipeManager.manegment.Manager;

public class FilterPane extends VBox {
	private Stage stage;
	
	public FilterPane() {
		Label label = new Label("Enter in tags, seperated by spaces:");
		TextField tags = new TextField();
		
		String currentTags = "";
		for (String tag : Manager.getFilter()) {
			currentTags += tag + " ";
		}
		tags.setText(currentTags);
		
		Button btFilter = new Button("Apply Filter");
		
		this.getChildren().addAll(label, tags, btFilter);
		
		btFilter.setOnAction(e -> {
			Manager.getFilter().clear();
			
			for (String tag : tags.getText().trim().toUpperCase().split(" ")) {
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
