package controller.factory;

import controller.action.CancelOrderAction;
import controller.action.ShowRequestsAction;
import view.IAction;

public class RequestActionFactory implements IActionFactory{
    @Override
    public IAction createShowAction() {
        return new ShowRequestsAction();
    }

    @Override
    public IAction createAddAction() {
        return null;
    }

    @Override
    public IAction createRemoveAction() {
        return null;
    }
}
