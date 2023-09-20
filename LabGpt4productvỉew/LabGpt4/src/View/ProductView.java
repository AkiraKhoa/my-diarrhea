package View;

import Controller.ProductController;
import java.util.List;
import java.util.Scanner;

public class ProductView {

    private final ProductController productController;
    private final Scanner scanner;

    public ProductView(ProductController productController) {
        this.productController = productController;
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
                    listAllProducts();
                    break;
                case 2:
                    addNewProduct();
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

    private void listAllProducts() {
        // TODO: Call the method from ProductController to get all products and display them
    }

    private void addNewProduct() {
        // TODO: Capture user inputs and call the method from ProductController to add a new product
    }

    private void updateProduct() {
        // TODO: Capture user inputs and call the method from ProductController to update a product
    }

    private void deleteProduct() {
        // TODO: Capture user inputs and call the method from ProductController to delete a product
    }
}
