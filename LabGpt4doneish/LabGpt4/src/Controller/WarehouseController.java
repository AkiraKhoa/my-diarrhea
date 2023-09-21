package Controller;
import Controller.ProductNotFoundException;
import Data.DataSingleton;
import Model.ExportReceipt;
import Model.ImportReceipt;
import Model.Product;
import Model.Receipt;
import Tools.Tool;
import java.util.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WarehouseController {
    private Map<String, ImportReceipt> importReceipts;
    private Map<String, ExportReceipt> exportReceipts;
    private Map<String, Product> products;
    public WarehouseController() {
        this.importReceipts = DataSingleton.getInstance().getImportReceipts();
        this.exportReceipts = DataSingleton.getInstance().getExportReceipts();
        this.products = DataSingleton.getInstance().getProducts();
    }
    
    public List<Receipt> getReceiptsByProductCode(String productCode) throws ProductNotFoundException {
        if (productCode == null || productCode.isEmpty()) {
            throw new IllegalArgumentException("Invalid product code.");
        }

        List<Receipt> receipts = new ArrayList<>();
        for (ImportReceipt receipt : importReceipts.values()) {
            if (receipt.containsProduct(productCode)) {
                receipts.add(receipt);
            }
        }
        for (ExportReceipt receipt : exportReceipts.values()) {
            if (receipt.containsProduct(productCode)) {
                receipts.add(receipt);
            }
        }

        return receipts;
    }
    
    public void displayProductData(String productCode) throws ProductNotFoundException {
    if (!products.containsKey(productCode)) {
        System.out.println("Product does not exist");
        return;
    }
    
    Product product = products.get(productCode);
    System.out.println("Product Data from warehouse.dat or warehouse's collection:");
    System.out.println(product.toString());  // Assuming you've implemented a good toString() in Product
    
    List<Receipt> relatedReceipts = getReceiptsByProductCode(productCode);
    if (relatedReceipts.isEmpty()) {
        System.out.println("No import/export receipts for this product.");
    } else {
        System.out.println("Related Receipts:");
        for (Receipt receipt : relatedReceipts) {
            System.out.println(receipt.toString());  // Assuming you've implemented a good toString() in Receipt
        }
    }
}

    
    
    public int getImportedQuantityForProduct(String productCode) {
        if (productCode == null || productCode.isEmpty()) {
        throw new IllegalArgumentException("Invalid product code.");
    }
        int importedQuantity = 0;
        for (ImportReceipt importReceipt : importReceipts.values()) {
            Map<String, Integer> products = importReceipt.getProducts();
            if (products.containsKey(productCode)) {
                importedQuantity += products.get(productCode);
            }
        }
        return importedQuantity;
    }
    
    // Method to get the exported quantity of a specific product
    public int getExportedQuantityForProduct(String productCode) {
        if (productCode == null || productCode.isEmpty()) {
        throw new IllegalArgumentException("Invalid product code.");
    }
        int exportedQuantity = 0;
        for (ExportReceipt exportReceipt : exportReceipts.values()) {
            Map<String, Integer> products = exportReceipt.getProducts();
            if (products.containsKey(productCode)) {
                exportedQuantity += products.get(productCode);
            }
        }
        return exportedQuantity;
    }
    
    public int getStockForProduct(String productCode) {
        int importedQuantity = getImportedQuantityForProduct(productCode);
        int exportedQuantity = getExportedQuantityForProduct(productCode);
        return importedQuantity - exportedQuantity;
    }
    
    public List<Product> getProductsForSale() {
        List<Product> productsForSale = new ArrayList<>();
        for (Product product : products.values()) {
            int stock = product.getImportedQuantity() + product.getExportedQuantity();
            if (!product.isExpired() && stock > 0) {
                productsForSale.add(product);
            }
        }
        return productsForSale;
    }
    
    // Method for checking inventory
    public void checkInventory() {
    try {
        String code = Tool.validateExistInMap("Enter the product code to check inventory: ", products, "Product code does not exist!");
        int stock = this.getStockForProduct(code);
        System.out.println("Current stock for product " + code + ": " + stock);
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}
    
    public List<Product> getLowStockProducts() {
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : products.values()) {
            int stock = product.getImportedQuantity() + product.getExportedQuantity();
            if (stock <= 3) {
                lowStockProducts.add(product);
            }
        }
        // Sort by quantity
        Collections.sort(lowStockProducts, Comparator.comparing(product -> 
            product.getImportedQuantity() + product.getExportedQuantity()));
        return lowStockProducts;
    }

    // Method to save warehouse data to file
    public boolean saveDataToFile(String fileName) {
    if (fileName == null || fileName.isEmpty()) {
        throw new IllegalArgumentException("Invalid file name.");
    }
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
        oos.writeObject(importReceipts);
        oos.writeObject(exportReceipts);
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        // Consider logging
        return false;
    }
}

    // Method to load warehouse data from file
    public boolean loadDataFromFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
        throw new IllegalArgumentException("Invalid file name.");
    }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            importReceipts = (Map<String, ImportReceipt>) (List<Receipt>) ois.readObject();
            exportReceipts = (Map<String, ExportReceipt>) (List<Receipt>) ois.readObject();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
    
    
    

