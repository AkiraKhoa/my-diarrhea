/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public Product(){}

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String formattedManufactureDate = manufacturingDate.format(formatter);
        String formattedExpiryDate = expirationDate.format(formatter);
        return "Product Code: " + productCode + "\n" +
               "Product Name: " + productName + "\n" +
               "Category: " + category + "\n" +
               "Manufacturing Date: " + formattedManufactureDate + "\n" +
               "Expiration Date: " + formattedExpiryDate + "\n" +
               "Price: " + price + "\n";
    }
    
    public String toStringReportProduct() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String formattedManufactureDate = manufacturingDate.format(formatter);
        String formattedExpiryDate = expirationDate.format(formatter);

        // Use the String format method to format the product details as a table row.
        return String.format("| %-8s | %-20s | %-12s | %-12s | %-10d |",
                productCode, productName, manufacturingDate, expirationDate, ImportedQuantity+ExportedQuantity);
    }
    public String toStringShowAll() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String formattedManufactureDate = manufacturingDate.format(formatter);
        String formattedExpiryDate = expirationDate.format(formatter);

        return String.format("| %-6s | %-6s | %-8s | %-10s | %-10s | %-10.2f | %-12d | %-12d | %-6d |",
                productCode,
                productName,
                category,
                formattedManufactureDate,
                formattedExpiryDate,
                price,
                ImportedQuantity, // Assuming this method exists
                ExportedQuantity, // Assuming this method exists
                ImportedQuantity+ExportedQuantity// This is probably (imported - exported)
        );
    }


}
