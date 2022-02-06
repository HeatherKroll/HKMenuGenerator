package MenuGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    private String mealToRecipesJoin = "from meal\n" +
            "join \n" +
            "meal_recipe_xref on meal_id=meal.id\n" +
            "join \n" +
            "recipes on meal_recipe_xref.recipe_id=recipes.id";
    private String recipeToIngredientsJoin = "from recipes \n" +
            "join\n" +
            "recipe_ingredients_xref on recipe_ingredients_xref.recipe_id=recipes.id\n" +
            "join\n" +
            "ingredients on recipe_ingredients_xref.ingredient_id=ingredients.id;";

    private Connection connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:C:/sqlite/db/meals.db/";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                System.out.println("Connected to the database");
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
            return conn;

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Meals> getMealsList()  {
        String sql = "SELECT * from meal";
        List<Meals> list = new ArrayList<Meals>();

        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            //array list and result set stuff
            ResultSet result = stmt.executeQuery();
            while (result.next()){
                Meals meal = new Meals();
                meal.setMealName(result.getString("meal_name"));
                meal.setOtherNote(result.getString("meal_notes"));

                list.add(meal);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;

    }


    public void insertMeal(String name, String other){
        String sql = "INSERT INTO meal (meal_name, meal_notes) " +
                "VALUES(?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, name);
            stmt.setString(2,other);

            int row = stmt.executeUpdate();

            System.out.println(row);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void insertRecipe(String name, String instructions, String notes){
        String sql = "insert into recipes (recipe_name, recipe_instructions, recipe_notes)" +
                "values(?, ?, ?)";
        try (Connection conn = this.connect();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, instructions);
            stmt.setString(3, notes);

            int row = stmt.executeUpdate();
            System.out.println(row);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertIngredient(String name, String type){
        String sql = "insert into ingredients(recipe_name, recipe_instructions, recipe_notes)" +
                "values(?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, type);

            int row = stmt.executeUpdate();
            System.out.println(row);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}



