package controller.action;

import model.*;
import persistence.SerializeShop;
import view.IAction;

import java.io.IOException;
import java.util.List;

public class SerializeAction implements IAction {
    @Override
    public void execute() {
        try {
            SerializeShop.serialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
