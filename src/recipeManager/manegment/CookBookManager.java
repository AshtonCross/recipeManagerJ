package recipeManager.manegment;

import recipeManager.bookData.CookBook;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CookBookManager {
	private static ArrayList<CookBook> openCookBooks = new ArrayList<CookBook>();
    private static CookBook currentCookBook;
    
    public static void openCookBook(String cookBookPath) {
        File file = new File(cookBookPath);
        
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
