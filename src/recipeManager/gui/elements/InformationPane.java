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
	private final double FONT_BASE = 25;
	private Font defaultFont = Font.font(FONT_BASE);
	
	public InformationPane() {
		
	}
	
	public void loadRecipe(Recipe r) {
		// clear out any text in here
		getChildren().clear();
		
		// check if r = null. (no recipe given)
		if (r == null) {
			Text nullmsg = new Text("Select a recipe.");
			nullmsg.setFont(defaultFont);
			getChildren().add(nullmsg);
			return;
		}
		
		// set up all text objects starting with recipe's name
		Text name = new Text(r.getName());
		name.wrappingWidthProperty().bind(this.widthProperty());
		name.setFont(Font.font("Mono", FontWeight.BOLD, FontPosture.ITALIC, 1.8*FONT_BASE));
		
		// time info
		String prep = r.getPrepTime().trim();
		String cook = r.getCookTime().trim();
		
		if (prep == null || prep.equals(""))
			prep = "???";
		
		if (cook == null || cook.equals(""))
			cook = "???";
		
		Text timeInfo = new Text("Prep Time: " + r.getPrepTime() + 
						      " | Cook Time: " + r.getCookTime());
		timeInfo.setFont(defaultFont);
		
		// author
		Text author = new Text("Author: " + r.getAuthor());
		author.setFont(defaultFont);
		
		// tags
		String ftags = "TAGS: ";
		
		for (String tag : r.getTags()) {
			ftags += tag;
			ftags += " "; // separate w./ SPC
		}
		
		Text tags = new Text(ftags);
		tags.setFont(Font.font("Mono", FontWeight.LIGHT, FontPosture.REGULAR, 0.7*FONT_BASE));
		
		// Ingredients
		Text ingredientsLabel = new Text("Ingredients:");
		ingredientsLabel.setFont(Font.font("Mono", FontWeight.BOLD, FontPosture.ITALIC, 1.2*FONT_BASE));
		
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
