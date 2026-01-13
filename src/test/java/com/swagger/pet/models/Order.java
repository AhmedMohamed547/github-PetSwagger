package com.swagger.pet.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
    public enum orderStatus{
        PLACED,
        APPROVED,
        DELIVERED
    }
    @JsonProperty("id")
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private orderStatus status;
    private Boolean complete;
    @JsonProperty("id")

    public int getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }
    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public orderStatus getStatus() {
        return status;
    }

    public void setStatus(orderStatus status) {
        this.status = status;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }




}
