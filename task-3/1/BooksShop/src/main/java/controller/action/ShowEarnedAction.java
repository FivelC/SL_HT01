package controller.action;

import controller.ShopProvider;
import model.*;
import controller.ConsoleInput;
import view.IAction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ShowEarnedAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        LocalDate from = input.readDate("С какой даты:");
        LocalDate to = input.readDate("По какую дату:");

        System.out.println(shop.getEarnedAmount(from,to));
    }
}
