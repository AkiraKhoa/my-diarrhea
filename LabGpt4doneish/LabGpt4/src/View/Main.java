package View;

import Controller.ProductController;
import Controller.WarehouseController;
import Controller.ImportExportController;
import Model.ExportReceipt;
import Model.ImportReceipt;
import Model.Product;
import View.MainView;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Instantiate controllers
        ProductController productController = new ProductController();
        WarehouseController warehouseController = new WarehouseController();
        ImportExportController importExportController = new ImportExportController();

        // For now, we'll start with empty datasets. In a more complex application, 
        // you might load these from a database or a file.
        Map<String, Product> products = new HashMap<>();
        Map<String, ImportReceipt> importReceipts = new HashMap<>();
        Map<String, ExportReceipt> exportReceipts = new HashMap<>();

        // Initialize the main view with the required controllers and data
        MainView mainView = new MainView(productController, warehouseController, importExportController, products, importReceipts, exportReceipts);

        // Start the UI loop
        mainView.showMenu();
    }
}

