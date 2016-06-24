package model;

import java.util.Comparator;

/**
 * Created by AsusPC on 6/21/2016.
 */
public class Node {
    private int id;
    private double[] data;
    private double distance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

}