package com.example.telega.boilerplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
@RequiredArgsConstructor
public class TelBot extends TelegramLongPollingBot {

    @Value("${com.example.bot-token}")
    private String botToken;

    @Value("${com.example.bot-name}")
    private String botName;


    private static final TelBotMethodContainer container = TelBotMethodContainer.getInstance();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String key = "";
            if (update.getMessage().isCommand())
                key = update.getMessage().getText().split(" ")[0].trim();
            else if (update.getMessage().hasText())
                key = ContentType.TEXT.toString();
            else if (update.getMessage().hasSticker())
                key = ContentType.STICKER.toString();
            TelBotMethodController controller = container.getController(key);
            if (controller == null) controller = container.getController("");
            try {
                execute(controller.process(update));
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

}