package com.example.telega;

import com.example.telega.commands.TCommand;
import com.example.telega.commands.TCommandAuthorized;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class MyBot extends TelegramLongPollingBot {

    @Value("${com.example.bot-token}")
    private String botToken;

    @Value("${com.example.bot-name}")
    private String botName;

    private final Map<String, TCommand> commands;
    private final Map<String, TCommandAuthorized> authorizedCommands;

    private final Config config;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().isCommand()) {
            if (config.getChats().contains(update.getMessage().getChatId()))
                Optional.ofNullable(authorizedCommands.get(update.getMessage().getText()))
                        .ifPresent(cmd -> cmd.execute(update));
            Optional.ofNullable(commands.get(update.getMessage().getText()))
                    .ifPresent(cmd -> cmd.execute(update));
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
