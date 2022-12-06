package recipeManager.bookData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CookBook {
    private File homeFile;
    private String name = "Untitled Recipe";
    private String description = "Description.";

    private final String[] DATA = { "cookBookName", "description"};
    
    public CookBook(File file) throws IOException {
        this.homeFile = file;
        
        try {
            this.readFromFile();
        }   catch (NoSuchElementException e) {
        	// file does not exist, create generic file with default values
        	
        	this.writeToFile();
        }
        
    }
    
    public void readFromFile() throws IOException {
        Scanner input = new Scanner(homeFile);
        
        int seekStage = 0;
        
        String currentLine = input.nextLine().trim();
        while (input.hasNextLine() && seekStage < DATA.length) {
            
            if (currentLine.equals(DATA[seekStage])) {
                String nl = input.nextLine().trim();
                
                switch (seekStage) {
                case 0:
                    this.name = nl;
                    break;
                case 1:
                    this.description = nl;
                    break;
                }
                
                ++seekStage;
            }
            
            currentLine = input.nextLine().trim();
        }
        
        input.close();
    }
    
    public void writeToFile() throws IOException {
    	if (!homeFile.exists()) {
            homeFile.createNewFile();
        }
    	
        FileWriter output = new FileWriter(homeFile, false);
        
        int writeStage = 0;
        int limit = 0;
        
        while (writeStage < DATA.length && ++limit < 100) {
            output.write(DATA[writeStage] + "\n");
            
            output.write("\t");
            
            switch (writeStage) {
            case 0:
                output.write(this.name);
                break;
            case 1:
                output.write(this.description);
                break;
            }
            
            output.write("\n");
            ++writeStage;
        }
        
        output.close();
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
}
