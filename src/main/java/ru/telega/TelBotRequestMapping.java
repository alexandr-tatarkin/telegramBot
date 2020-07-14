package ru.telega;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TelBotRequestMapping {

    String[] value() default {};
    ContentType[] content() default {ContentType.COMMAND};
}
