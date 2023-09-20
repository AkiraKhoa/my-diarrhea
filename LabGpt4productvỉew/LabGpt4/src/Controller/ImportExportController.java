package Controller;

import Model.ImportReceipt;
import Model.ExportReceipt;
import Controller.ProductNotFoundException;
import Model.Product;
import Tools.Tool;
import java.util.HashMap;
import java.util.Map;


public class ImportExportController {
    private WarehouseController warehouseController;  // Using WarehouseController instead of WarehouseManager
    private HashMap<String, ImportReceipt> importReceipts = new HashMap<>();
    private HashMap<String, ExportReceipt> exportReceipts = new HashMap<>();
    private Map<String, Product> products;
    private int importCodeCounter = 0;
    private int exportCodeCounter = 0;

    public ImportExportController(WarehouseController warehouseController, 
                                  Map<String, Product> products, 
                                  HashMap<String, ImportReceipt> importReceipts,
                                  HashMap<String, ExportReceipt> exportReceipts) {
        this.warehouseController = warehouseController;
        this.products = products;
        this.importReceipts = importReceipts;
        this.exportReceipts = exportReceipts;
    }
    public String generateImportCode() {
        importCodeCounter++;
        return "I" + String.format("%06d", importCodeCounter);
    }

    public String generateExportCode() {
        exportCodeCounter++;
        return "E" + String.format("%06d", exportCodeCounter);
    }
    public void createAndManageImportReceipt() throws ProductNotFoundException {
    if (products.isEmpty()) {
        throw new ProductNotFoundException("No products available for import.");
    }

    String code = generateImportCode();
    ImportReceipt newImportReceipt = new ImportReceipt(code);
    importReceipts.put(code, newImportReceipt);

    System.out.println("Import receipt created successfully. Receipt code: " + code);

    while (true) {
        // Submenu to add products
        String productCode = Tool.validateExistInMap("Enter the product code to add: ", products, "Product code does not exist!");
        int quantity = Tool.validateInt("Enter the quantity: ", "Invalid quantity.");

        if (productCode != null) {
            addItemToImportReceipt(code, productCode, quantity);
        } else {
            System.out.println("Failed to add item to import receipt.");
        }

        String continueChoice = Tool.validateString("Would you like to add more products? (yes/no): ", "Invalid input.");
        if ("no".equalsIgnoreCase(continueChoice)) {
            break;
        }
    }

    // Show confirmation message here
    System.out.println("Import receipt confirmed.");
    // Logic to display the import receipt or save it could go here
    
    // Return to the main screen
}
    
    public void createAndManageExportReceipt() throws ProductNotFoundException {
    if (products.isEmpty()) {
        throw new ProductNotFoundException("No products available for export.");
    }

    String code = generateExportCode();
    ExportReceipt newExportReceipt = new ExportReceipt(code);
    exportReceipts.put(code, newExportReceipt);
    System.out.println("Export receipt created successfully. Receipt code: " + code);

    while (true) {
        // Submenu to add products
        String productCode = Tool.validateExistInMap("Enter the product code to add: ", products, "Product code does not exist!");
        int quantity = Tool.validateInt("Enter the quantity: ", "Invalid quantity.");

        if (productCode != null) {
            addItemToExportReceipt(code, productCode, quantity);
        } else {
            System.out.println("Failed to add item to export receipt.");
        }

        String continueChoice = Tool.validateString("Would you like to add more products? (yes/no): ", "Invalid input.");
        if ("no".equalsIgnoreCase(continueChoice)) {
            break;
        }
    }

    // Show confirmation message here
    System.out.println("Export receipt confirmed.");
    // Logic to display the export receipt or save it could go here

    // Return to the main screen
}


    public void addItemToImportReceipt(String receiptCode, String productCode, int quantity) throws ProductNotFoundException {
       ImportReceipt receipt = importReceipts.get(receiptCode);
       if (receipt != null && products.containsKey(productCode)) {
           Product product = products.get(productCode);
           product.setImportedQuantity(product.getImportedQuantity() + quantity);
           System.out.println("Item added to import receipt and product quantity updated.");
       } else {
           System.out.println("Failed to add item to import receipt.");
       }
   }
    public void addItemToExportReceipt(String receiptCode, String productCode, int quantity) throws ProductNotFoundException {
    ExportReceipt receipt = exportReceipts.get(receiptCode);
    if (receipt != null && products.containsKey(productCode)) {
        Product product = products.get(productCode);
        if (product.getImportedQuantity() >= quantity) {
            product.setImportedQuantity(product.getImportedQuantity() - quantity);
            System.out.println("Item added to export receipt and product quantity updated.");
        } else {
            System.out.println("Insufficient quantity in stock.");
        }
    } else {
        System.out.println("Failed to add item to export receipt.");
    }
}


    // Additional methods can be added here
}
