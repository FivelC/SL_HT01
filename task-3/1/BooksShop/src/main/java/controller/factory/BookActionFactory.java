package controller.factory;

import controller.action.*;
import view.IAction;

public class BookActionFactory implements IActionFactory {
    @Override
    public IAction createShowAction() {
        return new ShowBooksAction();
    }

    @Override
    public IAction createAddAction() {
        return new StoreBookAction();
    }

    @Override
    public IAction createRemoveAction() {
        return new RemoveBookAction();
    }

    @Override
    public IAction importAction() {
        return new ImportBookAction();
    }

    @Override
    public IAction exportAction() {
        return new ExportBookAction();
    }
}
