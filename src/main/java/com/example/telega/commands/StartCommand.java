package com.example.telega.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component("/start")
@Slf4j
@RequiredArgsConstructor
public class StartCommand implements TCommandAuthorized {

    private final DefaultAbsSender sender;

    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage(update.getMessage().getChatId(), "Welcome");
        try {
            sender.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

}
