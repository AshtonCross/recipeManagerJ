/*
 * InformationPane.java
 * 
 * This pane contains all that goes into formatting the data from a Recipe class
 * into something nice to read. 
 * 
 * TODO: 
 * Create a FILO stack containing the last 10 or so informationPanes that were 
 * generated, and then use that to avoid reloading the same panes over and over
 * again to allow easy switching.
 * 
 * TODO:
 * Allow for the user to add a list of things that they're allergic to, and then
 * try your best to highlight those allergies in the ingredients list before they 
 * get added.
 * 
 * TODO:
 * Add increase / decrease font size buttons, where it takes base font and then
 * increases. Each font should have their size be proportional to this base font
 * size; Most would be a 1x, but some at 2x.
 * Maybe make it an observable property and then multiply it or smth?
 */

package recipeManager.gui.elements;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
		
		// set up all text objects starting with recipe's name
		Text name = new Text(r.getName());
		name.setFont(Font.font("Mono", FontWeight.BOLD, FontPosture.ITALIC, 26));
		
		// time info
		String prep = r.getPrepTime().trim();
		String cook = r.getCookTime().trim();
		
		if (prep == null || prep.equals(""))
			prep = "???";
		
		if (cook == null || cook.equals(""))
			cook = "???";
		
		Text timeInfo = new Text("Prep Time: " + r.getPrepTime() + 
						      " | Cook Time: " + r.getCookTime());
		
		// author
		Text author = new Text("Author: " + r.getAuthor());
		
		// tags
		String ftags = "TAGS: ";
		
		for (String tag : r.getTags()) {
			ftags += tag;
			ftags += " "; // separate w./ SPC
		}
		
		Text tags = new Text(ftags);
		tags.setFont(Font.font("Mono", FontWeight.LIGHT, FontPosture.REGULAR, 9));
		
		// Ingredients
		Text ingredientsLabel = new Text("Ingredients:");
		ingredientsLabel.setFont(Font.font("Mono", FontWeight.BOLD, FontPosture.ITALIC, 16));
		
		VBox ingredientsList = new VBox();
		
		for (String ingredient : r.getIngredients()) {
			Text i = new Text("+ " + ingredient);
			i.setFont(Font.font("Mono", FontWeight.NORMAL, FontPosture.ITALIC, 14));
			
			ingredientsList.getChildren().add(i);
		}
		
		// format ingredients within a vbox
		VBox ingredients = new VBox();
		
		ingredients.getChildren().addAll(ingredientsLabel, ingredientsList);
		ingredients.setPadding(new Insets(7, 7, 7, 7));
		
		// add all text objects to pane
		getChildren().addAll(name, timeInfo, author, tags,
				ingredients);
		
		
	}
}
