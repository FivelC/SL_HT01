package controller.factory;

import controller.action.*;
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

    @Override
    public IAction importAction() {
        return new ImportOrderAction();
    }

    @Override
    public IAction exportAction() {
        return new ExportOrderAction();
    }
}
