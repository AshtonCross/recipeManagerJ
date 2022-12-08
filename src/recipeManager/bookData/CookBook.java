/*
 * CookBook.java
 *
 * This file contains the basic data read from a cook book file,
 * and is where data relating to the cook book itself is.
 */

package recipeManager.bookData;

import java.io.File;

import recipeManager.manegment.Manager;

public class CookBook {
	private File homeFile;
	private String name = "untitled.cb";
	private String description = "Description.";

	public CookBook() {

	}

	public String getName() {
		return Manager.getLocation().getName();
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
}
