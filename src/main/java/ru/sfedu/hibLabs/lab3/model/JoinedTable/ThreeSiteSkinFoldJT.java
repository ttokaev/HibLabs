package ru.sfedu.hibLabs.lab3.model.JoinedTable;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "ThreeSiteSkinFoldJT")
@PrimaryKeyJoinColumn(name = "measurement_id")
public class ThreeSiteSkinFoldJT extends MeasurementJT {
    private double chest;
    private double thigh;
    private double tricep;
    private double suprailiac;
    private double abdominal;

    public ThreeSiteSkinFoldJT() {}

    public ThreeSiteSkinFoldJT(double thigh, double tricep, double suprailiac, int f) {
        this.thigh = thigh;
        this.tricep = tricep;
        this.suprailiac = suprailiac;
    }

    public ThreeSiteSkinFoldJT(double thigh, double chest, double abdominal) {
        this.chest = chest;
        this.thigh = thigh;
        this.abdominal = abdominal;
    }

    public ThreeSiteSkinFoldJT(double weight, double height, int age, String gender, double chest, double thigh, double tricep, double suprailiac, double abdominal) {
        super(weight, height, age, gender);
        this.chest = chest;
        this.thigh = thigh;
        this.tricep = tricep;
        this.suprailiac = suprailiac;
        this.abdominal = abdominal;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getThigh() {
        return thigh;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public double getTricep() {
        return tricep;
    }

    public void setTricep(double tricep) {
        this.tricep = tricep;
    }

    public double getSuprailiac() {
        return suprailiac;
    }

    public void setSuprailiac(double suprailiac) {
        this.suprailiac = suprailiac;
    }

    public double getAbdominal() {
        return abdominal;
    }

    public void setAbdominal(double abdominal) {
        this.abdominal = abdominal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ThreeSiteSkinFoldJT that = (ThreeSiteSkinFoldJT) o;
        return chest == that.chest && thigh == that.thigh && tricep == that.tricep && suprailiac == that.suprailiac && abdominal == that.abdominal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), chest, thigh, tricep, suprailiac, abdominal);
    }

    @Override
    public String toString() {
        return "ThreeSiteSkinFoldJT{" +
                "chest=" + chest +
                ", thigh=" + thigh +
                ", tricep=" + tricep +
                ", suprailiac=" + suprailiac +
                ", abdominal=" + abdominal +
                '}';
    }
}
