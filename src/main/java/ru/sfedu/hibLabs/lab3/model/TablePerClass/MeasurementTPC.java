package ru.sfedu.hibLabs.lab3.model.TablePerClass;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "MeasurementTPC")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MeasurementTPC {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long measurementId;
    private double weight;
    private double height;
    private int age;
    private String gender;

    public MeasurementTPC() {
    }

    public MeasurementTPC(String g) {
        this.gender = g;
    }

    public MeasurementTPC(double weight, double height, int age, String gender) {
        this.measurementId = System.currentTimeMillis();
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }

    public void setCommon(long userid, double weight, double height, int age, String gender) {
        this.measurementId = System.currentTimeMillis();
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }


    public long getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(long measurementId) {
        this.measurementId = measurementId;
    }

    public void setMeasurementId() {
        this.measurementId = System.currentTimeMillis();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeasurementTPC)) return false;
        MeasurementTPC that = (MeasurementTPC) o;
        return measurementId == that.measurementId && Double.compare(that.weight, weight) == 0 && Double.compare(that.height, height) == 0 && age == that.age && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(measurementId, weight, height, age, gender);
    }

    @Override
    public String toString() {
        return "Measurement{" +
                ", measurementId=" + measurementId +
                ", weight=" + weight +
                ", height=" + height +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
