package View;

import Controller.ImportExportController;
import Controller.ProductController;
import Controller.ProductNotFoundException;
import Controller.WarehouseController;
import Model.ExportReceipt;
import Model.ImportReceipt;
import Model.Product;
import Tools.Tool;
import java.util.Map;

public class MainView {

    private final WarehouseView warehouseView;
    private final ProductView productView;
    private final ReportView reportView;  

    public MainView() {
        this.warehouseView = new WarehouseView();
        this.productView = new ProductView();
        this.reportView = new ReportView();
    }


    public void showMenu() throws ProductNotFoundException {
        while (true) {
            printMainMenu();
            int choice = Tool.validateIntRange("Enter your choice from the MAIN MENU: ", 1, 5, "Invalid choice. Please choose a valid option.");
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

    private void processMainMenuChoice(int choice) throws ProductNotFoundException {
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
