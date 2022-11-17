package ru.sfedu.hibLabs.lab4.model;

import javax.persistence.*;
import java.util.*;

@Entity(name = "UserCol")
@Table(schema = "lab4col")
public class UserCol {
        @Id
        private long id;
        private String name;
        private String surname;

        @ElementCollection
        @CollectionTable(schema = "lab4col", name = "measurement")
        @AttributeOverride(
                name = "weight",
                column = @Column(name = "weight")
        )
        protected Set<Measurement> weight = new HashSet<Measurement>();


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

        public Set<Measurement> getWeight() {
            return weight;
        }

        public void setWeight(Set<Measurement> weight) {
            this.weight = weight;
        }

}
