package MenuGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MenuGeneratorController {
    MenuDAO menu = new MenuDAO();


    //meals controls
    private List<Meals> meals = new ArrayList<Meals>();
    private List<Recipes> recipes = new ArrayList<Recipes>();
    private List<Ingredients> ingredients = new ArrayList<Ingredients>();



    public void generateMeals(int days){
        meals = menu.getMealsList();
        List<Meals> shuffleList = new ArrayList<>(meals);
        Collections.shuffle(shuffleList);
        for (int i=0; i<days; i++){
            Meals meal = shuffleList.get(i); //returns the array list element at that index
            System.out.println(i+1);
            System.out.println(meal.getMealName());
            System.out.println(" ");
        }
    }
//Meal Controls
    public void getFullMealsList(){
        meals = menu.getMealsList();
    }

    public void printFullMealsList(){
        getFullMealsList();
        for (int i=0; i<meals.size(); i++){
            Meals meal = meals.get(i);
            System.out.println(i+1 + ". " + meal.getMealName());
            System.out.println(" ");
        }
    }


    public void removeMeal(int selection){
        meals = menu.getMealsList();




    }

    public void modifyMeal(){

    }


    public void addMeal(String name, String other){
        Meals meal = new Meals();
        meal.setMealName(name);
        meals.add(meal);
        MenuDAO menu = new MenuDAO();
        menu.insertMeal(name, other);
    }

//recipe controls
    public void addRecipe(String name, String instructions, String notes){
        Recipes recipe = new Recipes();
        recipe.setRecipeName(name);
        recipes.add(recipe);
        menu.insertRecipe(name, instructions, notes);

    }


    public void retrieveRecipe(){

    }

//Ingredients Controls
    public void addIngredient(String name, String type){
        Ingredients ingredient = new Ingredients();
        ingredient.setIngredientName(name);
        menu.insertIngredient(name, type);

    }




}
