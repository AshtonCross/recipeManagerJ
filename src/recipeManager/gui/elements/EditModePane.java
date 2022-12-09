/*
 * EditModePane.java
 *
 * This class defines the pane used for editing mode.
 */

package recipeManager.gui.elements;

import java.util.ArrayList;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import recipeManager.bookData.Recipe;
import recipeManager.manegment.Manager;

public class EditModePane extends BorderPane {
	private TextField tfName;

	public EditModePane(RecipeButton rb) {
		// taking in recipe as pointer to where it is in the manager
		// it can be freely changed

		Recipe r = rb.getRecipe();

		// name
		VBox name = new VBox();
		tfName = new TextField(r.getName());
		tfName.setPrefWidth(r.getName().length() * 5);
		name.getChildren().addAll(new Label("Name:"), tfName);
		// tfName.setMaxWidth(640);
		
		// Author
		VBox author = new VBox();
		TextField tfAuthor = new TextField(r.getAuthor());
		author.getChildren().addAll(new Label("Author:"), tfAuthor);

		// prep time
		VBox prepTime = new VBox();
		TextField tfPrepTime = new TextField(r.getPrepTime());
		prepTime.getChildren().addAll(new Label("Prep Time:"), tfPrepTime);

		// cook time
		VBox cookTime = new VBox();
		TextField tfCookTime = new TextField(r.getCookTime());
		cookTime.getChildren().addAll(new Label("Cook Time:"), tfCookTime);


		// description
		TextArea description = new TextArea(r.getDescription());
		description.setWrapText(true);
		
		
		// tags
		VBox tags = new VBox();
		TextField tfTags = new TextField();

		String tagText = "";

		for (String element : r.getTags()) {
			tagText += element + " ";
		}

		tfTags.setText(tagText);

		tags.getChildren().addAll(new Label("Tags (seperated by space):"), tfTags);

		// ingredients
		Label ingredientsLabel = new Label("Ingredients List:");

		// give each ingredient their own box
		ArrayList<TextField> ingredientTFArray = new ArrayList<>();
		VBox ingredientList = new VBox();
		ingredientList.setSpacing(7);

		for (String ingredient : r.getIngredients()) {
			TextField newTF = new TextField(ingredient);
			
			ingredientTFArray.add(newTF);
			ingredientList.getChildren().add(newTF);
		}

		// ingredient controls
		Button btIngAdd = new Button("+");
		Button btIngRem = new Button("-");

		HBox ingredientControls = new HBox();
		ingredientControls.getChildren().addAll(btIngAdd, btIngRem);
		ingredientControls.setSpacing(7);

		// TODO: allow for the deletion of steps nonsequentially

		VBox ingredientsEditor = new VBox();
		ingredientsEditor.setSpacing(10);
		ingredientsEditor.getChildren().addAll(ingredientsLabel, ingredientList, ingredientControls);

		// directions
		Label directionsLabel = new Label("Directions:");

		// each direction gets a text-area
		ArrayList<TextArea> directionTAList = new ArrayList<>();
		VBox directionList = new VBox();
		directionList.setSpacing(7);

		for (String direction : r.getDirections()) {
			TextArea newTA = new TextArea(direction);
			newTA.setPrefColumnCount(60);
			newTA.setMinHeight(5);
			newTA.setWrapText(true);
			directionTAList.add(newTA);
			directionList.getChildren().add(newTA);
		}

		// direction controls
		Button btDirAdd = new Button("+");
		Button btDirRem = new Button("-");

		HBox directionControls = new HBox();
		directionControls.getChildren().addAll(btDirAdd, btDirRem);
		directionControls.setSpacing(7);

		// put it in one thing
		VBox directionEditor = new VBox();
		directionEditor.setSpacing(15);
		directionEditor.getChildren().addAll(directionsLabel, directionList, directionControls);

		// vertical pane for all that junk
		VBox verticalPane = new VBox();
		verticalPane.getChildren().addAll(name, author, description, prepTime, cookTime, tags, ingredientsEditor, directionEditor);
		verticalPane.setSpacing(20);

		// controls
		Button btSave = new Button("Save Without Writing");
		Button btCancel = new Button("Cancel");
		Button btDel = new Button("Delete");

		HBox controls = new HBox();
		controls.setSpacing(15);
		controls.getChildren().addAll(btSave, btCancel, btDel);

		VBox all = new VBox();
		all.setPadding(new Insets(10, 10, 10, 10));
		all.getChildren().addAll(verticalPane, controls);
		all.setSpacing(35);
	
		this.setCenter(all);

		// scroll bar
		ScrollPane scrollBar = new ScrollPane(all);
		scrollBar.setPadding(new Insets(10, 10, 10, 10));
		this.setRight(scrollBar);

		// ingredient add buttons

		btIngAdd.setOnAction(e -> {
			// double originalHeight = all.getHeight();

			TextField newTF = new TextField("");
			ingredientTFArray.add(newTF);
			ingredientList.getChildren().add(newTF);

			// update scroll bar
			// scrollBar.setMax(scrollBar.getMax() + (all.getHeight() - originalHeight) /
			// scrollBar.getMax());
			// TODO figure out bind
			// scrollBar.maxProperty().bind(all.heightProperty().divide(scrollBar.getMax()));
		});

		btIngRem.setOnAction(e -> {
			// double originalHeight = all.getHeight();

			// remove last (linear)
			ingredientTFArray.remove(ingredientTFArray.size() - 1);
			ingredientList.getChildren().remove(ingredientList.getChildren().size() - 1);

			// scrollBar.setMax(scrollBar.getMax() + (originalHeight - all.getHeight()) /
			// scrollBar.getMax());

		});

		btDirAdd.setOnAction(e -> {
			TextArea newTA = new TextArea();
			newTA.setPrefColumnCount(60);
			newTA.setWrapText(true);
			directionTAList.add(newTA);
			directionList.getChildren().add(newTA);
		});

		btDirRem.setOnAction(e -> {
			directionTAList.remove(directionTAList.size() - 1);
			directionList.getChildren().remove(directionList.getChildren().size() - 1);
		});

		// setup behavior for the buttons

		btCancel.setOnAction(e -> {
			Manager.closeEditor();
		});
		
		btDel.setOnAction(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION,
					"Are you sure you want to delete this recipe?",
					ButtonType.YES, 
		            ButtonType.CANCEL);
			alert.setTitle("Date format warning");
			Optional<ButtonType> result = alert.showAndWait(); // not sure what "optional" means???
			
			if (result.get() == ButtonType.YES) {
				Manager.getRecipes().remove(r);
				Manager.getButtonsControl().updateButtons();
				Manager.getButtonsControl().getInformationPane().loadRecipe(r);
				Manager.getButtonsControl().getInformationPane().getChildren().clear();
				Manager.closeEditor();
			}
			
			
		});

		btSave.setOnAction(e -> {

			// basic info
			r.setName(tfName.getText());
			r.setPrepTime(tfPrepTime.getText());
			r.setCookTime(tfCookTime.getText());
			
			// author
			r.setAuthor(tfAuthor.getText());
			
			// description
			r.setDescription(description.getText());
			System.out.println(description.getText());

			// tags

			ArrayList<String> newTags = new ArrayList<>();

			// convert from text, to String[], to arraylist in one go!
			for (String newTag : tfTags.getText().trim().split(" ")) {
				newTags.add(newTag.toUpperCase());
			}

			r.setTags(newTags);

			// ingredients
			ArrayList<String> newIngredients = new ArrayList<>();
			for (TextField ingredient : ingredientTFArray) {
				newIngredients.add(ingredient.getText().trim());
			}

			r.setIngrediants(newIngredients);

			// directions TODO
			ArrayList<String> newDirections = new ArrayList<>();
			for (TextArea direction : directionTAList) {
				newDirections.add(direction.getText().trim());
			}

			r.setDirections(newDirections);

			// all done
			Manager.getButtonsControl().updateButtons();
			Manager.getButtonsControl().getInformationPane().loadRecipe(r);
			Manager.closeEditor();

			System.out.println(Manager.getRecipes().size());
			
			Manager.indicateUnsavedChanges();
			
			this.setMaxHeight(700);
		});
	}
}
