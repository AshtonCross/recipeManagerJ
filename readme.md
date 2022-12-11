# Recipe Manager 0.1.2

Recipe Manager is a free software program written in Java that helps you manage your recipies.

It works by storing them in a mildly json-inspired plaintext format denoted by a ".cb" (for Cook Book) at the end of the file. To start working with a cook book, just run the program, start writing down recipes, and then save them to a file with the "Write" button.

This program was written for my final project in Computer Science II, and it was very fun to write!

## Features

- Interactive GUI.

- Store information such as Prep time, cook time, ingredients, and instructions in an organized manner.

- Read cook book files.

- Modify said cookbook files or create new ones. 

- Filter recipes with tags.

## Downloading and Running

Naviate to the "releases" page and download a the .jar. After downloading, it should be able to be run with the JDK with a double click. If not, try to right click and, click "open with", then select Java. If none of this works, open up a terminal, navigate to the diretory of the .jar file, and type:

```
java -jar Recipe-Manager_0-1-1.jar
```

Recipe Manager was compiled using OpenJDK-17. I recommend using Java 17 to run it. (If you don't feel like using this version, try compiling it with a different version. It probably works.)

## Compiling from Source

To compile from source, install Eclipse and then import the source as an existing project. From there, right click on RecipeManager.Main (not RecipeManager.gui.Main!), click "run as", then change the "VM Args" to match the aforementioned arguments used for running from the terminal, then compile and run. Also make sure to add the necissary libraies.

## 0.1.2 Changelog

* Tweaked filter menu's layout.

## Example Cookbook

All recipes in the example cookbook are from [https://based.cooking](https://based.cooking).

