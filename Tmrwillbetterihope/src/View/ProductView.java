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

    public void start() throws ProductNotFoundException {
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
            int option = Tool.validateIntRange("Choose an option: ", 1, 5, "Invalid option. Try again.");

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
System.out.println("+--------+--------+----------+------------+------------+------------+--------------+--------------+--------+");
System.out.println("|  Code  |  Name  |Category  |Manufacture |  Expiry    | Price      |Imported Qty. |Exported Qty. |  Qty.  |");
System.out.println("+--------+--------+----------+------------+------------+------------+--------------+--------------+--------+");

for (Product product : allProducts) {
    // Use the product's toStringShowAll method to display its details
    System.out.println(product.toStringShowAll());
}
    System.out.println("+--------+--------+----------+------------+------------+------------+--------------+--------------+--------+");
}

    
    public void addProduct() {
    String code = Tool.validateAlphanumericString("Enter product code: ", "Invalid code. Only alphanumeric characters are allowed.");

    // Check for code uniqueness immediately after input
    if (productController.checkProductExists(code)) {
        System.out.println("Product code already exists!, Failed to add product");
        return;
    }

    String name = Tool.validateAlphanumericString("Enter product name: ", "Name cannot be empty and don't have special characters");
    double price = Tool.validateDouble("Enter product price: ", "What did you just type?", "You can't give customers money for buying something! :((( T.T T.T ");    
    // Ask user to select a category
    int categoryChoice = Tool.validateIntRange("Choose product category (0 for Daily, 1 for Longshelf): ", 0, 1, "Invalid choice.");
    String category = categoryChoice == 0 ? "Daily" : "L.shelf";
        
    LocalDate manufactureDate;
    LocalDate expiryDate;
    
    if (category.equals("Daily")) {
        manufactureDate = Tool.validateNotFutureDate("Enter manufacture date (dd/mm/yyyy): ", "Date cannot be in the future.");
        expiryDate = manufactureDate.plusDays(1);
    } else {
        manufactureDate = Tool.validateNotFutureDate("Enter manufacture date (dd/mm/yyyy): ", "Date cannot be in the future.");
        expiryDate = Tool.validateDateAfter("Enter expiry date (dd/mm/yyyy): ", manufactureDate, "Expiry date must be after manufacturing date.");
    }
    productController.addProduct(code, name, category, manufactureDate, expiryDate, price);
    System.out.println("Product added successfully!");
}


    
    
//                  GOOD IN DATE
//    String newManufactureDateInput = Tool.checkNull("Enter the new manufacture date (d/M/yyyy) (press Enter to keep unchanged): ");
//    LocalDate newManufactureDate = Tool.validateNotFutureDateUpdate(newManufactureDateInput, "Enter the new manufacture date (d/M/yyyy): ", "Date cannot be in the future.");
//
//    String newExpiryDateInput = Tool.checkNull("Enter the new expiry date (d/M/yyyy) (press Enter to keep unchanged): ");
//    LocalDate newExpiryDate;
//    if (newManufactureDate != null) {
//        newExpiryDate = Tool.validateDateAfterUpdate(newExpiryDateInput, newManufactureDate, "Enter the new expiry date (d/M/yyyy): ", "Expiry date must be after the manufacturing date.");
//    } else {
//        newExpiryDate = Tool.validateLocalDateUpdate(newExpiryDateInput, "Enter the new expiry date (d/M/yyyy): ", "Invalid date format. Please enter again.");
//    }
//
//    String newPriceInput = Tool.checkNull("Enter the new product price (press Enter to keep unchanged): ");
//    double newPrice = Tool.validateDoubleUpdate(newPriceInput, "Enter the new product price: ", "Invalid price. Please enter again.");
//
//    if (!productController.updateProduct(code, newName, newCategory, newManufactureDate, newExpiryDate, newPrice)) {
//        System.out.println("Product does not exist!");
//        return;
//    }
//    System.out.println("Product updated successfully!");
//}
    
    
/***
 * date và price nếu nhập đúng thì nhận luôn, enter thì phải nhập lại
 * @throws ProductNotFoundException 
 */
    public void updateProduct() throws ProductNotFoundException {;
    if (productController.getProductsMap().isEmpty()) {
        System.out.println("There are no products to update!");
        return;
    }

    String code = Tool.validateExistInMap("Enter the product code to update: ", productController.getProductsMap(), "Product code does not exist!");
    Product currentProduct = productController.getProductByCode(code);
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter new product name (hit enter to skip):");
    String newName = scanner.nextLine().trim();
    if (!newName.isEmpty()) {
        currentProduct.setProductName(code);
    }
    
    
    System.out.println("Enter new category (0 for Daily, 1 for L.shelf, hit enter to skip):");
    String newCategoryInput = scanner.nextLine().trim();
    boolean categoryChanged = false;
    if (!newCategoryInput.isEmpty()) {
        int categoryChoice = Integer.parseInt(newCategoryInput);
        if (categoryChoice == 0 && !currentProduct.getCategory().equals("Daily")) {
            currentProduct.setCategory("Daily");
            categoryChanged = true;
        } else if (categoryChoice == 1 && !currentProduct.getCategory().equals("L.shelf")) {
            currentProduct.setCategory("L.shelf");
            categoryChanged = true;
        }
    }

    System.out.println("Enter new manufacture date (d/M/yyyy format, hit enter to skip):");
    LocalDate newManufactureDate = Tool.getValidManufactureDate();
    if (newManufactureDate != null) {
        currentProduct.setManufacturingDate(newManufactureDate);
    }
    
    if (categoryChanged || newManufactureDate != null) {
        System.out.println("Enter new expiry date (d/M/yyyy format, you cannot skip this):");
    } else {
        System.out.println("Enter new expiry date (d/M/yyyy format, hit enter to skip):");
    }
    LocalDate newExpiryDate = Tool.getValidExpiryDate(newManufactureDate);
    if (newExpiryDate != null) {
        currentProduct.setExpirationDate(newExpiryDate);
    }
    
    System.out.println("Product updated successfully!");
}

////        System.out.print("Enter the new manufacture date (d/M/yyyy) (press Enter to keep unchanged): ");
////        String newManufactureDateInput = scanner.nextLine().trim();
////        LocalDate newManufactureDate = (newManufactureDateInput.isEmpty()) ? currentProduct.getManufacturingDate() : Tool.validateNotFutureDate("Re-enter the new manufacture date (yyyy-mm-dd): ", "Date cannot be in the future.");
////
////        System.out.print("Enter the new expiry date (d/M/yyyy) (press Enter to keep unchanged): ");
////        String newExpiryDateInput = scanner.nextLine().trim();
////        LocalDate newExpiryDate = (newExpiryDateInput.isEmpty()) ? currentProduct.getExpirationDate() : Tool.validateDateAfter("Re-enter the new expiry date (yyyy-mm-dd): ", newManufactureDate, "Expiry date must be after the manufacturing date.");
//
//        System.out.print("Enter the new product price (press Enter to keep unchanged): ");
//        String newPriceInput = scanner.nextLine().trim();
//        double newPrice;
//        if (newPriceInput.isEmpty()) {
//            newPrice = currentProduct.getPrice();
//        } else {
//            newPrice = Tool.validateDouble("Re-enter the new product price: ", "Invalid input.", "Price must be non-negative.");
//        }
//
//        if (!productController.updateProduct(code, newName, newCategory, newManufactureDate, newExpiryDate, newPrice)) {
//            System.out.println("Product does not exist!");
//            return;
//        }
//        System.out.println("Product updated successfully!");
//}
//    public void updateProduct() throws ProductNotFoundException {
//    if (productController.getProductsMap().isEmpty()) {
//        System.out.println("There are no products to update!");
//        return;
//    }
//
//    String code = Tool.validateExistInMap("Enter the product code to update: ", productController.getProductsMap(), "Product code does not exist!");
//    Product currentProduct = productController.getProductByCode(code);
//
//    System.out.print("Enter the new product name (press Enter to keep unchanged): ");
//    String newNameInput = scanner.nextLine().trim();
//    String newName = Tool.validateStringUpdate("Re-enter the new product name: ", "Name cannot be empty.", newNameInput);
//
//    System.out.print("Enter the new product category (press Enter to keep unchanged): ");
//    String newCategoryInput = scanner.nextLine().trim();
//    String newCategory = Tool.validateStringUpdate("Re-enter the new product category: ", "Category cannot be empty.", newCategoryInput);
//
//    System.out.print("Enter the new manufacture date (d/M/yyyy) (press Enter to keep unchanged): ");
//    String newManufactureDateInput = scanner.nextLine().trim();
//    LocalDate newManufactureDate = newManufactureDateInput.isEmpty() ? currentProduct.getManufacturingDate() : Tool.validateNotFutureDate("Re-enter the new manufacture date (yyyy-mm-dd): ", "Date cannot be in the future.");
//
//    System.out.print("Enter the new expiry date (d/M/yyyy) (press Enter to keep unchanged): ");
//    String newExpiryDateInput = scanner.nextLine().trim();
//    LocalDate newExpiryDate = newExpiryDateInput.isEmpty() ? currentProduct.getExpirationDate() : Tool.validateDateAfter("Re-enter the new expiry date (yyyy-mm-dd): ", newManufactureDate, "Expiry date must be after the manufacturing date.");
//
//    System.out.print("Enter the new product price (press Enter to keep unchanged): ");
//    String newPriceInput = scanner.nextLine().trim();
//    double newPrice;
//    if (newPriceInput.isEmpty()) {
//        newPrice = currentProduct.getPrice();
//    } else {
//        newPrice = Tool.validateDouble("Re-enter the new product price: ", "Invalid input.", "Price must be non-negative.");
//    }
//
//    if (!productController.updateProduct(code, newName, newCategory, newManufactureDate, newExpiryDate, newPrice)) {
//        System.out.println("Product does not exist!");
//        return;
//    }
//    System.out.println("Product updated successfully!");
//}
//    public void updateProduct() throws ProductNotFoundException {
//    String code = Tool.validateExistInMap("Enter the product code to update: ", productController.getProductsMap(), "Product code does not exist!");
//    Product currentProduct = productController.getProductByCode(code);
//    String newNameInput = Tool.checkNull("Enter the new product name (press Enter to keep unchanged): ");
//    String newName = newNameInput == null ? null : Tool.validateString(newNameInput, "Name cannot be empty.");
//
//    String newCategoryInput = Tool.checkNull("Enter the new product category (press Enter to keep unchanged): ");
//    String newCategory = newCategoryInput == null ? null : Tool.validateString(newCategoryInput, "Category cannot be empty.");
//
//    String newManufactureDateInput = Tool.checkNull("Enter the new manufacture date (d/M/yyyy) (press Enter to keep unchanged): ");
//    LocalDate newManufactureDate = newManufactureDateInput == null ? null : Tool.validateNotFutureDate(newManufactureDateInput, "Date cannot be in the future.");
//
//    System.out.print("Enter the new expiry date (d/M/yyyy) (press Enter to keep unchanged): ");
//    String newExpiryDateInput = scanner.nextLine().trim();
//    LocalDate newExpiryDate = newExpiryDateInput.isEmpty() ? currentProduct.getExpirationDate() : Tool.validateDateAfter("Re-enter the new expiry date (yyyy-mm-dd): ", newManufactureDate, "Expiry date must be after the manufacturing date.");
//
//    System.out.print("Enter the new product price (press Enter to keep unchanged): ");
//    String newPriceInput = scanner.nextLine().trim();
//    double newPrice;
//    if (newPriceInput.isEmpty()) {
//        newPrice = currentProduct.getPrice();
//    } else {
//        newPrice = Tool.validateDouble("Re-enter the new product price: ", "Invalid input.", "Price must be non-negative.");
//    }
//
//    if (!productController.updateProduct(code, newName, newCategory, newManufactureDate, newExpiryDate, newPrice)) {
//        System.out.println("Product does not exist!");
//        return;
//    }
//    System.out.println("Product updated successfully!");
//}



    public void deleteProduct() {
        String code = Tool.validateExistInMap("Enter the product code to delete: ", productController.getProductsMap(), "Product code does not exist!");
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
