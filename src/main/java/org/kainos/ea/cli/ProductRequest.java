package org.kainos.ea.cli;

/**
 * Object to hold PUT and POST requests for products (includes all attributes except the 'productID')
 */
public class ProductRequest {

    private String name;
    private String description;
    private double price;
    private int supplierID;

    public ProductRequest(String name, String description, double price, int supplierID) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.supplierID = supplierID;
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

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

}
