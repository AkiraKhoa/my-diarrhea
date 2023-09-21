package View;

import Controller.ImportExportController;
import Controller.ProductController;
import Controller.ProductNotFoundException;
import Controller.WarehouseController;
import Data.DataSingleton;
import Model.Product;
import Model.Receipt;
import Tools.Tool;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class WarehouseView {
    private Map<String, Product> products;
    ImportExportController importExportController = new ImportExportController();

    
    
    public WarehouseView() {
        this.products = DataSingleton.getInstance().getProducts();
    }

    public void start() {
        while (true) {
            System.out.println("-----------------------------------------------------");
            System.out.println("                   Warehouse Menu                    ");
            System.out.println("-----------------------------------------------------");
            System.out.println("1. Manage Import Receipt");
            System.out.println("2. Manage Export Receipt");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int option = Tool.validateInt("Choose an option: ", "Invalid option. Try again.");

            switch (option) {
                case 1:
                    createAndManageImportReceipt();
                    break;
                case 2:
                    createAndManageExportReceipt();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    
    
    public void createAndManageImportReceipt() {
    try {
        String receiptCode = importExportController.generateImportCode();
        System.out.println("Import receipt created successfully. Receipt code: " + receiptCode);

        while (true) {
            String productCode = Tool.validateExistInMap("Enter the product code to add: ", products, "Product code does not exist!");
            int quantity = Tool.validateInt("Enter the quantity: ", "Invalid quantity.");
            
            boolean itemAdded = importExportController.addItemToImportReceipt(receiptCode, productCode, quantity);
            if (itemAdded) {
                System.out.println("Item added to import receipt and product quantity updated.");
            } else {
                System.out.println("Failed to add item to import receipt.");
            }

            String continueChoice = Tool.validateString("Would you like to add more products? (yes/no): ", "Invalid input.");
            if ("no".equalsIgnoreCase(continueChoice)) {
                break;
            }
        }
        System.out.println("Import receipt confirmed.");
    } catch (ProductNotFoundException e) {
        System.out.println(e.getMessage());
    }
}
    public void createAndManageExportReceipt() {
        try {
            String code = importExportController.createExportReceipt();
            System.out.println("Export receipt created successfully. Receipt code: " + code);
            while (true) {
                String productCode = Tool.validateExistInMap("Enter the product code to add: ", products, "Product code does not exist!");
                int quantity = Tool.validateInt("Enter the quantity: ", "Invalid quantity.");
                
                if (importExportController.addItemToExportReceipt(code, productCode, quantity)) {
                    System.out.println("Item added to export receipt and product quantity updated.");
                } else {
                    System.out.println("Insufficient quantity in stock.");
                }

                String continueChoice = Tool.validateString("Would you like to add more products? (yes/no): ", "Invalid input.");
                if ("no".equalsIgnoreCase(continueChoice)) {
                    break;
                }
            }
            
            System.out.println("Export receipt confirmed.");
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
