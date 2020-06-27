package ru.telega;

public enum ContentType {

    TEXT("text"),
    COMMAND("command"),
    STICKER("sticker");

    private final String name;

    ContentType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
