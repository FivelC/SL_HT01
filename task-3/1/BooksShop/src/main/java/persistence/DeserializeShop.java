package persistence;

import controller.ShopProvider;
import model.Shop;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class DeserializeShop {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static ShopState deserialize() throws IOException {
        File file = new File("app_state.json");
        if(!file.exists()) {
            throw new IOException("Файл сохранения не найден");
        }

        return mapper.readValue(file, ShopState.class);
    }

    public static void restore(Shop shop) throws IOException {
        ShopState state = deserialize();

        shop.importBooks(state.books());
        shop.importOrders(state.orders());
        shop.importRequests(state.requests());
    }

}
