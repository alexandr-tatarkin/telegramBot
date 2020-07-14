package ru.telega;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@TelBotController
public class AnnotationTestController {

    @TelBotRequestMapping("/test")
    public SendMessage testMethod(Update update) {
        return new SendMessage(123L, "test");
    }
}
