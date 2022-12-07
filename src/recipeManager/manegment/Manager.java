package recipeManager.manegment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
			System.out.println("r\t" + nextLine);
			switch (nextLine) {
			case "\t{":
				tempRecipe = new Recipe();
				System.out.println("reset the recipe");
				break;
			case "\t\trecipeName":
				tempRecipe.setName(input.nextLine().trim());
				break;
			case "\t\tdescription":
				tempRecipe.setDescription(input.nextLine().trim());
				break;
			case "\t}":
				// add previous recipe progress
				// reset recipe for continued writing
				recipes.add(tempRecipe);
				System.out.println("added " + tempRecipe);
				break;
			}

			nextLine = input.nextLine();
		}
	}

	public static ArrayList<Recipe> getRecipes() {
		return recipes;
	}

	public static CookBook currentCookBook() {
		return currentCookBook;
	}
}
