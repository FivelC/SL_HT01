package view;

import java.util.List;

public class Navigator {
    private Menu currentMenu;
    public Navigator(Menu currentMenu){
        this.currentMenu = currentMenu;
    }
    public void printMenu(){
        System.out.println("===="+currentMenu.getName()+"====");
        for(int i = 0; i < currentMenu.getMenuItems().size(); i++){
            System.out.println(i + ": " + currentMenu.getMenuItems().get(i).getTitle());
        }
    }
    public void navigate(int index){
        List<MenuItem> items = currentMenu.getMenuItems();
        if(index < 0 || index >= items.size()){
            System.out.println("Нет такого пункта");
            return;
        }
        MenuItem item = items.get(index);
        item.doAction();
        currentMenu = item.getNextMenu();
    }
}
