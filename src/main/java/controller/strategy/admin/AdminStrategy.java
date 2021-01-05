package controller.strategy.admin;

import model.user.enums.UserLevel;
import controller.strategy.ControllerStrategy;
import view.View;

public class AdminStrategy implements ControllerStrategy {
    public static UserLevel userLevel = UserLevel.ADMIN;

    private View view;

    public AdminStrategy(View view) {
        this.view = view;
    }

    @Override
    public void executeMenu() {

    }
}
