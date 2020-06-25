package com.example.telega.boilerplate;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class TelBotMethodController {

    private final Object bean;
    private final Method method;

    public TelBotMethodController(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
    }

    public BotApiMethod<?> process(Update update) {
        try {
            return (BotApiMethod<?>) method.invoke(bean, update);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("bad invoke method", e);
        }
        return null;
    }
}
