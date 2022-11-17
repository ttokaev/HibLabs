package ru.sfedu.hibLabs.lab3.model.SingleTable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity(name = "TapeST")
@DiscriminatorValue("tape")
public class TapeST extends MeasurementST {
    private double abdomen;
    private double neck;
    private double waist;
    private double hips;

    public TapeST() {
    }

    public TapeST(double abd, double neck) {
        this.abdomen = abd;
        this.neck = neck;
    }

    public TapeST(double neck, double waist, double hips) {
        this.neck = neck;
        this.waist = waist;
        this.hips = hips;
    }

    public TapeST(double weight, double height, int age, String gender, double abdomen, double neck, double waist, double hips) {
        super(weight, height, age, gender);
        this.abdomen = abdomen;
        this.neck = neck;
        this.waist = waist;
        this.hips = hips;
    }

    public double getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(double abdomen) {
        this.abdomen = abdomen;
    }

    public double getNeck() {
        return neck;
    }

    public void setNeck(double neck) {
        this.neck = neck;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TapeST TapeST = (TapeST) o;
        return abdomen == TapeST.abdomen && neck == TapeST.neck && waist == TapeST.waist && hips == TapeST.hips;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), abdomen, neck, waist, hips);
    }

    @Override
    public String toString() {
        return "TapeST{" +
                "abdomen=" + abdomen +
                ", neck=" + neck +
                ", waist=" + waist +
                ", hips=" + hips +
                '}';
    }
}
