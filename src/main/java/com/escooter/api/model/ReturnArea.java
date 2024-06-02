package com.escooter.api.model;

import java.util.ArrayList;
import java.util.Map;

public class ReturnArea {

    private int idreturnArea;
    private ArrayList<Map<String, String>> areaPoint;

    // Getters and Setters
    public int getIdreturnArea() {
        return idreturnArea;
    }

    public void setIdreturnArea(int idreturnArea) {
        this.idreturnArea = idreturnArea;
    }

    public ArrayList<Map<String, String>> getAreaPoint() {
        return areaPoint;
    }

    public void setAreaPoint(ArrayList<Map<String, String>> areaPoint) {
        this.areaPoint = areaPoint;
    }
}
