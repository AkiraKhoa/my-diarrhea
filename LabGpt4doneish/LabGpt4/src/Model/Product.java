/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.time.LocalDate;

public class Product {
    private String productCode;
    private String productName;
    private String category; // New field for category
    private LocalDate manufacturingDate;
    private LocalDate expirationDate;
    private double price;
    private int ImportedQuantity;
    private int ExportedQuantity;


    // Constructor
    public Product(String productCode, String productName, String category, LocalDate manufacturingDate, LocalDate expirationDate, double price) {
        this.productCode = productCode;
        this.productName = productName;
        this.category = category; // Initialize the category
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.price = price;
    }

    public int getImportedQuantity() {
        return ImportedQuantity;
    }

    public void setImportedQuantity(int ImportedQuantity) {
        this.ImportedQuantity = ImportedQuantity;
    }

    public int getExportedQuantity() {
        return ExportedQuantity;
    }

    public void setExportedQuantity(int ExportedQuantity) {
        this.ExportedQuantity = ExportedQuantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
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

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public boolean isExpired() {
    if(expirationDate == null) {
        throw new IllegalStateException("Expiration date is not set.");
    }
    return LocalDate.now().isAfter(expirationDate);
}

    @Override
    public String toString() {
        return "Product Code: " + productCode + "\n" +
               "Product Name: " + productName + "\n" +
               "Category: " + category + "\n" +
               "Manufacturing Date: " + manufacturingDate + "\n" +
               "Expiration Date: " + expirationDate + "\n" +
               "Price: " + price + "\n";
    }

}
