package ru.sfedu.hibLabs.lab4.model;

import javax.persistence.Embeddable;

@Embeddable
public class Measurement {
    private int age;
    private int weight;
    private int height;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
