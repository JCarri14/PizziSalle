package strategy;

import model.user.enums.UserLevel;
import view.View;

public class MenuWorkerStrategy implements MenuStrategy {
    public static UserLevel userLevel = UserLevel.WORKER;

    private View view;

    public MenuWorkerStrategy(View view) {
        this.view = view;
    }

    @Override
    public void executeMenu() {

    }
}
