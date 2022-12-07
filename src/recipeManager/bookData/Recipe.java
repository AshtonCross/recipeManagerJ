/*
 * Recipe.java
 * 
 * This class contains the information for one individual recipe.
 * 
 * This class is instantiate multiple times to store many recipes.
 */

package recipeManager.bookData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recipe {
	private String name = "Untitled Recipe"; // name
	private String description = "Description"; // basic description of the food in here.
	private String author = ""; // who made it?
	private ArrayList<String> tags = new ArrayList<String>(); // store all tags here
	private ArrayList<String> directions = new ArrayList<String>(); // store all directions here
	private ArrayList<String> ingredients = new ArrayList<String>(); // store ingredients
	private String prepTime = "";
	private String cookTime = "";

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

	public String getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(String prepTime) {
		this.prepTime = prepTime;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setIngrediants(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}

	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	public void setCookTime(String cookTime) {
		this.cookTime = cookTime;
	}

	public String getCookTime() {
		return cookTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public ArrayList<String> getDirections() {
		return directions;
	}

	public void setDirections(ArrayList<String> directions) {
		this.directions = directions;
	}
}
