package view;

public class Navigator {
    private Menu currentMenu;
    public Navigator(Menu currentMenu){
        this.currentMenu = currentMenu;
    }
    public void printMenu(){
        System.out.println("===="+currentMenu.getName()+"====");
        for(int i = 0; i < currentMenu.getMenuItems().length; i++){
            System.out.println(i + ": " + currentMenu.getMenuItems()[i].getTitle());
        }
    }
    public void navigate(int index){
        MenuItem[] items = currentMenu.getMenuItems();
        if(index < 0 || index >= items.length){
            System.out.println("Нет такого пункта");
            return;
        }
        MenuItem item = items[index];
        item.doAction();
        currentMenu = item.getNextMenu();
    }
}
