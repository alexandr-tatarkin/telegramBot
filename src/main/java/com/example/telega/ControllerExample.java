package com.example.telega;

import com.example.telega.boilerplate.ContentType;
import com.example.telega.boilerplate.TelBotController;
import com.example.telega.boilerplate.TelegramRequestMapping;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@TelBotController
public class ControllerExample {

    @TelegramRequestMapping(value = "/ok")
    public SendMessage ok(Update update) {
        return new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("okay bro, okay!");
    }

    @TelegramRequestMapping(value = "/start")
    public SendMessage start(Update update) {
        return new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("Welcome!");
    }

    @TelegramRequestMapping(value = "")
    public SendMessage defaultMethod(Update update) {
        return new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("Unknown command - " + update.getMessage().getText());
    }


    @TelegramRequestMapping(content = ContentType.TEXT)
    public SendMessage text(Update update) {
        if (update.getMessage().getText().contains("test"))
            return new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("Testing ..");
        else
            return new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("IDK");
    }

    @TelegramRequestMapping(content = ContentType.STICKER)
    public SendMessage sticker(Update update) {
        return new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("Nice sticker. BTW file id - " + update.getMessage().getSticker().getFileId());
    }


}
