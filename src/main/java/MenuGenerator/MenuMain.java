package MenuGenerator;

import java.util.Scanner;

public class MenuMain {

    public static Scanner scanner = new Scanner (System.in);

    public static MenuGeneratorController menuGeneratorController = new MenuGeneratorController();

    public static void main(String [] args){
        boolean quit = false;
        int choice = 0;

        printInstructions();

        while(!quit){
            System.out.println("Select an option");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    printInstructions();
                    break;
                case 2:
                    menuGeneratorController.printFullMealsList();
                    break;
                case 3:
                    generateMeals();
                    break;

                case 4:
                    removeMeal();
                    break;

                case 5:
                    modifyMeal();
                    break;

                case 6:
                    retrieveRecipe();
                    break;

                case 7:
                    retrieveDesserts();
                    break;

                case 8:
                    addMeal();
                    break;


                case 9:
                    quit = true;
            }



        }
    }


    public static void printInstructions(){
        System.out.println("1 - Show Instructions");
        System.out.println("2 - show all meal options");
        System.out.println("3 - Generate meals for X number of days");
        System.out.println("4 - Remove a meal");
        System.out.println("5 - Modify a recipe");
        System.out.println("6 - Retrieve recipe");
        System.out.println("7 - Show me deserts");
        System.out.println("8 - Add a meal");
        System.out.println("9 - Quit");

    }

    public static void generateMeals(){
       System.out.println("How many days worth of meals?");
        int numberOfDays = scanner.nextInt();
        if (numberOfDays == 0){
            menuGeneratorController.printFullMealsList();
        }

        menuGeneratorController.generateMeals(numberOfDays);

    }


    public static void removeMeal(){
        System.out.println("Select a number from the list of meals provided to remove ");
        int selection = scanner.nextInt();
        menuGeneratorController.removeMeal(selection);


    }

    public static void modifyMeal(){

    }

    public static void retrieveRecipe(){

    }

    public static void retrieveDesserts(){

    }

    public static void addMeal(){
        System.out.println("enter meal name");
        String name = scanner.nextLine();
        System.out.println("enter other notes");
        String other = scanner.nextLine();
        if (other == null){
            other = "no input";
        }
        menuGeneratorController.addMeal(name, other);

    }
}
