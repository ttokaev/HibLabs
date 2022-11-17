package ru.sfedu.hibLabs.lab4.model;

import javax.persistence.*;
import java.util.*;

@Entity(name = "UserMap")
@Table(schema = "lab4map")
public class UserMap {
    @Id
    private long id;
    private String name;
    private String surname;

    @ElementCollection
    @CollectionTable(schema = "lab4map", name = "measurement")
    @OrderColumn
    @Column(name = "weight")
    protected Map<Integer, Integer> weight = new HashMap<Integer, Integer>();

    @ElementCollection
    @CollectionTable(schema = "lab4map", name = "user")
    @OrderColumn
    @Column(name = "height")
    protected Map<Integer, Integer> height = new HashMap<Integer, Integer>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Map<Integer, Integer> getWeight() {
        return weight;
    }

    public void setWeight(Map<Integer, Integer> weight) {
        this.weight = weight;
    }

    public Map<Integer, Integer> getHeight() {
        return height;
    }

    public void setHeight(Map<Integer, Integer> height) {
        this.height = height;
    }
}
