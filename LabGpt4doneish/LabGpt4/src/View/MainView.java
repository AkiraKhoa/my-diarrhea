package View;

import Controller.ImportExportController;
import Controller.ProductController;
import Controller.WarehouseController;
import Model.ExportReceipt;
import Model.ImportReceipt;
import Model.Product;
import Tools.Tool;
import java.util.Map;

public class MainView {

    private final ProductController productController;
    private final WarehouseController warehouseController;
    private final WarehouseView warehouseView;
    private final ProductView productView;
    private final ReportView reportView;  

    public MainView(ProductController productController, 
                    WarehouseController warehouseController, 
                    ImportExportController importExportController, 
                    Map<String, Product> products, 
                    Map<String, ImportReceipt> importReceipts, 
                    Map<String, ExportReceipt> exportReceipts) {
        this.productController = productController;
        this.warehouseController = warehouseController;

        // Now pass the required parameters to the constructors of WarehouseView and ReportView
        this.warehouseView = new WarehouseView(importExportController, productController, warehouseController, products);
        this.productView = new ProductView(productController);
        this.reportView = new ReportView(products, importReceipts, exportReceipts, warehouseController, productController);
    }


    public void showMenu() {
        while (true) {
            printMainMenu();
            int choice = Tool.validateInt("Enter your choice from the MAIN MENU: ", "Invalid choice. Please choose a valid option.");
            processMainMenuChoice(choice);
        }
    }

    private void printMainMenu() {
        System.out.println("===== MAIN MENU =====");
        System.out.println("1. Manage products");
        System.out.println("2. Manage Warehouse");
        System.out.println("3. Report");
        System.out.println("4. Store data to files");
        System.out.println("5. Close the application");
    }

    private void processMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                productView.start();
                break;
            case 2:
                warehouseView.start();
                break;
            case 3:
                reportView.start();
                break;
//            case 4:
//                storeDataToFile();
//                break;
            case 5:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please choose a number between 1-5.");
                break;
        }
    }

    
//    private void storeDataToFile() {
//        // Placeholder. Implement the logic to store data to files here.
//        System.out.println("Data stored successfully!"); // Temporary message.
//    }
}
