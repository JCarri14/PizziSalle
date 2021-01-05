package view;

import model.pizza.Drink;
import model.pizza.Ingredient;
import model.pizza.Mass;
import model.pizza.Pizza;

import java.util.List;
import java.util.Scanner;


public class View {
    private Scanner scanner;

    public View() {
        scanner = new Scanner(System.in);
    }

    public void showWelcomeText () {
        System.out.println("*************************************************");
        System.out.println("Benvingut a PizziSalle");
        System.out.println("*************************************************");
    }

    public String prompt(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    /*
    public void printExistingCustomerInfo(Customer customer) {
        System.out.println("\t- Nom: " + customer.getName() + System.lineSeparator()
            + "\t- Cognoms: " + customer.getSurname1() + " " + customer.getSurname2() + System.lineSeparator()
            + "\t- Telefon: " + customer.getPhoneNumber() + System.lineSeparator()
            + "\t- Direccio postal: " + customer.getAddress() + System.lineSeparator()
            + "\t- Poblacio: " + customer.getCity());
    }
    */

    public void startNewOrder(){
        System.out.println();
        System.out.println("---------------------------------------------------");
        System.out.println("---- Demanant una nova comanda ----");
        System.out.println("---------------------------------------------------");
    }

    public void printUserInformation(String name, String delegation) {
        System.out.println("" +
                "----------------------------------------------------------------" +
                "\nClient name: " + name +
                "\nDelegation: " + delegation +
                "\n----------------------------------------------------------------");
    }

    public void printPizzas(List<Pizza> pizzas, String especialPizza) {
        System.out.println();
        System.out.println("---- Pizzes disponibles ----");

        int j = 0;
        for (Pizza p : pizzas) {
            if (/*!p.esEspecial() ||*/ p.getName().equals(especialPizza)) {
                System.out.println("\t[" + (++j) + "]. " + p.getName() + " (" + concatIngredients(p.getIngredients()) + ")");
            }
        }
    }

    public void printMassas(List<Mass> massas) {
        System.out.println();
        System.out.println("---- Masses disponibles ----");
        for (int i = 0; i < massas.size(); i++) {
            Mass c = massas.get(i);
            System.out.println("\t[" + (i+1) + "]. " + c.getName());
        }
    }

    public void printDrinks(List<Drink> drinks) {
        System.out.println();
        System.out.println("---- Begudes disponibles ----");
        for (int i = 0; i < drinks.size(); i++) {
            Drink d = drinks.get(i);
            System.out.println("\t[" + (i+1) + "]. " + d.getName());
        }
    }

    public void printAvailableIngredients(List<Ingredient> ingredients) {
        System.out.println();
        System.out.println("---- Ingredients disponibles ----");
        for (int i = 0; i < ingredients.size(); i++) {
                Ingredient ingredient = ingredients.get(i);
                System.out.println("\t[" + (i+1) + "]. " + ingredient.getName());
        }
    }

    private String concatIngredients(List<Ingredient> ingredients) {
        String s = "";

        for (Ingredient i: ingredients) {
            s += i.getName() + ", ";
        }

        return s.substring(0, s.length() - 2);
    }
}

