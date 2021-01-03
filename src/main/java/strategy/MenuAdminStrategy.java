package strategy;

import model.user.enums.UserLevel;
import view.View;

public class MenuAdminStrategy implements MenuStrategy {
    public static UserLevel userLevel = UserLevel.ADMIN;

    private View view;

    public MenuAdminStrategy(View view) {
        this.view = view;
    }

    @Override
    public void executeMenu() {

    }
}
