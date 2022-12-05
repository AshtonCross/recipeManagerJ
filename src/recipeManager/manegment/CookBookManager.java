package recipeManager.manegment;

import recipeManager.bookData.CookBook;
import java.io.File;

public class CookBookManager {
    CookBook currentCookBook;
    
    public void openCookBook(String cookBookPath) {
        File file = new File(cookBookPath);
        this.currentCookBook = new CookBook(file);
    }
    
    public void createCookBook(String cookBookPath) {
        // create basic cook book and write to file
        // set this as current cook book after closing 
    }
    
    public CookBook getCurrentCookBook() {
        return currentCookBook;
    }
}
