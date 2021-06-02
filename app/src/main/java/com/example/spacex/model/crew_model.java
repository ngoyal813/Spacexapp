package com.example.spacex.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "crew_details")
public class crew_model {

    @NonNull
    @PrimaryKey
    private String name;

    @SerializedName("image")
    private String imgae;

    private String agency;
    private String status;

    @SerializedName("wikipedia")
    private String wikipedia_link;



    public crew_model(String name, String imgae, String agency, String status, String wikipedia_link) {
        this.name = name;
        this.imgae = imgae;
        this.agency = agency;
        this.status = status;
        this.wikipedia_link = wikipedia_link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgae() {
        return imgae;
    }

    public void setImgae(String imgae) {
        this.imgae = imgae;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWikipedia_link() {
        return wikipedia_link;
    }

    public void setWikipedia_link(String wikipedia_link) {
        this.wikipedia_link = wikipedia_link;
    }

    @Override
    public String toString() {
        return "crew_model{" +
                "name='" + name + '\'' +
                ", imgae='" + imgae + '\'' +
                ", agency='" + agency + '\'' +
                ", status='" + status + '\'' +
                ", wikipedia_link='" + wikipedia_link + '\'' +
                '}';
    }

}
