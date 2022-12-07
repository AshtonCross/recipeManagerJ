package recipeManager.bookData;

import java.util.ArrayList;
import java.util.Scanner;

public class Recipe {
	private String name = "Untitled Recipe";
	private String description = "Description";

	public Recipe() {

	}

	public static ArrayList<Recipe> readRecipes(Scanner input) {
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();

		String nextLine = input.nextLine().trim();
		System.out.println("recipe gen\t" + nextLine);
		while (!nextLine.equals("END OF RECIPES")) {
			if (nextLine.equals("{")) {
				recipes.add(parseRecipe(input));
			}

			nextLine = input.nextLine().trim();
		}

		return recipes;
	}

	private static Recipe parseRecipe(Scanner input) {
		System.out.println("i was called");
		Recipe out = new Recipe();

		String nextLine = input.nextLine().trim();
		System.out.println("new r\t" + nextLine);

		while (!nextLine.equals("}")) {
			switch (nextLine) {
			case "recipeName":
				out.name = input.nextLine().trim();
				break;
			case "description":
				out.description = input.nextLine().trim();
				break;
			}

			nextLine = input.nextLine().trim();
		}

		return out;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return name;
	}
}
