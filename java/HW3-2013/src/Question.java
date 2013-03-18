package ru.spbau.bandurin.task3;

public class Question {
    private Type type;

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public static enum Type {
        SINGLE_CHOICE, MULIT_CHOICE, TEXT
    }
}