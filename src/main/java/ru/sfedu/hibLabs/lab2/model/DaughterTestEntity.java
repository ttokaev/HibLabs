package ru.sfedu.hibLabs.lab2.model;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class DaughterTestEntity {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DaughterTestEntity that = (DaughterTestEntity) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "EmbeddedEntity{" +
                "text='" + text + '\'' +
                '}';
    }
}