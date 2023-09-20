package View;

import Controller.ImportExportController;
import Controller.ProductController;
import Controller.WarehouseController;
import Model.ExportReceipt;
import Model.ImportReceipt;
import Model.Product;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;

public class MainView {
    public static void main(String[] args) {
        Map<String, Product> products = new HashMap<>();
        HashMap<String, ImportReceipt> importReceipts = new HashMap<>();
        HashMap<String, ExportReceipt> exportReceipts = new HashMap<>();

        ProductController productController = new ProductController(products);
        WarehouseController warehouseController = new WarehouseController(products, importReceipts, exportReceipts);
        ImportExportController importExportController = new ImportExportController(warehouseController, products, importReceipts, exportReceipts);
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Create Import Receipt");
            System.out.println("2. Create Export Receipt");
            System.out.println("3. Add Item to Import Receipt");
            System.out.println("4. Add Item to Export Receipt");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            String code, productCode;
            int quantity;

            switch (choice) {
                case 1:
                    System.out.print("Enter import receipt code: ");
                    code = scanner.nextLine();
                    importExportController.createImportReceipt(code);
                    break;
                case 2:
                    System.out.print("Enter export receipt code: ");
                    code = scanner.nextLine();
                    importExportController.createExportReceipt(code);
                    break;
                case 3:
                    System.out.print("Enter import receipt code: ");
                    code = scanner.nextLine();
                    System.out.print("Enter product code: ");
                    productCode = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    importExportController.addItemToImportReceipt(code, productCode, quantity);
                    break;
                case 4:
                    System.out.print("Enter export receipt code: ");
                    code = scanner.nextLine();
                    System.out.print("Enter product code: ");
                    productCode = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    importExportController.addItemToExportReceipt(code, productCode, quantity);
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
