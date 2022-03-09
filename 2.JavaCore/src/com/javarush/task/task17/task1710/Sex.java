package com.javarush.task.task17.task1710;

public enum Sex {
    MALE ("м"),
    FEMALE ("ж");

    Sex(String sex) {
        this.sex = sex;
    }

    private String sex;

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return this.getSex();
    }
}
