package ru.telega;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TelBotMethodContainerTest {

    private static final TelBotMethodContainer container = TelBotMethodContainer.getInstance();

    @Test
    void addBotController() {
        TelBotMethodController telBotMethodController = new TelBotMethodController(new AnnotationTestController(),
                AnnotationTestController.class.getMethods()[0]);
        container.addBotController("/tst", telBotMethodController);
        Assertions.assertEquals(telBotMethodController, container.getController("/tst"));
    }

}