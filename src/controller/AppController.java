package controller;

import model.Product;
import model.ImportReceipt;
import model.ExportReceipt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Storable;


public class AppController  {
    // Implement methods to handle user interactions and application logic here
    private List<Product> products = new ArrayList<>();
    private List<ImportReceipt> importReceipts = new ArrayList<>();
    private List<ExportReceipt> exportReceipts = new ArrayList<>();
    
    public void addProduct(Product product) {
        if (product.isValidProduct()) {
            // Check for duplicate product code
            if (!isProductCodeDuplicate(product.getProductCode())) {
                products.add(product);
                System.out.println("Product added successfully.");
            } else {
                System.out.println("Product code already exists. Cannot add duplicate.");
            }
        } else {
            System.out.println("Invalid product information. Product not added.");
        }
    }
    public boolean updateProduct(String productCode, Product updatedProduct) {
        Product existingProduct = getProductByCode(productCode);

        if (existingProduct != null) {
            // Check if the updated product is valid
            if (updatedProduct.isValidProduct()) {
                // Update the existing product
                existingProduct.setProductName(updatedProduct.getProductName());
                existingProduct.setManufacturingDate(updatedProduct.getManufacturingDate());
                existingProduct.setExpirationDate(updatedProduct.getExpirationDate());
                existingProduct.setPrice(updatedProduct.getPrice());
                existingProduct.setQuantity(updatedProduct.getQuantity());
                System.out.println("Product updated successfully.");
                return true;
            } else {
                System.out.println("Invalid updated product information. Product not updated.");
            }
        } else {
            System.out.println("Product code does not exist. Product not updated.");
        }
        return false;
    }

    public void showAllProducts() {
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }
    }

    public ImportReceipt createImportReceipt() {
        // Generate a unique import code (you need to implement this)
        String importCode = generateUniqueImportCode();
        Date timestamp = new Date(); // Current timestamp

        // Create an import receipt and add products to it
        ImportReceipt importReceipt = new ImportReceipt(importCode, timestamp, new ArrayList<>());

        // Add products to the import receipt (you need to implement this)
        // You might want to interact with the user to add products to the receipt

        return importReceipt;
    }
    public boolean confirmImportReceipt(ImportReceipt receipt) {
        // Implement logic to confirm the import receipt
        // Check for duplicate import code and validate product information
        // Return true if the confirmation is successful, false otherwise
    }
    public ExportReceipt createExportReceipt() {
        // Implement logic to create an export receipt
        // Generate a unique export code, record timestamp, and add products to the receipt
        // Return the created export receipt
    }
    public boolean confirmExportReceipt(ExportReceipt receipt) {
        // Implement logic to confirm the export receipt
        // Check for duplicate export code and validate product information
        // Return true if the confirmation is successful, false otherwise
    }
    public void displayExpiredProducts() {
        // Implement logic to find and display products that have expired
    }
    public void displaySellingProducts() {
        // Implement logic to find and display products that the store is selling
    }
    public void displayLowStockProducts() {
        // Implement logic to find, sort, and display products that are running out of stock
    }
    public void displayImportExportReceipts(String productCode) {
        // Implement logic to search for a product by its code and display its import/export receipts
    }
    public void storeData(List<Product> products, List<ImportReceipt> importReceipts, List<ExportReceipt> exportReceipts) {
        // Implement logic to save product and warehouse information to files
        // You can use the Storable interface methods for file I/O
        try {
            Storable<Product> productStorable = new Product();
            Storable<ImportReceipt> importReceiptStorable = new ImportReceipt();
            Storable<ExportReceipt> exportReceiptStorable = new ExportReceipt();

            productStorable.saveAll(products, "product.dat");
            importReceiptStorable.saveAll(importReceipts, "warehouse.dat");
            exportReceiptStorable.saveAll(exportReceipts, "warehouse.dat");
        } catch (IOException e) {
            // Handle file I/O errors
            e.printStackTrace();
        }
    }

}
