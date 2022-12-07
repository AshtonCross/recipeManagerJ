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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import recipeManager.bookData.*;

public class Manager {
	private static CookBook currentCookBook = new CookBook();
	private static ArrayList<Recipe> recipes = new ArrayList<Recipe>();

	public static void open(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);

		// if it's empty just create a new cookbook and exit
		if (!input.hasNext()) {
			currentCookBook = new CookBook();
		}

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
				// we now save this recipe to the recipe list.
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
				ArrayList<String> arrayTags = new ArrayList<String>();

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

		ArrayList<String> list = new ArrayList<String>();

		while (!nextLine.equals("}")) {
			// add each element from the list, under .cb ""syntax"" is seperated by line

			list.add(nextLine);

			nextLine = input.nextLine().trim();
		}

		return list;
	}

	public static ArrayList<Recipe> getRecipes() {
		return recipes;
	}

	public static CookBook currentCookBook() {
		return currentCookBook;
	}
}
