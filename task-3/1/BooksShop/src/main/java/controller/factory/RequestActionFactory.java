package controller.factory;

import controller.action.*;
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
        return new CancelRequestAction();
    }

    @Override
    public IAction importAction() {
        return new ImportRequestAction();
    }

    @Override
    public IAction exportAction() {
        return new ExportRequestAction();
    }
}
