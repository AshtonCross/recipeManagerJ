package recipeManager.gui.elements;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import recipeManager.bookData.CookBook;
import recipeManager.bookData.Recipe;
import recipeManager.manegment.InvalidCookbookFileException;
import recipeManager.manegment.Manager;

public class CookBookControl extends VBox {
	public final int BUTTON_WIDTH = 250;
	public final int BUTTON_HEIGHT = 50;
	private VBox buttonPane = new VBox();
	private Button btNew = new Button("New"); // make a new recipe
	private Button btLoad = new Button("Load"); // load a cookbook
	private Button btEdit = new Button("Edit"); // make changes to recipes
	private Button btSave = new Button("Save"); // save changes to recipes
	private Button btSearch = new Button("Search / Filter");
	private RecipeButton selectedRecipe = null; // whichever recipe is currently selected
	private InformationPane info = new InformationPane();

	public CookBookControl() {
		// set up the buttons by getting currently open cookbooks.
		this.updateButtons();
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
		this.getChildren().add(buttonPane);

		HBox metaControls = new HBox();

		metaControls.getChildren().addAll(btNew, btLoad, btEdit, btSave);
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
			File file = fileChooser.showOpenDialog(new Stage());

			if (file == null) {
				// user has picked "cancel"
				return;
			}

			try {
				Manager.open(file);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println("it broke.");
				e1.printStackTrace();
			}

			this.updateButtons();
		});
		
		btNew.setOnAction(e -> {
			// TODO:
			
			// create a new recipe, add it to the file, and then open it for editing
		});
		
		btSave.setOnAction(e -> {
			// TODO:
			
			// if there's no cookbook open, then ask user for location and open the new one
			
			// write to open cookbook
		});
		
		btEdit.setOnAction(e -> {
			// get the current recipe and create a new editing pane based off it
			
			// get the current values and put them into editable text boxes
			
			// in this pane, have a cancel and save button
		});

	}

	public void updateButtons() {
		buttonPane.getChildren().clear();

		if (Manager.getRecipes().size() == 0) {
			Text noneFound = new Text("Please load a cookbook!");
			buttonPane.getChildren().add(noneFound);
			return;
		}

		for (int i = 0; i < Manager.getRecipes().size(); ++i) {
			Recipe r = Manager.getRecipes().get(i);
			RecipeButton newButton = new RecipeButton(r);
			newButton.setMinWidth(BUTTON_WIDTH);
			newButton.setMinHeight(BUTTON_HEIGHT);

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
