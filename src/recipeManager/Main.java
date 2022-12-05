package recipeManager;

import java.io.IOException;

import recipeManager.bookData.CookBook;
import recipeManager.manegment.CookBookManager;

public class Main {

    public static void main(String[] args) {
        CookBookManager manager = new CookBookManager();
        
        manager.openCookBook("test.cb");
        CookBook cb = manager.getCurrentCookBook();
        
        System.out.println(cb.getName());
        System.out.println(cb.getDescription());
        
        cb.setName("edward");

        System.out.println(cb.getName());
        
        try {
            cb.writeToFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
