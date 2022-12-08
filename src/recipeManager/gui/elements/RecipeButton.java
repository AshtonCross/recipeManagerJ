package recipeManager.gui.elements;

import javafx.scene.control.Button;
import recipeManager.bookData.Recipe;

public class RecipeButton extends Button {
	private Recipe recipe;

	public RecipeButton(Recipe r) {
		super(r.getName());
		this.recipe = r;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void highlight() {

	}

	public void normal() {

	}
}
