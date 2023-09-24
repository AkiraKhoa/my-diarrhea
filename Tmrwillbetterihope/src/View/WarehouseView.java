package View;

import Controller.ImportExportController;
import Controller.ProductController;
import Controller.ProductNotFoundException;
import Controller.WarehouseController;
import Data.DataSingleton;
import Model.ExportReceipt;
import Model.ImportReceipt;
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

    public void start() throws ProductNotFoundException {
        while (true) {
            System.out.println("-----------------------------------------------------");
            System.out.println("                   Warehouse Menu                    ");
            System.out.println("-----------------------------------------------------");
            System.out.println("1. Manage Import Receipt");
            System.out.println("2. Manage Export Receipt");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int option = Tool.validateIntRange("Choose an option: ", 1, 3, "Invalid option. Try again.");

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
        if (products.isEmpty()) {
                        System.out.println("There are no products available to import.");
                        return; 
                    }
        try {
            String receiptCode = importExportController.generateImportCode();
            ImportReceipt newReceipt = new ImportReceipt(receiptCode);
            System.out.println("Import receipt created successfully. Receipt code: " + receiptCode);
            importExportController.addImportReceiptToMap(newReceipt);

            while (true) {
                String productCode = Tool.validateExistInMap("Enter the product code to import: ", products, "Product code does not exist!");
                int quantity = Tool.validateInt("Enter the quantity: ", "Invalid quantity.");

                boolean itemAdded = importExportController.addItemToImportReceipt(receiptCode, productCode, quantity);
                if (itemAdded) {
                    System.out.println("Item added to import receipt and product quantity updated.");
                } else {
                    System.out.println("Failed to add item to import receipt.");
                }

                boolean confirm = Tool.validateYesOrNo("Would you like to import more products?");
                if (!confirm) {
                    break;
                }
            }
            System.out.println("Import receipt confirmed.");
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
//    public void createAndManageExportReceipt() {
//        if (products.isEmpty()) {
//                    System.out.println("There are no products available to import.");
//                    return; 
//                }
//        try {
//            String receiptCode = importExportController.generateExportCode();
//            ExportReceipt newReceipt = new ExportReceipt(receiptCode);
//            importExportController.addExportReceiptToMap(newReceipt);
//            System.out.println("Export receipt created successfully. Receipt code: " + receiptCode);
//            while (true) { 
//                
//                String productCode = Tool.validateExistInMap("Enter the product code to export: ", products, "Product code does not exist!");
//                int quantity = Tool.validateInt("Enter the quantity: ", "Invalid quantity.");
//                if (importExportController.addItemToExportReceipt(receiptCode, productCode, quantity)) {
//                    System.out.println("Item added to export receipt and product quantity updated.");
//                } else {
//                    System.out.println("Insufficient quantity in stock.");
//                }
//
//                String continueChoice = Tool.validateString("Would you like to export more products? (yes/no): ", "Invalid input.");
//                if ("no".equalsIgnoreCase(continueChoice)) {
//                    break;
//                }
//            }
//            
//            System.out.println("Export receipt confirmed.");
//        } catch (ProductNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//    }
    
    public void createAndManageExportReceipt() throws ProductNotFoundException {
    if (products.isEmpty()) {
        System.out.println("There are no products available to export.");
        return;
    }
    
    String receiptCode = importExportController.generateExportCode(true);
    ExportReceipt newReceipt = new ExportReceipt(receiptCode);

    // Add the receipt to the map first
    importExportController.addExportReceiptToMap(newReceipt);

    boolean itemAddedFlag = false; // A flag to check if at least one item was added

    while (true) {
        String productCode = Tool.validateExistInMap("Enter the product code to export: ", products, "Product code does not exist!");

        Product product = products.get(productCode);
        if (product.getImportedQuantity() == 0) {
            System.out.println("Warning: The imported quantity of this product is 0. You can't export it.");
            
            boolean confirm1 = Tool.validateYesOrNo("Would you like to export more products?");
            if (!confirm1) {
                break;
            }
            continue; // move to the next iteration of the loop
        } 

        int quantity = Tool.validateInt("Enter the quantity: (Current imported quantity for product " + productCode + ": " + product.getImportedQuantity() + "): ","Invalid quantity.");

        if (product.getImportedQuantity() < quantity) {
            System.out.println("Export quantity exceeds available stock. Current imported quantity for product " + productCode + ": " + product.getImportedQuantity());
        } else {
            boolean itemAdded = importExportController.addItemToExportReceipt(receiptCode, productCode, quantity);
            if (itemAdded) {
                product.setExportedQuantity(product.getExportedQuantity() + quantity);
                product.setImportedQuantity(product.getImportedQuantity() - quantity);
                System.out.println("Item added to export receipt and product quantity updated.");
                itemAddedFlag = true; // Set the flag as true since an item was added
            } else {
                System.out.println("Failed to add item to export receipt.");
            }
        }
        boolean confirm2 = Tool.validateYesOrNo("Would you like to export more products?");
        if (!confirm2) {
            break;
        }
    }
    
    if (!itemAddedFlag) {
        // No items were added. Remove the receipt and decrease the receipt code counter.
        importExportController.removeExportReceiptFromMap(receiptCode);
        importExportController.generateExportCode(false);
        System.out.println("Failed to create export receipt since no items were added.");
    } else {
        System.out.println("Export receipt confirmed.");
    }
}




}
