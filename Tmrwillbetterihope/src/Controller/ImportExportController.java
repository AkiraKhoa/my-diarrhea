package Controller;

import Model.ImportReceipt;
import Model.ExportReceipt;
import Controller.ProductNotFoundException;
import Data.DataSingleton;
import Model.Product;
import Tools.Tool;
import java.util.HashMap;
import java.util.Map;


public class ImportExportController {
    private Map<String, ImportReceipt> importReceipts; //importReceipts code : importReceipts
    private Map<String, ExportReceipt> exportReceipts; // exportReceipts code : exportReceipts
    private Map<String, Product> products;
    private int importCodeCounter = 0;
    private int exportCodeCounter = 0;

    public ImportExportController() {
        this.importReceipts = DataSingleton.getInstance().getImportReceipts();
        this.exportReceipts = DataSingleton.getInstance().getExportReceipts();
        this.products = DataSingleton.getInstance().getProducts();
    }
    public String generateImportCode() {
        importCodeCounter++;
        return "I" + String.format("%06d", importCodeCounter);
    }

    public String generateExportCode(boolean reset) {
        if (reset == true) {
        exportCodeCounter++;
        }
        else exportCodeCounter--;
        return "E" + String.format("%06d", exportCodeCounter);
    }
    
    public String createImportReceipt() throws ProductNotFoundException {
    if (products.isEmpty()) {
        throw new ProductNotFoundException("No products available for import.");
    }
    String receiptsCode = generateImportCode();
    ImportReceipt newImportReceipt = new ImportReceipt(receiptsCode);
    importReceipts.put(receiptsCode, newImportReceipt);
    return receiptsCode;
}

//    public boolean addItemToImportReceipt(String receiptCode, String productCode, int quantity) throws ProductNotFoundException {
//        ImportReceipt receipt = importReceipts.get(receiptCode);
//        if (receipt != null && products.containsKey(productCode)) {
//            Product product = products.get(productCode);
//            product.setImportedQuantity(product.getImportedQuantity() + quantity);
//            receipt.addProductToReceipt(productCode, quantity);
//            return true;
//        } 
//        return false;
//    }
    
    public boolean addItemToImportReceipt(String receiptCode, String productCode, int quantity) throws ProductNotFoundException {
    ImportReceipt receipt = importReceipts.get(receiptCode);
    
    if (receipt == null) {
        System.out.println("Receipt not found for code: " + receiptCode);
        return false;
    }
    
    if (!products.containsKey(productCode)) {
        System.out.println("Product not found for code: " + productCode);
        return false;
    }
    
    Product product = products.get(productCode);
    int currentQuantity = product.getImportedQuantity();
    
    System.out.println("Current Quantity = " + currentQuantity);
    
    product.setImportedQuantity(currentQuantity + quantity);
    receipt.addProductToReceipt(productCode, quantity);
    return true;
}

    
    
    
//    public boolean addItemToExportReceipt(String receiptCode, String productCode, int quantity) throws ProductNotFoundException {
//        ExportReceipt receipt = exportReceipts.get(receiptCode);
//        if (receipt != null && products.containsKey(productCode)) {
//            Product product = products.get(productCode);
//            if (product.getImportedQuantity() >= quantity) {
//                product.setImportedQuantity(product.getImportedQuantity() - quantity);
//                return true;
//            }
//        }
//        return false;
//    }
//    
    public boolean addItemToExportReceipt(String receiptCode, String productCode, int quantity) throws ProductNotFoundException {
    ExportReceipt receipt = exportReceipts.get(receiptCode);
    if (receipt == null) {
        System.out.println("Debug: Receipt not found for code: " + receiptCode);
        return false;
    }
    
    if (!products.containsKey(productCode)) {
        System.out.println("Debug: Product not found for code: " + productCode);
        return false;
    }
    receipt.addProductToReceipt(productCode, quantity);
    return true;
}

    public void addImportReceiptToMap(ImportReceipt receipt) {
    importReceipts.put(receipt.getReceiptsCode(), receipt);
}
    public void addExportReceiptToMap(ExportReceipt receipt) {
    exportReceipts.put(receipt.getReceiptsCode(), receipt);
}
    public void removeExportReceiptFromMap(String receiptCode) {
    // Check if the exportReceipts map contains the receiptCode
    if (exportReceipts.containsKey(receiptCode)) {
        exportReceipts.remove(receiptCode);
    } else {
        System.out.println("Warning: Receipt with code " + receiptCode + " not found in the map.");
    }
}


}
