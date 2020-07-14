package ru.telega;

import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TelBotMethodControllerTest {

    TelBotMethodController controller =
            new TelBotMethodController(new AnnotationTestController(),  AnnotationTestController.class.getMethods()[0]);

    @Test
    void process() {
        BotApiMethod<?> method = controller.process(new Update());
        assertEquals(String.valueOf(123L), ((SendMessage) method).getChatId());

    }
}