package controller;

import view.Navigator;

import java.util.Scanner;

public class MenuController {
    private final Builder builder;
    private Navigator navigator;

    public MenuController(Builder builder){
        this.builder = builder;
    }
    public void run(){
        navigator = new Navigator(builder.getRootMenu());
        Scanner scanner = new Scanner(System.in);

        while(true){
            navigator.printMenu();
            int choice = scanner.nextInt();
            navigator.navigate(choice);
        }
    }
}
