package controller.factory;

import controller.action.RemoveBookAction;
import controller.action.ShowBooksAction;
import controller.action.StoreBookAction;
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
}
