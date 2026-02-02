package com.zhrfrd.model;

public class Question {
    public enum Type {
        BOOLEAN,
        TEXT,
        NUMBER
    }

    private String id;
    private String text;
    private Type type;

    public Question(String id, String text, Type type) {
        this.id = id;
        this.text = text;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Type getType() {
        return type;
    }
}
