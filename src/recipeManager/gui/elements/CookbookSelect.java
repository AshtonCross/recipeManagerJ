package recipeManager.gui.elements;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import recipeManager.bookData.CookBook;
import recipeManager.manegment.CookBookManager;

public class CookbookSelect extends Pane {
	private VBox buttonPane = new VBox();
	
	public CookbookSelect() {
		// get cookbooks from CookBookManager.getCookBooks() 
		// and turn them into buttons in a grid pane.
		
		// set up the buttons by getting currently open cookbooks.
		this.updateButtons();
		this.getChildren().add(buttonPane);
		
		
	}
	
	public void updateButtons() {
		buttonPane.getChildren().clear();
	
		for (CookBook cb : CookBookManager.getOpenCookBooks()) {
			Button newButton = new Button(cb.getName());
			newButton.setMinWidth(250);
			newButton.setMinHeight(50);
			buttonPane.getChildren().add(newButton);
		}
		
	}
}
