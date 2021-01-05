package controller.strategy.worker;

import model.user.enums.UserLevel;
import controller.strategy.ControllerStrategy;
import view.View;

public class WorkerStrategy implements ControllerStrategy {
    public static UserLevel userLevel = UserLevel.WORKER;

    private View view;

    public WorkerStrategy(View view) {
        this.view = view;
    }

    @Override
    public void executeMenu() {

    }
}
