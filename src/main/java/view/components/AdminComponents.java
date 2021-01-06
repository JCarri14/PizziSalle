package view.components;

public class AdminComponents {

    public static String createAdminMenu() {
        return "\nWhat do you want to do?\n" +
                "\t[1] Config Cart\n" +
                "\t[2] Config Users\n" +
                "\t[3] System Logs\n" +
                "\t[4] Show Statistics\n" +
                "\t[5] Exit\n" +
                "Option: ";
    }

    public static String createConfigCartMenu() {
        return "\nChoose Action\n" +
                "\t[1] Add Item\n" +
                "\t[2] Remove Item\n" +
                "\t[3] Back...\n" +
                "Action: ";
    }

    public static String createCartItemMenu() {
        return "\nChoose item type\n" +
                "\t[1] Pizza\n" +
                "\t[2] Drink\n" +
                "\t[3] Ingredient\n" +
                "\t[4] Mass\n" +
                "\t[5] Back...\n" +
                "Action: ";
    }

    public static String createConfigUserMenu() {
        return "\nChoose Action\n" +
                "\t[1] Add User\n" +
                "\t[2] Delete User\n" +
                "\t[3] Change Permission Level\n" +
                "\t[4] Back...\n" +
                "Action: ";
    }

}
