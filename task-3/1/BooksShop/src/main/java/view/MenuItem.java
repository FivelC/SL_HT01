package view;

import java.io.IOException;

public class MenuItem {
    private final String title;
    private final IAction action;
    private Menu nextMenu;

    public MenuItem(String title, IAction action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }

    public void doAction() {
        if (action != null) {
            try {
                action.execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public String getTitle() { return title; }
    public Menu getNextMenu() { return nextMenu; }
}