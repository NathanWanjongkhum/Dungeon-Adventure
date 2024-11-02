## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
    - `main`: the folder that contains the main code plus some additional resources
        - `java`: the folder that contains the Java code
            - `App.java`: the application entry point
                - `Controller`: the folder that contains the controller classes
                - `model`: the folder that contains the model classes
                - `view`: the folder that contains the view classes
            - `module-info.java`: declares the module dependencies
        - `resources`: the folder that contains the JavaFX resources
    - `test`: the folder that contains test code for module `main`
- `pom.xml`: the Maven project object model file
- `.mvn`: the folder to store Maven dependencies


Meanwhile, the compiled output files will be generated in the `bin` folder by default. For this project, you can use `target` instead of `bin` as the output folder.

> In the `pom.xml` file, you can find the `<outputDirectory>` tag to specify the output folder of the compiled classes.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies). This is a Maven project, so you can also go to the `MAVEN` view and manage your dependencies.

## Running and Debugging

### Running the Application

You can run the application with the `Tasks: Run Task > Run JavaFX` command. This command will generate a runnable JAR file in the `target` folder, and then run it. Or you can run the `run` task in the `MAVEN` view.

### Testing the Application

You can run the JUnit tests with the `Testing` view.
