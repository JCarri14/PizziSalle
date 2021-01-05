package view.components;

import java.util.List;

public class CustomerComponents {

    public static String createCustomerMenu() {
        return "\nWhat do you want to do?\n" +
                "\t[1] Make order\n" +
                "\t[2] See cart\n" +
                "\t[3] Update credentials\n" +
                "\t[4] Update delegation\n" +
                "\t[5] Exit\n" +
                "Option: ";
    }

    public static String createDelegationMenu() {
        return "\nChoose your delegation:\n" +
                "\t[1]. Barcelona\n" +
                "\t[2]. Lleida\n" +
                "\t[3]. Tarragona\n" +
                "\t[4]. Girona\n" +
                "Delegacio: ";
    }

    public static String createOrderOptions() {
        return "\nOrder items:\n" +
                "\t 1. Add Pizza\n" +
                "\t 2. Add Drink\n" +
                "\t 3. Add Comment\n" +
                "\t 4. Finish\n";
    }

    public static String createPizzaCart(List<String> pizzas) {
        return createGridCart(3, pizzas);
    }

    public static String createExtraCart(List<String> extras) {
        return createGridCart(3, extras);
    }

    public static String createIngredientsCart(List<String> ingredients) {
        return createGridCart(3, ingredients);
    }

    public static String createDrinkCart(List<String> drinks) {
        return createGridCart(1, drinks);
    }

    private static String createGridCart(int cols, List<String> items) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            res.append(i + 1).append(". ").append(items.get(i)).append(" ");
            if (i % cols == 0) res.append("\n");
        }
        return res.toString();
    }
}
