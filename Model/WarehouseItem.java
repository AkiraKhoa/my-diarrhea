package model;

import java.io.Serializable;
import java.util.Date;

public class WarehouseItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String importExportCode;
    private Date timestamp;
    private Product product;
    private int quantity;

    // Constructor, getters, setters, and validation methods here
    // Getters and setters
    public String getImportExportCode() {
        return importExportCode;
    }

    public WarehouseItem(String importExportCode, Date timestamp, Product product, int quantity) {
        this.importExportCode = importExportCode;
        this.timestamp = timestamp;
        this.product = product;
        this.quantity = quantity;
    }
    

    public void setImportExportCode(String importExportCode) {
        this.importExportCode = importExportCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Validation methods (you can add validation logic here)
    public boolean isValidWarehouseItem() {
        // Implement validation logic, e.g., check for valid import/export code, quantity, etc.
        return true; // Return true if the warehouse item is valid, false otherwise
    }

    @Override
    public String toString() {
        // Customize the toString method to display warehouse item information
        return "Import/Export Code: " + importExportCode +
               "\nTimestamp: " + timestamp +
               "\nProduct Information:\n" + product.toString() +
               "\nQuantity: " + quantity;
    }
}
