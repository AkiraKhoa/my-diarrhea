package controller;

import model.Product;
import model.ImportReceipt;
import model.ExportReceipt;
import java.io.IOException;
import java.util.List;
import model.Storable;


public class AppController implements Storable  {
    // Implement methods to handle user interactions and application logic here
    public void addProduct(Product product) {
        // Implement logic to add a product to the collection
        // Check for duplicate product code and validate input
    }
    public boolean updateProduct(String productCode, Product updatedProduct) {
        // Implement logic to update product information
        // Check if the product code exists and validate updated information
        // Return true if the update is successful, false otherwise
    }
    public void showAllProducts() {
        // Implement logic to retrieve and display all products/items in the store
    }
    public ImportReceipt createImportReceipt() {
        // Implement logic to create an import receipt
        // Generate a unique import code, record timestamp, and add products to the receipt
        // Return the created import receipt
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

    @Override
    public List loadAll(String filePath) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveAll(List items, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
