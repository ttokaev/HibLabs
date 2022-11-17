package ru.sfedu.hibLabs.lab4.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "UserList")
@Table(schema = "lab4list")
public class UserList {
    @Id
    private long id;
    private String name;
    private String surname;

    @ElementCollection
    @CollectionTable(schema = "lab4list", name = "measurement")
    @OrderColumn
    @Column(name = "weight")
    protected List<Integer> weight = new ArrayList<Integer>();
//
//    @ElementCollection
//    @CollectionTable(schema = "lab4list", name = "user")
//    @OrderColumn
//    @Column(name = "height")
//    protected List<Integer> height = new ArrayList<Integer>();

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

    public List<Integer> getWeight() {
        return weight;
    }

    public void setWeight(List<Integer> weight) {
        this.weight = weight;
    }
//
//    public List<Integer> getHeight() {
//        return height;
//    }
//
//    public void setHeight(List<Integer> height) {
//        this.height = height;
//    }
}
