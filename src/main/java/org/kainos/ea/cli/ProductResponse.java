package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ProductResponse {

    private int productID;
    private String name;
    private String description;
    private double price;
    private String supplierName;
    private int supplierID;

    public ProductResponse(int productID, String name, String description, double price, String supplierName, int supplierID) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.supplierName = supplierName;
        this.supplierID = supplierID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }
}
