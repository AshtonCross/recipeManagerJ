# Recipe Manager

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

To run this program, go to the "releases" tab on github and download the version for your operating system. Currently the only two releases are for Windows and GNU/Linux, but other systems such as MacOSX and the BSD's can be run by downloading the raw jar file, then executing it (weather via a terminal or via shell script) with the additional arguments:

```
--module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml
```

Make sure that you have your [JavaFX path variables set up](https://openjfx.io/openjfx-docs/)! (or alternativly replace $PATH_TO_FX with the path to your JavaFX libraries (Ex: /usr/share/openjfx/lib)

## Compiling from Source

To compile from source, install Eclipse and then import the source as an existing project. From there, right click on RecipeManager.Main (not RecipeManager.gui.Main!), click "run as", then change the "VM Args" to match the aforementioned arguments used for running from the terminal, then compile and run. Also make sure to add the necissary libraies.

