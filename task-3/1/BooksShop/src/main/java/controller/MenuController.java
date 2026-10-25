package controller;

import view.Navigator;

public class MenuController {
    private final Builder builder;
    private Navigator navigator;

    public MenuController(Builder builder){
        this.builder = builder;
    }
    public void run(){
        navigator = new Navigator(builder.getRootMenu());

        while(true){
            navigator.printMenu();
            int choice = ConsoleInput.getInstance().readInt("> ");
            navigator.navigate(choice);
        }
    }
}
