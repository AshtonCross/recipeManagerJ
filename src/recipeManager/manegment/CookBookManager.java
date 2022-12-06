package recipeManager.manegment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import recipeManager.bookData.CookBook;

public class CookBookManager {
	private static ArrayList<CookBook> openCookBooks = new ArrayList<>();
    private static CookBook currentCookBook;

    public static void openCookBook(String cookBookPath) {
        File file = new File(cookBookPath);

        if (!file.exists()) {
        	createCookBook(file);
        	return;
        }

        try {
			CookBook newCookBook = new CookBook(file);
	        openCookBooks.add(newCookBook);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public static void createCookBook(String cookBookPath) {
        // create basic cook book and write to file
        // set this as current cook book after closing
    	File file = new File(cookBookPath);

    	createCookBook(file);
    }

    public static void createCookBook(File file) {
        // create basic cook book and write to file
        // set this as current cook book after closing

    	try {
    		file.createNewFile();
			CookBook newCookBook = new CookBook(file);
			openCookBooks.add(newCookBook);
			newCookBook.writeToFile();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void saveOpenCookBooks() {
    	// iterate through every cook book and have them write their stuff.
    }

    public static ArrayList<CookBook> getOpenCookBooks() {
    	return openCookBooks;
    }

    public CookBook getCurrentCookBook() {
        return currentCookBook;
    }
}
