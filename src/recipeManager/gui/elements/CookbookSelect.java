package recipeManager.gui.elements;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import recipeManager.bookData.CookBook;
import recipeManager.manegment.CookBookManager;

public class CookbookSelect extends VBox {
	public final int BUTTON_WIDTH = 250;
	public final int BUTTON_HEIGHT = 50;
	private VBox buttonPane = new VBox();
	private HBox controlPane = new HBox();
	private Button btLoad = new Button("Load");
	private Button btView = new Button("Open");
	
	public CookbookSelect() {
		// set up the buttons by getting currently open cookbooks.
		this.updateButtons();
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
		this.getChildren().add(buttonPane);
		
		controlPane.getChildren().addAll(btLoad, btView);
		controlPane.setAlignment(Pos.BOTTOM_CENTER);
		this.getChildren().add(controlPane);
		
	}
	
	public void updateButtons() {
		buttonPane.getChildren().clear();
		
		if (CookBookManager.getOpenCookBooks().size() == 0) {
			Text noneFound = new Text("Please load a cookbook!");
			buttonPane.getChildren().add(noneFound);
			return;
		}
		
	
		for (CookBook cb : CookBookManager.getOpenCookBooks()) {
			Button newButton = new Button(cb.getName());
			newButton.setMinWidth(BUTTON_WIDTH);
			newButton.setMinHeight(BUTTON_HEIGHT);
			buttonPane.getChildren().add(newButton);
		}
		
	}
	
	public HBox getControlPane() {
		return controlPane;
	}
	
	public int countCookBooks() {
		return buttonPane.getChildren().size();
	}
}
