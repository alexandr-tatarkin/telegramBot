package ru.telega;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Slf4j
public class TelBotUpdateHandlerBeanPostProcessor implements BeanPostProcessor {

    private final TelBotMethodContainer container = TelBotMethodContainer.getInstance();

    @Override
    public Object postProcessBeforeInitialization(Object bean, @Nullable String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(TelBotController.class)) {
            Arrays.stream(beanClass.getMethods())
                    .filter(method -> method.isAnnotationPresent(TelegramRequestMapping.class))
                    .forEach((Method method) -> generateController(bean, method));
        }
        return bean;
    }

    private void generateController(Object bean, Method method) {
        TelegramRequestMapping requestMapping = method.getAnnotation(TelegramRequestMapping.class);
        for (ContentType contentType : requestMapping.content()) {
            if (contentType.equals(ContentType.COMMAND))
                Arrays.stream(requestMapping.value())
                        .forEach(path -> container.addBotController(path, new TelBotMethodController(bean, method)));
            else
                container.addBotController(contentType.toString(), new TelBotMethodController(bean, method));
        }
    }

}