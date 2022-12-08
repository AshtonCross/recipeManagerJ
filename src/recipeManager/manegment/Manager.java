/*
 * Management.java
 *
 * The management class is a class used statically containing many static
 * variables useful throughout any different panes that may be conjured up
 * while running.
 *
 * This class also handles the IO, and is what reads and writes to cook book
 * files.
 */

package recipeManager.manegment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import recipeManager.bookData.CookBook;
import recipeManager.bookData.Recipe;
import recipeManager.gui.elements.CookBookControl;
import recipeManager.gui.elements.EditModePane;
import recipeManager.gui.elements.FilterPane;
import recipeManager.gui.elements.RecipeButton;

public class Manager {
	private static CookBook currentCookBook = new CookBook();
	private static ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	private static boolean hasCookBookOpen = false;
	private static Stage primaryStage;
	private static Scene cookBookViewer;
	private static Stage editStage;
	private static CookBookControl buttons;
	private static File location;
	private static ArrayList<String> filters = new ArrayList<String>();

	// get pointer to stage
	public static void setPrimaryStage(Stage s) {
		primaryStage = s;
	}

	// get pointer to scene (this is NOT the pane itself)
	public static void setCookBookViewer(Scene s) {
		cookBookViewer = s;
	}
	
	public static ArrayList<String> getFilter() {
		return filters;
	}

	public static void setButtonsControl(CookBookControl b) {
		buttons = b;
	}

	public static CookBookControl getButtonsControl() {
		return buttons;
	}

	public static void open(File file) throws FileNotFoundException {
		location = file;
		Scanner input = new Scanner(file);

		// reset
		currentCookBook = new CookBook();
		recipes.clear();
		if (buttons != null)
			buttons.getInformationPane().getChildren().clear();

		primaryStage.setTitle("Recipe Manager - " + currentCookBook.getName());

		String nextLine = input.nextLine().trim();

		while (input.hasNextLine()) {
			switch (nextLine) {
			case "cookBookName":
				currentCookBook.setName(input.nextLine().trim());
				break;
			case "description":
				currentCookBook.setDescription(input.nextLine().trim());
				break;
			case "recipes":
				readRecipes(input);
				break;
			}

			if (input.hasNextLine())
				nextLine = input.nextLine().trim();
		}

		input.close();
		hasCookBookOpen = true;
	}
	
	public static void openFilterMenu() {
		
		FilterPane filterScreen = new FilterPane();
		Scene scene = new Scene(filterScreen);
		Stage filterMenu = new Stage();
		filterMenu.setScene(scene);
		filterMenu.setTitle("Filter by Tags");
		filterMenu.show();
		
		filterScreen.setStage(filterMenu);
	}

	private static void readRecipes(Scanner input) {
		String nextLine = input.nextLine();
		Recipe tempRecipe = new Recipe();

		while (!nextLine.equals("END OF RECIPES") && input.hasNextLine()) {
			switch (nextLine) {
			case "\t{":
				// opening curly bracket with 1 tab = new recipe declaration.
				// clear out the old tempRecipe definition with a new one.
				tempRecipe = new Recipe();
				break;

			case "\t}":
				// closing curly bracket with 1 tab = end recipe declaration.
				// we now save this recipe to the recipe list.)
				recipes.add(tempRecipe);
				System.out.println("added " + tempRecipe);
				break;

			case "\t\trecipeName":
				tempRecipe.setName(input.nextLine().trim());
				break;

			case "\t\tauthor":
				tempRecipe.setAuthor(input.nextLine().trim());
				break;

			case "\t\tdescription":
				tempRecipe.setDescription(input.nextLine().trim());
				break;

			case "\t\tprepTime":
				tempRecipe.setPrepTime(input.nextLine().trim());
				break;

			case "\t\tcookTime":
				tempRecipe.setCookTime(input.nextLine().trim());
				break;

			// now for some arrays

			case "\t\tdirections":
				tempRecipe.setDirections(readList(input));
				break;

			case "\t\tingredients":
				tempRecipe.setIngrediants(readList(input));
				break;

			case "\t\ttags":
				// all tags seperated by a SPC then split into arraylist.

				String[] rawTags = input.nextLine().trim().split(" ");
				ArrayList<String> arrayTags = new ArrayList<>();

				tempRecipe.setTags(arrayTags);

				for (String tag : rawTags)
					arrayTags.add(tag);

				break;

			}

			nextLine = input.nextLine();
		}
	}

	private static ArrayList<String> readList(Scanner input) {
		/*
		 * This method takes a scanner, and reads
		 */

		String nextLine = input.nextLine().trim(); // this should be "{"

		if (!nextLine.equals("{")) {
			System.out.println("List missing starting bracket.");
			return null; // no list :(
		} else {
			// skip initial opening bracket
			nextLine = input.nextLine().trim();
		}

		ArrayList<String> list = new ArrayList<>();

		while (!nextLine.equals("}")) {
			// add each element from the list, under .cb ""syntax"" is seperated by line

			list.add(nextLine);

			nextLine = input.nextLine().trim();
		}

		return list;
	}

	public static void createNewCookBook(File file) {
		createNewCookBook(file, "untitled.cb");
	}

	public static void createNewCookBook(File file, String name) {
		// TODO:
		// see if a new file is able to be created

		// if yes, create the file and open it

		// once open, return

	}
	
	public static void setCookBookControl(CookBookControl c) {
		buttons = c;
	}

	public static void write() {
		System.out.println("write being attempted");
		if (location == null) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Write to which file?");
			
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CB files (*.cb)", "*.cb");
			fileChooser.getExtensionFilters().add(extFilter);
			

			File file = fileChooser.showSaveDialog(new Stage());

			if (file == null) {
				// user has picked "cancel"
				return;
			}
			
			System.out.println(file);
			file = new File(new String(file + ".cb"));

			location = file;
			currentCookBook.setName(file.getName());
		}

		try {
			FileWriter writer = new FileWriter(location, false);

			// consider StringBuilder TODO
			String output = "";

			// name

			output += "cookBookName\n";
			output += "\t" + currentCookBook.getName() + "\n";
			output += "description\n";
			output += "\t" + currentCookBook.getDescription() + "\n";
			output += "dateCreated\n\t0\n"; // temp
			output += "dateLastModified\n\t493489348934\n";

			output += "recipes\n";

			System.out.println(getRecipes().size());

			for (Recipe r : getRecipes()) {
				output += "\t{\n";

				output += "\t\trecipeName\n";
				output += "\t\t\t" + r.getName() + "\n";

				output += "\t\tdescription\n";
				output += "\t\t\t" + currentCookBook.getDescription() + "\n";

				output += "\t\tauthor\n";
				output += "\t\t\t" + r.getAuthor() + "\n";

				output += "\t\tprepTime\n";
				output += "\t\t\t" + r.getPrepTime() + "\n";

				output += "\t\tcookTime\n";
				output += "\t\t\t" + r.getCookTime() + "\n";

				output += "\t\tingredients\n";
				output += getWriteList(r.getIngredients());

				output += "\t\tdirections\n";
				output += getWriteList(r.getDirections());

				output += "\t\ttags\n\t\t\t";

				for (String tag : r.getTags()) {
					output += tag + " ";
				}

				output += "\n";

				output += "\t}\n";
			}

			output += "END OF RECIPES\n";

			writer.write(output);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("wrote");
	}

	public static String getWriteList(ArrayList<String> list) {
		String returnString = "";
		returnString += "\t\t{\n";

		for (String item : list) {
			returnString += "\t\t\t" + item + "\n";
		}

		returnString += "\t\t}\n";

		return returnString;
	}

	public static File getLocation() {
		return location;
	}

	public static void setLocation(File file) {
		location = file;
	}

	public static ArrayList<Recipe> getRecipes() {
		return recipes;
	}

	public static CookBook currentCookBook() {
		return currentCookBook;
	}

	public static boolean isCookBookOpen() {
		return hasCookBookOpen;
	}

	public static void openEditor(RecipeButton rb) {
		System.out.println("opened" + rb.getRecipe());
		System.out.println(Manager.getRecipes().size());

		// create edit scene
		EditModePane edit = new EditModePane(rb);
		edit.setPrefHeight(550);
		edit.setPrefWidth(650);
		Scene editScene = new Scene(edit);

		// new stage
		editStage = new Stage();
		editStage.setScene(editScene);
		editStage.setTitle("Edit - " + rb.getRecipe().getName());
		editStage.setMinHeight(550);
		editStage.setMinWidth(550);
		editStage.show();
	}

	public static void closeEditor() {
		editStage.close();
	}
}
