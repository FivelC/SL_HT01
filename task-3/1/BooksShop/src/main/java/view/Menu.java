package view;

public class Menu {
    String name;
    MenuItem[] menuItems;

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public String getName() {
        return name;
    }

    public void setMenuItems(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    public void setName(String name) {
        this.name = name;
    }
}
