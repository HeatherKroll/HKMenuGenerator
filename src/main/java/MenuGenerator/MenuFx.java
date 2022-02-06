package MenuGenerator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.stage.StageStyle.DECORATED;


public class MenuFx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(DECORATED);
        primaryStage.setTitle("Menu Generator");
        primaryStage.setWidth(890);
        primaryStage.setHeight(700);
        mainMenu(primaryStage);
        primaryStage.show();
    }

    public void mainMenu(Stage primaryStage){

        //structure
        VBox parentContainer = new VBox();
        Scene scene = new Scene(parentContainer);
        primaryStage.setScene(scene);
        //labels
        Label label = new Label("Welcome to HK Meal Generator! Choose your desired option");

        //button options
        Button button1 = new Button("Add a New Meal or Recipe");
        button1.setPrefWidth(300);
        Button button2 = new Button("Generate Meals");
        button2.setPrefWidth(300);
        Button button3 = new Button("Show All My Meal Options");
        button3.setPrefWidth(300);
        Button button4 = new Button("Edit or Delete Existing Meal");
        button4.setPrefWidth(300);


        //parentContainer Children
        parentContainer.getChildren().add(label);
        parentContainer.getChildren().add(button1);
        parentContainer.getChildren().add(button2);
        parentContainer.getChildren().add(button3);
        parentContainer.getChildren().add(button4);

        //button event listeners
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addANewMeal(primaryStage);
            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                generateMealsFx(primaryStage);
            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showAllMealOptions(primaryStage);
            }
        });

        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                editOrDeleteExistingMeal(primaryStage);
            }
        });

    }
    public void addANewMeal(Stage primaryStage){
        VBox vBox = new VBox();
        Scene scene2 = new Scene(vBox);
        primaryStage.setScene(scene2);
        Label instructions = new Label("Add a new meal");
        Label enterMealName = new Label("Enter meal name *");
        Label enterMealNotes = new Label("Enter meal notes");
        TextField mealNameTxt = new TextField();
        TextField mealNotesTxt = new TextField();

        Button submitMeal = new Button("Submit Meal");
        Button addRecipe = new Button("Add Recipe");

        vBox.getChildren().add(instructions);
        vBox.getChildren().add(enterMealName);
        vBox.getChildren().add(mealNameTxt);
        vBox.getChildren().add(enterMealNotes);
        vBox.getChildren().add(mealNotesTxt);
        vBox.getChildren().add(addRecipe);
        vBox.getChildren().add(submitMeal);
        vBox.getChildren().add(backToMain(primaryStage));

        MenuGeneratorController menuGeneratorController = new MenuGeneratorController();

        submitMeal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = mealNameTxt.getText();
                String notes = mealNotesTxt.getText();
                menuGeneratorController.addMeal(name, notes);

                Label submitSuccess = new Label("Added meal = " + name +"and note = " + notes);

                vBox.getChildren().add(submitSuccess);
            }
        });
        addRecipe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addANewRecipe(primaryStage);
            }
        });

    };

    public void addANewRecipe(Stage primaryStage){
        VBox vBox = new VBox();
        Scene scene2 = new Scene(vBox);
        primaryStage.setScene(scene2);
        Label instructions = new Label("Add a new recipe");
        Label enterRecipeName = new Label("Enter recipe name *");
        Label enterRecipeInstructions = new Label("Enter recipe instructions *");
        Label enterRecipeNotes = new Label("Enter recipe notes");
        Label recipeToMealsLabel = new Label("Select the meal(s) to which this recipe belongs");

        TextField enterRecipeNameTxt = new TextField();
        TextField enterRecipeInstructionsTxt = new TextField();
        TextField enterRecipesNotesTxt = new TextField();
        //ComboBox recipeToMealsCombo = new ComboBox();

        Button submitRecipe = new Button("Submit Recipe");

        vBox.getChildren().add(instructions);
        vBox.getChildren().add(enterRecipeName);
        vBox.getChildren().add(enterRecipeNameTxt);
        vBox.getChildren().add(enterRecipeInstructions);
        vBox.getChildren().add(enterRecipeInstructionsTxt);
        vBox.getChildren().add(enterRecipeNotes);
        vBox.getChildren().add(enterRecipesNotesTxt);
        vBox.getChildren().add(submitRecipe);
        vBox.getChildren().add(backToMain(primaryStage));

        //recipeToMealsCombo.getItems().add("Meal 1");
        //recipeToMealsCombo.getItems().add("Meal 2");
        //recipeToMealsCombo.getItems().add("Meal 3");
    }

    public void generateMealsFx(Stage primaryStage){
        VBox vBox = new VBox();
        Scene scene2 = new Scene(vBox);
        primaryStage.setScene(scene2);
        Label label = new Label("Enter in desired amount of days and select '/Generate Meals below/'");
        vBox.getChildren().add(label);
        vBox.getChildren().add(backToMain(primaryStage));

    };
    public void showAllMealOptions(Stage primaryStage){
        VBox vBox = new VBox();
        Scene scene2 = new Scene(vBox);
        primaryStage.setScene(scene2);
        Label label = new Label("Choose a meal to view from the list of meals below");
        vBox.getChildren().add(label);
        vBox.getChildren().add(backToMain(primaryStage));

    };



    public void editOrDeleteExistingMeal(Stage primaryStage){
        VBox vBox = new VBox();
        Scene scene2 = new Scene(vBox);
        primaryStage.setScene(scene2);
        Label label = new Label("Add a new meal or recipe");
        vBox.getChildren().add(label);
        vBox.getChildren().add(backToMain(primaryStage));

    };

    public Button backToMain(Stage primaryStage){
        Button back = new Button("<-- Back to Menu");
        back.setCancelButton(true);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainMenu(primaryStage);
            }
        });

        return back;
    }
}
