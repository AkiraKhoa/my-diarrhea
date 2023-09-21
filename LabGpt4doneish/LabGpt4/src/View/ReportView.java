package View;

import Controller.ProductController;
import Controller.ProductNotFoundException;
import Controller.WarehouseController;
import Data.DataSingleton;
import Model.ExportReceipt;
import Model.ImportReceipt;
import Model.Product;
import Model.Receipt;
import Tools.Tool;
import java.util.List;
import java.util.Map;


public class ReportView {

    private final Map<String, Product> products;

   
    public ReportView() {
        this.products = DataSingleton.getInstance().getProducts();
    }
    
    public void start() {
        while (true) {
            System.out.println("-----------------------------------------------------");
            System.out.println("                     Report Menu                     ");
            System.out.println("-----------------------------------------------------");
            System.out.println("1. Display Expired Products");
            System.out.println("2. Display Products for Sale");
            System.out.println("3. Display Low Stock Products");
            System.out.println("4. Display Product Data");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int option = Tool.validateInt("Choose an option: ", "Invalid option. Try again.");

            switch (option) {
                case 1:
                    displayExpiredProducts();
                    break;
                case 2:
                    displayProductsForSale();
                    break;
                case 3:
                    displayLowStockProducts();
                    break;
                case 4:
                    String productCode = Tool.validateExistInMap("Enter the product code to view data: ", products, "Product code does not exist!");
                    try {
                        displayProductData(productCode);
                    } catch (ProductNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // 3.1. Products that have expired
    public void displayExpiredProducts() {
        try {
            ProductController productController = new ProductController();
            List<Product> expiredProducts = productController.getExpiredProducts();
            
            // Print header
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("                              List of Expired Products                                   ");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.format("| %-8s | %-20s | %-12s | %-12s | %-10s |", "CODE", "NAME", "MANUFACTURE", "EXPIRE", "QUANTITY");
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------");

            // Print products
            for (Product product : expiredProducts) {
                System.out.println(product.toString());  // Use existing toString() method
            }
            
            System.out.println("-----------------------------------------------------------------------------------------");

        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // 3.2. The products that the store is selling
    public void displayProductsForSale() {
        WarehouseController warehouseController = new WarehouseController();
        List<Product> productsForSale = warehouseController.getProductsForSale();
        if (!productsForSale.isEmpty()) {
            // Print header
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("                              List of Products for Sale                                  ");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.format("| %-8s | %-20s | %-12s | %-12s | %-10s |", "CODE", "NAME", "MANUFACTURE", "EXPIRE", "QUANTITY");
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------");

            // Print products
            for (Product product : productsForSale) {
                System.out.println(product.toString());  // Using existing toString() method
            }
            
            System.out.println("-----------------------------------------------------------------------------------------");
        } else {
            System.out.println("No products available for sale.");
        }

        // Return to the main screen (if you have one)
    }
    // 3.3. Products that are running out of stock
    public void displayLowStockProducts() {
        WarehouseController warehouseController = new WarehouseController();
        List<Product> lowStockProducts = warehouseController.getLowStockProducts();
        if (!lowStockProducts.isEmpty()) {
            // Print header
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("                              List of Low-Stock Products                                 ");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.format("| %-8s | %-20s | %-12s | %-12s | %-10s |", "CODE", "NAME", "MANUFACTURE", "EXPIRE", "QUANTITY");
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------");

            // Print products
            for (Product product : lowStockProducts) {
                System.out.println(product.toString());  // Using existing toString() method
            }
            
            System.out.println("-----------------------------------------------------------------------------------------");
        } else {
            System.out.println("No low-stock products found.");
        }
        // Return to the main screen (if you have one)
    }
    
    // 3.4. Import/export receipt of a product
    public void displayProductData(String productCode) throws ProductNotFoundException {
    if (!products.containsKey(productCode)) {
        System.out.println("Product does not exist");
        return;
    }
    
    Product product = products.get(productCode);
    System.out.println("Product Data from warehouse.dat or warehouse's collection:");
    System.out.println(product.toString());
    WarehouseController warehouseController = new WarehouseController();
    List<Receipt> relatedReceipts = warehouseController.getReceiptsByProductCode(productCode);
    if (relatedReceipts.isEmpty()) {
        System.out.println("No import/export receipts for this product.");
    } else {
        System.out.println("Related Receipts:");
        for (Receipt receipt : relatedReceipts) {
            System.out.println(receipt.toString());
        }
    }
}

    // ... Any other helper methods or private methods ...
}
