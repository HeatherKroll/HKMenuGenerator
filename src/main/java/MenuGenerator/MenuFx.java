package MenuGenerator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static javafx.stage.StageStyle.DECORATED;


public class MenuFx extends Application {

    MenuGeneratorController menuGeneratorController = new MenuGeneratorController();

    private List<Ingredients> ingredients = new ArrayList<>();


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
        Label label1 = new Label("Welcome to HK Meal Generator! Choose your desired option");

        //button options
        Button button1 = new Button("Add a New Meal or Recipe");
        Button button2 = new Button("Generate Meals");
        Button button3 = new Button("Show All My Meal Options");
        Button button4 = new Button("Edit or Delete Existing Meal");


        //parentContainer Children
        parentContainer.getChildren().addAll(label1, button1, button2, button3, button4);

        for (Node node: parentContainer.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setPrefWidth(300);
            }
        }

       // Stream.of(button1, button2, button3, button4).forEach(button -> button.setPrefWidth(300));


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

        vBox.getChildren().addAll(instructions, enterMealName, mealNameTxt, enterMealNotes, mealNotesTxt, addRecipe,
                submitMeal, backToMain(primaryStage));



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
        HBox hBox = new HBox();

        primaryStage.setScene(scene2);

        //Labels
        Label instructions = new Label("Add a new recipe");
        Label enterRecipeName = new Label("Enter recipe name *");
        Label enterRecipeIngredients = new Label("Enter recipe ingredients and press 'Add'");
        Label enterRecipeInstructions = new Label("Enter recipe instructions *");
        Label enterRecipeNotes = new Label("Enter recipe notes");
        Label recipeToMealsLabel = new Label("Select the meal(s) to which this recipe belongs");

        //Text Fields
        TextField enterRecipeNameTxt = new TextField();
        TextField enterRecipeIngredientsTxt = new TextField();
        TextField enterIngredientTypeTxt = new TextField();
        enterRecipeIngredientsTxt.setPromptText("Name");
        enterIngredientTypeTxt.setPromptText("Type");
        TextField enterRecipeInstructionsTxt = new TextField();
        TextField enterRecipesNotesTxt = new TextField();
        ComboBox <String> recipeToMealsCombo = new  ComboBox<String>();

        //Buttons
        Button addIngredient = new Button("Add");
        Button submitRecipe = new Button("Submit Recipe");

        //Add to scene
        vBox.getChildren().addAll(instructions, enterRecipeName, enterRecipeNameTxt, enterRecipeIngredients);

        vBox.getChildren().add(hBox);

        hBox.getChildren().addAll(enterRecipeIngredientsTxt, enterIngredientTypeTxt, addIngredient);
        vBox.getChildren().addAll(enterRecipeInstructions, enterRecipeInstructionsTxt, enterRecipeNotes,
                enterRecipesNotesTxt, recipeToMealsLabel, recipeToMealsCombo);


       //combobox
        recipeToMealsCombo.getItems().addAll("Meal 1", "Meal 2", "Meal 3");


        //Buttons
        vBox.getChildren().addAll(submitRecipe, backToMain(primaryStage));

        //Submits the recipe and ingredients. Ingredients can't be null with a new recipe submission.
        submitRecipe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (ingredients.isEmpty()){
                    Label ingredientsRequired = new Label("Ingredients are required to submit a recipe");
                }
                //insert recipe name, instructions, and notes into db
                String name = enterRecipeNameTxt.getText();
                String instructions = enterRecipeInstructionsTxt.getText();
                String notes = enterRecipeInstructionsTxt.getText();

                menuGeneratorController.addRecipe(name, instructions, notes);

                //insert ingredients list object name and type into db
                int i=0;
                while(i<ingredients.size()){
                    Ingredients ingredient = ingredients.get(i);
                    String ingredientName1 = ingredient.getIngredientName();
                    String type = ingredient.getIngredientType();
                    menuGeneratorController.addIngredient(ingredientName1, type);
                }

                //show success/verification message
                Label submitSuccess = new Label("Added recipe = " + name);

                vBox.getChildren().add(submitSuccess);
            }
        });

        addIngredient.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
               String ingredientName = enterRecipeIngredientsTxt.getText();
               String ingredientType = enterIngredientTypeTxt.getText();
               Ingredients ingredient = new Ingredients();
               ingredient.setIngredientName(ingredientName);
               ingredient.setIngredientType(ingredientType);

               ingredients.add(ingredient);

               Label submitSuccess = new Label("Added recipe = " + ingredientName);
               vBox.getChildren().add(submitSuccess);
            }

        });

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
        vBox.getChildren().addAll(label, backToMain(primaryStage));

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
