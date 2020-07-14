package ru.telega;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TelBotMethodContainer {

    private final Map<String, TelBotMethodController> controllerMap;

    private static class Holder {
        final static TelBotMethodContainer INSTANCE = new TelBotMethodContainer();
    }

    private TelBotMethodContainer() {
        controllerMap = new HashMap<>();
    }

    public static TelBotMethodContainer getInstance() {
        return Holder.INSTANCE;
    }

    public void addBotController(String key, TelBotMethodController controller) {
        if (controllerMap.containsKey(key)) throw new RuntimeException("This path " + key + " already exists");
        controllerMap.put(key, controller);
    }

    public TelBotMethodController getController(String key) {
        if (controllerMap.get(key) == null)
            if (controllerMap.get("") == null)
                throw new RuntimeException("Unknown command and no default controller provided");
            else
                return controllerMap.get("");
        else
            return controllerMap.get(key);
    }

    public boolean hasController(String key) {
        return controllerMap.containsKey(key);
    }
}
