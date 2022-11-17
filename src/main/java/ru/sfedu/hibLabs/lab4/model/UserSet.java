package ru.sfedu.hibLabs.lab4.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "UserSet")
@Table(schema = "lab4set")
public class UserSet {
    @Id
    private long id;
    private String name;
    private String surname;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(schema = "lab4set",
            name = "measurement")
    @Column(name = "weight")
    protected Set<String> weight = new HashSet<String>();

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

    public Set<String> getWeight() {
        return weight;
    }

    public void setWeight(Set<String> weight) {
        this.weight = weight;
    }

//    public Set<Integer> getHeight() {
//        return height;
//    }
//
//    public void setHeight(Set<Integer> height) {
//        this.height = height;
//    }
}
