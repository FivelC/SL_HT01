import controller.Builder;
import controller.MenuController;
import view.Navigator;

class Main{
    public static void main(String[] args){
        Builder builder = new Builder();
        builder.buildMenu();
        MenuController menu = new MenuController(builder);
        menu.run();
    }
}
