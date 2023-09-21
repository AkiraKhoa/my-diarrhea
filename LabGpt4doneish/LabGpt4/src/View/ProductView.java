package View;

import Controller.ProductController;
import Controller.ProductNotFoundException;
import Model.Product;
import Tools.Tool;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductView {
    private final Scanner scanner;
    ProductController productController = new ProductController();
    

    public ProductView() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("-----------------------------------------------------");
            System.out.println("                    Product Menu                     ");
            System.out.println("-----------------------------------------------------");
            System.out.println("1. List All Products");
            System.out.println("2. Add New Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (option) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public void displayAllProducts() {
    ProductController productController = new ProductController();
    List<Product> allProducts = productController.getAllProducts();
    if (allProducts.isEmpty()) {
        System.out.println("No products are available.");
        return;
    }

    System.out.println("List of all products:");
    // You can use formatting or even ASCII table for better visual representation
    System.out.println("+--------+--------+--------+------------+------------+-------+");
    System.out.println("|  Code  |  Name  |Category|Manufacture |  Expiry    | Price |");
    System.out.println("+--------+--------+--------+------------+------------+-------+");

    for (Product product : allProducts) {
        // Assuming Product class has getters for these fields
        System.out.printf("| %-6s | %-6s | %-6s | %-10s | %-10s | %-5.2f|%n",
            product.getProductCode(),
            product.getProductName(),
            product.getCategory(),
            product.getManufacturingDate(),
            product.getExpirationDate(),
            product.getPrice()
        );
    }

    System.out.println("+--------+--------+--------+------------+------------+-------+");
}

    
    public void addProduct() {
        String code = Tool.validateAlphanumericString("Enter product code: ", "Invalid code. Only alphanumeric characters are allowed.");
        String name = Tool.validateString("Enter product name: ", "Name cannot be empty.");
        String category = Tool.validateString("Enter product category: ", "Category cannot be empty.");
        LocalDate manufactureDate = Tool.validateNotFutureDate("Enter manufacture date (yyyy-mm-dd): ", "Date cannot be in the future.");
        LocalDate expiryDate = Tool.validateDateBefore("Enter expiry date (yyyy-mm-dd): ", manufactureDate, "Expiry date must be after manufacturing date.");
        double price = Tool.validateDouble("Enter product price: ", "Invalid price input.");
        if (!productController.addProduct(code, name, category, manufactureDate, expiryDate, price)) {
            System.out.println("Product code already exists!");
            return;
        }
        System.out.println("Product added successfully!");
    }

    public void updateProduct() {
        String code = Tool.validateExistInMap("Enter the product code to update: ", (Map<String, ?>) productController.getAllProducts(), "Product code does not exist!");

        String newName = Tool.validateString("Enter the new product name: ", "Name cannot be empty.");
        String newCategory = Tool.validateString("Enter the new product category: ", "Category cannot be empty.");
        LocalDate newManufactureDate = Tool.validateNotFutureDate("Enter the new manufacture date (yyyy-mm-dd): ", "Date cannot be in the future.");
        LocalDate newExpiryDate = Tool.validateDateBefore("Enter the new expiry date (yyyy-mm-dd): ", newManufactureDate, "Expiry date must be after the manufacturing date.");
        double newPrice = Tool.validateDouble("Enter the new product price: ", "Invalid price input.");

        if (!productController.updateProduct(code, newName, newCategory, newManufactureDate, newExpiryDate, newPrice)) {
            System.out.println("Product does not exist!");
            return;
        }
        System.out.println("Product updated successfully!");
    }

    public void deleteProduct() {
        String code = Tool.validateExistInMap("Enter the product code to delete: ", (Map<String, ?>) productController.getAllProducts(), "Product code does not exist!");
        try {
            if (!productController.deleteProduct(code)) {
                System.out.println("Cannot delete this product. Import/export information has been generated.");
                return;
            }
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
            System.out.println("Product not found!");
            return;
        }

        boolean confirm = Tool.validateYesOrNo("Are you sure you want to delete this product?");
        if (!confirm) {
            System.out.println("Product deletion cancelled.");
            return;
        }
        System.out.println("Product deleted successfully!");
    }
}
