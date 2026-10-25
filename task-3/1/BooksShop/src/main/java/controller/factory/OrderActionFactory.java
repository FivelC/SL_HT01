package controller.factory;

import controller.action.CancelOrderAction;
import controller.action.CreateOrderAction;
import controller.action.ShowOrdersAction;
import view.IAction;

public class OrderActionFactory implements IActionFactory {

    @Override
    public IAction createShowAction() {
        return new ShowOrdersAction();
    }

    @Override
    public IAction createAddAction() {
        return new CreateOrderAction();
    }

    @Override
    public IAction createRemoveAction() {
        return new CancelOrderAction();
    }
}
