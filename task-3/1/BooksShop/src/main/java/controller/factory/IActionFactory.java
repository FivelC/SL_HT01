package controller.factory;

import view.IAction;

public interface IActionFactory {
    IAction createShowAction();

    IAction createAddAction();

    IAction createRemoveAction();
}
