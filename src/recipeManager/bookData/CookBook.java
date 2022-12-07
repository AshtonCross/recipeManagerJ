/*
 * CookBook.java
 * 
 * This file contains the basic data read from a cook book file,
 * and is where data relating to the cook book itself is.
 */

package recipeManager.bookData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CookBook {
	private File homeFile;
	private String name = "untitled.cb";
	private String description = "Description.";
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

	public CookBook() {

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

	public File getFile() {
		return homeFile;
	}

	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}
}
