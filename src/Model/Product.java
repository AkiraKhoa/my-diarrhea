package model;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productCode;
    private String productName;
    private Date manufacturingDate;
    private Date expirationDate;
    private double price;
    private int quantity;
    
    public Product(String productCode, String productName, Date manufacturingDate, Date expirationDate, double price, int quantity) {
        this.productCode = productCode;
        this.productName = productName;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Validation methods (you can add validation logic here)
    public boolean isValidProduct() {
        // Implement validation logic, e.g., check for valid product code, dates, etc.
        return true; // Return true if the product is valid, false otherwise
    }

    @Override
    public String toString() {
        // Customize the toString method to display product information
        return "Product Code: " + productCode +
               "\nProduct Name: " + productName +
               "\nManufacturing Date: " + manufacturingDate +
               "\nExpiration Date: " + expirationDate +
               "\nPrice: " + price +
               "\nQuantity: " + quantity;
    }
    // Constructor, getters, setters, and validation methods here
}
