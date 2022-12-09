/*
 * CookBookControl.java
 *
 * this file contains the declaration for the controls for interacting with the recipes.
 *
 */

package recipeManager.gui.elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import recipeManager.bookData.Recipe;
import recipeManager.manegment.Manager;

public class CookBookControl extends VBox {
	public final int BUTTON_WIDTH = 250;
	public final int BUTTON_HEIGHT = 50;
	private VBox buttonPane = new VBox();
	private Button btNew = new Button("New"); // make a new recipe
	private Button btLoad = new Button("Load"); // load a cookbook
	private Button btEdit = new Button("Edit"); // make changes to recipes
	private Button btWrite = new Button("Write"); // save changes to recipes
	private Button btSearch = new Button("Search / Filter");
	private RecipeButton selectedRecipe = null; // whichever recipe is currently selected
	private InformationPane info = new InformationPane();

	public CookBookControl() {
		// set up the buttons by getting currently open cookbooks.
		this.updateButtons();
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
		buttonPane.setSpacing(15);
		this.getChildren().add(buttonPane);
		Manager.setCookBookControl(this);

		HBox metaControls = new HBox();

		metaControls.getChildren().addAll(btNew, btLoad, btEdit, btWrite);
		metaControls.setAlignment(Pos.BOTTOM_CENTER);
		metaControls.setSpacing(5);

		VBox controls = new VBox();
		controls.getChildren().add(metaControls);
		controls.getChildren().add(btSearch);
		controls.setAlignment(Pos.BASELINE_CENTER);

		controls.setPadding(new Insets(10, 10, 10, 10));

		this.getChildren().add(controls);

		// giving the buttons their function
		btLoad.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Cookbook");
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CB files (*.cb)", "*.cb");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showOpenDialog(new Stage());

			if (file == null) {
				// user has picked "cancel"
				return;
			}

			try {
				Manager.open(file);
			} catch (FileNotFoundException e1) {
				System.out.println("it broke.");
				e1.printStackTrace();
			}

			this.updateButtons();
			Manager.setButtonsControl(this);
		});

		btNew.setOnAction(e -> {
			Manager.getRecipes().add(new Recipe());
			Manager.indicateUnsavedChanges();
			this.updateButtons();
		});

		btWrite.setOnAction(e -> {
			System.out.println("BUTTON WRITE PROCESSED");
			Manager.write();

		});

		btEdit.setOnAction(e -> {
			if (selectedRecipe == null)
				return;

			Manager.openEditor(selectedRecipe);
		});
		
		btSearch.setOnAction(e -> {
			Manager.openFilterMenu();
		});

	}

	public void updateButtons() {
		ArrayList<String> allTags = new ArrayList<String>();
		buttonPane.getChildren().clear();

		if (Manager.getRecipes().size() == 0) {
			Text noneFound = new Text("Press \"New\" to get started!");
			buttonPane.getChildren().add(noneFound);
			return;
		}

		for (Recipe r : Manager.getRecipes()) {
			allTags.addAll(r.getTags()); // for use with the set to view all types of tags

			if (Manager.getFilter().size() > 0) {
				boolean passesFilter = true;
				
				for (String filter : Manager.getFilter()) {
					if (!r.getTags().contains(filter))
						passesFilter = false;
				}

				// move to next recipe without adding.
				if (!passesFilter) continue;
			}
			

			RecipeButton newButton = new RecipeButton(r);
			newButton.setMinWidth(BUTTON_WIDTH);
			newButton.setMinHeight(BUTTON_HEIGHT);
			newButton.setWrapText(true);

			// reset the text weight of the last one and do this one
			newButton.setOnAction(e -> {
				if (!(selectedRecipe == null))
					selectedRecipe.setFont(Font.font("Mono", FontWeight.NORMAL, FontPosture.REGULAR, 14));

				selectedRecipe = newButton;
				newButton.setFont(Font.font("Mono", FontWeight.BOLD, FontPosture.ITALIC, 15));
				info.loadRecipe(r);
			});

			buttonPane.getChildren().add(newButton);
		}
		
		// after all is said and done, update the tags set
		Manager.updateTagsSet(allTags);

	}

	public InformationPane getInformationPane() {
		return info;
	}

	public Recipe getSelectedRecipe() {
		// if it's empty just return null
		if (selectedRecipe == null)
			return null;

		return selectedRecipe.getRecipe();
	}

	public int countCookBooks() {
		return buttonPane.getChildren().size();
	}
}
