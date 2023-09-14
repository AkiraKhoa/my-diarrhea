package controller;

import model.Product;
import model.ImportReceipt;
import model.ExportReceipt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Storable;
import model.WarehouseItem;


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
        // Check for duplicate import code (you need to implement this)
        if (!isImportCodeDuplicate(receipt.getImportExportCode())) {
            importReceipts.add(receipt);
            System.out.println("Import receipt confirmed successfully.");
            return true;
        } else {
            System.out.println("Import code already exists. Cannot confirm duplicate receipt.");
            return false;
        }
    }

    public ExportReceipt createExportReceipt() {
        // Generate a unique export code (you need to implement this)
        String exportCode = generateUniqueExportCode();
        Date timestamp = new Date(); // Current timestamp

        // Create an export receipt and add products to it
        ExportReceipt exportReceipt = new ExportReceipt(exportCode, timestamp, new ArrayList<>());

        // Add products to the export receipt (you need to implement this)
        // You might want to interact with the user to add products to the receipt

        return exportReceipt;
    }

    public boolean confirmExportReceipt(ExportReceipt receipt) {
        // Check for duplicate export code (you need to implement this)
        if (!isExportCodeDuplicate(receipt.getImportExportCode())) {
            exportReceipts.add(receipt);
            System.out.println("Export receipt confirmed successfully.");
            return true;
        } else {
            System.out.println("Export code already exists. Cannot confirm duplicate receipt.");
            return false;
        }
    }

    public void displayExpiredProducts() {
        Date currentDate = new Date();
        for (Product product : products) {
            if (product.getExpirationDate().before(currentDate)) {
                System.out.println(product.toString());
            }
        }
    }

    public void displaySellingProducts() {
        for (Product product : products) {
            if (product.getQuantity() > 0 && !product.getManufacturingDate().after(product.getExpirationDate())) {
                System.out.println(product.toString());
            }
        }
    }
    
    public void displayLowStockProducts() {
        products.sort((p1, p2) -> Integer.compare(p1.getQuantity(), p2.getQuantity()));
        for (Product product : products) {
            if (product.getQuantity() <= 3) {
                System.out.println(product.toString());
            }
        }
    }

    public void displayImportExportReceipts(String productCode) {
        // Find the product by product code
        Product product = getProductByCode(productCode);

        if (product != null) {
            for (ImportReceipt importReceipt : importReceipts) {
                for (WarehouseItem item : importReceipt.getItems()) {
                    if (item.getProduct().getProductCode().equals(productCode)) {
                        System.out.println(importReceipt.toString());
                        break;
                    }
                }
            }

            for (ExportReceipt exportReceipt : exportReceipts) {
                for (WarehouseItem item : exportReceipt.getItems()) {
                    if (item.getProduct().getProductCode().equals(productCode)) {
                        System.out.println(exportReceipt.toString());
                        break;
                    }
                }
            }
        } else {
            System.out.println("Product code does not exist");
        }
    }

    public void storeData() {
        // Save product data to file
        try {
            Storable<Product> productStorable = new Product();
            productStorable.saveAll(products, "product.dat");
            System.out.println("Product data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving product data: " + e.getMessage());
        }

        // Save import receipt data to file
        try {
            Storable<ImportReceipt> importReceiptStorable = new ImportReceipt();
            importReceiptStorable.saveAll(importReceipts, "importReceipt.dat");
            System.out.println("Import receipt data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving import receipt data: " + e.getMessage());
        }

        // Save export receipt data to file
        try {
            Storable<ExportReceipt> exportReceiptStorable = new ExportReceipt();
            exportReceiptStorable.saveAll(exportReceipts, "exportReceipt.dat");
            System.out.println("Export receipt data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving export receipt data: " + e.getMessage());
        }
    }
    
    private boolean isProductCodeDuplicate(String productCode) {
        for (Product product : products) {
            if (product.getProductCode().equals(productCode)) {
                return true;
            }
        }
        return false;
    }

    private boolean isImportCodeDuplicate(String importCode) {
        for (ImportReceipt receipt : importReceipts) {
            if (receipt.getImportExportCode().equals(importCode)) {
                return true;
            }
        }
        return false;
    }

    private boolean isExportCodeDuplicate(String exportCode) {
        for (ExportReceipt receipt : exportReceipts) {
            if (receipt.getImportExportCode().equals(exportCode)) {
                return true;
            }
        }
        return false;
    }

    private Product getProductByCode(String productCode) {
        for (Product product : products) {
            if (product.getProductCode().equals(productCode)) {
                return product;
            }
        }
        return null;
    }

    private String generateUniqueImportCode() {
        // Logic to generate a unique import code
        // You can implement your own logic for generating a unique code
        // Example: return a combination of timestamp and a random number
        return "IMP" + System.currentTimeMillis() + (int) (Math.random() * 1000);
    }

    private String generateUniqueExportCode() {
        // Logic to generate a unique export code
        // You can implement your own logic for generating a unique code
        // Example: return a combination of timestamp and a random number
        return "EXP" + System.currentTimeMillis() + (int) (Math.random() * 1000);
    }
}
