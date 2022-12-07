package recipeManager.gui.elements;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import recipeManager.bookData.Recipe;

public class InformationPane extends VBox {
	public InformationPane() {
		
	}
	
	public void loadRecipe(Recipe r) {
		// clear out any text in here
		getChildren().clear();
		
		// check if r = null. (no recipe given)
		if (r == null) {
			getChildren().add(new Text("Select a recipe."));
			return;
		}
		
		// build nice looking title
		getChildren().add(new Text(r.getName()));
		
		// basic information
		
		// add steps
	}
}
