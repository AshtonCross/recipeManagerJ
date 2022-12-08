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

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import recipeManager.bookData.Recipe;

public class InformationPane extends VBox {
	private final double FONT_BASE = 16;
	private Font defaultFont = Font.font(FONT_BASE);
	public static DoubleBinding widthBinding = null;

	public InformationPane() {

	}

	public void setWidthBinding(DoubleProperty b) {
		widthBinding = b.multiply(0.9);
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
		name.setFont(Font.font("Mono", FontWeight.BOLD, FontPosture.ITALIC, 2.8 * FONT_BASE));
		
		Text description = new Text(r.getDescription());
		description.setFont(Font.font("Mono", FontWeight.NORMAL, FontPosture.ITALIC, 1.8 * FONT_BASE));

		// time info
		String prep = r.getPrepTime().trim();
		String cook = r.getCookTime().trim();

		if (prep == null || prep.equals(""))
			prep = "???";

		if (cook == null || cook.equals(""))
			cook = "???";

		Text timeInfo = new Text("Prep Time: " + r.getPrepTime() + " | Cook Time: " + r.getCookTime());
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
		tags.setFont(Font.font("Mono", FontWeight.LIGHT, FontPosture.REGULAR, 0.7 * FONT_BASE));

		// Ingredients
		Text ingredientsLabel = new Text("Ingredients:");
		ingredientsLabel.setFont(Font.font("Mono", FontWeight.BOLD, FontPosture.ITALIC, 1.2 * FONT_BASE));
		ingredientsLabel.wrappingWidthProperty().bind(widthBinding);

		VBox ingredientsList = new VBox();

		for (String ingredient : r.getIngredients()) {
			Text i = new Text("+ " + ingredient);
			i.setFont(Font.font("Mono", FontWeight.NORMAL, FontPosture.ITALIC, FONT_BASE));
			i.wrappingWidthProperty().bind(widthBinding);

			ingredientsList.getChildren().add(i);
		}

		// format ingredients within a vbox
		VBox ingredients = new VBox();

		ingredients.getChildren().addAll(ingredientsLabel, ingredientsList);
		ingredients.setPadding(new Insets(7, 7, 7, 7));

		// directions
		VBox directions = new VBox();
		directions.setPadding(new Insets(7, 7, 7, 7)); // copy that padding
		directions.setSpacing(25);

		Text directionsLabel = new Text("Directions:");
		directionsLabel.setFont(Font.font("Mono", FontWeight.BOLD, FontPosture.ITALIC, 2.0 * FONT_BASE));
		directions.getChildren().add(directionsLabel);

		for (int i = 0; i < r.getDirections().size(); ++i) {
			Text stepLabel = new Text("Step #" + (i + 1));
			stepLabel.setFont(Font.font("Mono", FontWeight.NORMAL, FontPosture.ITALIC, 0.9 * FONT_BASE));
			stepLabel.wrappingWidthProperty().bind(widthBinding);

			Text direction = new Text("  " + r.getDirections().get(i));
			direction.setFont(Font.font(1.2 * FONT_BASE));
			direction.wrappingWidthProperty().bind(widthBinding);

			// keep them close to eachother
			VBox combo = new VBox();
			combo.getChildren().addAll(stepLabel, direction);

			directions.getChildren().add(combo);
		}

		// bind width if avalible.

		if (widthBinding != null) {
			name.wrappingWidthProperty().bind(widthBinding);
			description.wrappingWidthProperty().bind(widthBinding);
			timeInfo.wrappingWidthProperty().bind(widthBinding);
			author.wrappingWidthProperty().bind(widthBinding);
			tags.wrappingWidthProperty().bind(widthBinding);
		}

		// add all text objects to pane
		getChildren().addAll(name, description, timeInfo, author, tags, ingredients, directions);
	}
}
