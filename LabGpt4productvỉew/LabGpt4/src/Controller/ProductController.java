package Controller;

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

public class ProductController {
    private Map<String, Product> products;
    private WarehouseController warehouseController;
    public ProductController(Map<String, Product> products) {
        this.products = products;
    }

    // Method to add a product
    public void addProduct() {
    // Existing code to add a product
    String code = Tool.validateAlphanumericString("Enter product code: ", "Invalid code. Only alphanumeric characters are allowed.");

    if (products.containsKey(code)) {
        System.out.println("Product code already exists!");
        return; // Exit method since the code already exists
    }

    String name = Tool.validateString("Enter product name: ", "Name cannot be empty.");
    String category = Tool.validateString("Enter product category: ", "Category cannot be empty.");
    LocalDate manufactureDate = Tool.validateNotFutureDate("Enter manufacture date (yyyy-mm-dd): ", "Date cannot be in the future.");
    LocalDate expiryDate = Tool.validateDateBefore("Enter expiry date (yyyy-mm-dd): ", manufactureDate, "Expiry date must be after manufacturing date.");
    double price = Tool.validateDouble("Enter product price: ", "Invalid price input.");

    products.put(code, new Product(code, name, category, manufactureDate, expiryDate, price));
    System.out.println("Product added successfully!");
}


    

    // Method to update a product
    public void updateProduct() {
        String code = Tool.validateExistInMap("Enter the product code to update: ", products, "Product code does not exist!");
        Product product = products.get(code);

        String newName = Tool.validateString("Enter the new product name: ", "Name cannot be empty.");
        String newCategory = Tool.validateString("Enter the new product category: ", "Category cannot be empty.");
        LocalDate newManufactureDate = Tool.validateNotFutureDate("Enter the new manufacture date (yyyy-mm-dd): ", "Date cannot be in the future.");
        LocalDate newExpiryDate = Tool.validateDateBefore("Enter the new expiry date (yyyy-mm-dd): ", newManufactureDate, "Expiry date must be after the manufacturing date.");
        double newPrice = Tool.validateDouble("Enter the new product price: ", "Invalid price input.");
        
        product.setProductName(newName);
        product.setCategory(newCategory);
        product.setManufacturingDate(newManufactureDate);
        product.setExpirationDate(newExpiryDate);
        product.setPrice(newPrice);
        
        System.out.println("Product updated successfully!");
    }


    // Method to delete a product
    public boolean deleteProduct() throws ProductNotFoundException {
    // Check if product code exists
    String code = Tool.validateExistInMap("Enter the product code to delete: ", products, "Product code does not exist!");

    // Check for import/export information using getReceiptsByProductCode (assuming it returns a List)
    List<Receipt> receipts = warehouseController.getReceiptsByProductCode(code);
    if (receipts != null && !receipts.isEmpty()) {
        System.out.println("Cannot delete this product. Import/export information has been generated.");
        return false;
    }
    // Ask for confirmation before deleting
    boolean confirm = Tool.validateYesOrNo("Are you sure you want to delete this product?");
    if (!confirm) {
        System.out.println("Product deletion cancelled.");
        return false;
    }
    // Delete the product
    products.remove(code);
    System.out.println("Product deleted successfully!");
    return true;
}

    public Product getProductByCode(String code) throws ProductNotFoundException {
    if (!products.containsKey(code)) {
        throw new ProductNotFoundException("Product with code " + code + " not found.");
    }
    return products.get(code);
}

    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>(products.values());
        if (allProducts.isEmpty()) {
            System.out.println("No products are available.");
        }
        return allProducts;
    }
    
    public void displayAllProducts() {
    List<Product> allProducts = getAllProducts();
    if (!allProducts.isEmpty()) {
        System.out.println("List of all products:");
        for (Product product : allProducts) {
            System.out.println(product); // Assuming Product class has a meaningful toString() method.
        }
    }
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
    
    public void displayExpiredProducts() {
    try {
        List<Product> expiredProducts = getExpiredProducts();
        if (!expiredProducts.isEmpty()) {
            // Print header
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("                              List of Expired Products                                   ");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.format("| %-8s | %-20s | %-12s | %-12s | %-10s |", "CODE", "NAME", "MANUFACTURE", "EXPIRE", "QUANTITY");
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------");

            // Print products
            for (Product product : expiredProducts) {
                System.out.println(product.toString());  // Use existing toString() method
            }
            
            System.out.println("-----------------------------------------------------------------------------------------");
        }
    } catch (ProductNotFoundException e) {
        System.out.println(e.getMessage());
    }

    // Return to the main screen (if you have one)
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
