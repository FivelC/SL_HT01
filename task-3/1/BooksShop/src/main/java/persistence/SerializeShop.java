package persistence;

import controller.ShopProvider;
import model.*;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SerializeShop {
    private static Shop shop = ShopProvider.getInstance().getShop();
    private static ObjectMapper mapper = new ObjectMapper();
    public static void serialize() throws IOException {
        ShopState state = new ShopState(
                shop.getBooks(SortBook.NONE),
                shop.getOrders(SortOrder.NONE),
                shop.getRequests(),
                shop.isAutoCloseRequests()
        );
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(state);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("app_state.json"))){
            writer.write(json);
            System.out.println("Успешно сохранено в файл app_state.json");
        }catch (IOException e) {
            System.out.println("Не получилось сохранить магазин в \"app_state.json\": " + e.getMessage());
        }
    }
}
