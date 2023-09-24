package Controller;

import Data.DataSingleton;
import Model.Product;
import Model.Receipt;
import Tools.Tool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import Controller.WarehouseController;


public class ProductController {
    private Map<String, Product> products;
    public ProductController() {
        this.products = DataSingleton.getInstance().getProducts();
    }


    // Method to add a product
    public boolean addProduct(String code, String name, String category, LocalDate manufactureDate, LocalDate expiryDate, double price) {
        if (products.containsKey(code)) {
            return false;
        }
        products.put(code, new Product(code, name, category, manufactureDate, expiryDate, price));
        return true;
    }
    
    public boolean updateProduct(String code, String newName, String newCategory, LocalDate newManufactureDate, LocalDate newExpiryDate, double newPrice) {
        Product product = products.get(code);
        if (product == null) {
            return false;
        }
        product.setProductName(newName);
        product.setCategory(newCategory);
        product.setManufacturingDate(newManufactureDate);
        product.setExpirationDate(newExpiryDate);
        product.setPrice(newPrice);
        return true;
    }
    
    public boolean deleteProduct(String code) throws ProductNotFoundException {
        WarehouseController warehouseController = new WarehouseController();
        List<Receipt> receipts = warehouseController.getReceiptsByProductCode(code);
        if (receipts != null && !receipts.isEmpty()) {
            return false;
        }
        products.remove(code);
        return true;
    }

    public Product getProductByCode(String code) throws ProductNotFoundException {
    if (!products.containsKey(code)) {
        throw new ProductNotFoundException("Product with code " + code + " not found.");
    }
    return products.get(code);
}

    public List<Product> getAllProducts() {
    return new ArrayList<>(products.values());
}
    public Map<String, Product> getProductsMap() {
    return products;
}
    public boolean checkProductExists(String code) {
        return products.containsKey(code);
    }



    public List<Product> getExpiredProducts() throws ProductNotFoundException {
        List<Product> expiredProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.isExpired()) {
                expiredProducts.add(product);
            }
        }
        if (expiredProducts.isEmpty()) {
            throw new ProductNotFoundException("No expired products found.");
        }
        return expiredProducts;
    }
    
    // Method to save products to file
    public boolean saveProductsToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(products);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Method to load products from file
    public boolean loadProductsFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            products = (HashMap<String, Product>) ois.readObject();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Additional methods can be added here
}
