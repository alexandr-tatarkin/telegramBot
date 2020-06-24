package com.example.telega.commands;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface TCommandAuthorized {

    void execute(Update update);

}
