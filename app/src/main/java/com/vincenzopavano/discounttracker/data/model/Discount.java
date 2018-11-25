package com.vincenzopavano.discounttracker.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Discount {
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Company")
    @Expose
    private String company;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Website")
    @Expose
    private String website;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Latitude")
    @Expose
    private Double latitude;
    @SerializedName("Longitude")
    @Expose
    private Double longitude;
    @SerializedName("CategoryId")
    @Expose
    private int categoryId;

    public Discount(int id, String company, String description, String address, String website, String phone, Double latitude, Double longitude, int categoryId) {
        this.id = id;
        this.company = company;
        this.description = description;
        this.address = address;
        this.website = website;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}

