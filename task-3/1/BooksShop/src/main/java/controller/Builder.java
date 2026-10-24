package controller;

import view.Menu;
import view.MenuItem;

public class Builder {
    private Menu rootMenu;

    public Menu getRootMenu() {
        return rootMenu;
    }

    public void buildMenu() {
        rootMenu = new Menu();
        rootMenu.setName("Главное меню");

        Menu subMenu = new Menu();
        subMenu.setName("Подменю");

        rootMenu.setMenuItems(new MenuItem[]{
                new MenuItem("Сказать привет", () -> System.out.println("Привет!"), rootMenu),
                new MenuItem("Перейти в подменю", null, subMenu)
        });

        subMenu.setMenuItems(new MenuItem[]{
                new MenuItem("Назад", null, rootMenu)
        });
    }

}
