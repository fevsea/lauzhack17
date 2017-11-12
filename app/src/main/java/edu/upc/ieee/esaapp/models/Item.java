package edu.upc.ieee.esaapp.models;

/**
 * Created by alejandro on 12/11/17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("num")
    @Expose
    private int num;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("value")
    @Expose
    private String value;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
