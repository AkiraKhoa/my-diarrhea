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
    private Map<String, ImportReceipt> importReceipts;
    private Map<String, ExportReceipt> exportReceipts;
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

    public String generateExportCode() {
        exportCodeCounter++;
        return "E" + String.format("%06d", exportCodeCounter);
    }
    
    public String createImportReceipt() throws ProductNotFoundException {
    if (products.isEmpty()) {
        throw new ProductNotFoundException("No products available for import.");
    }
    String code = generateImportCode();
    ImportReceipt newImportReceipt = new ImportReceipt(code);
    importReceipts.put(code, newImportReceipt);
    return code;
}

public boolean addItemToImportReceipt(String receiptCode, String productCode, int quantity) throws ProductNotFoundException {
    ImportReceipt receipt = importReceipts.get(receiptCode);
    if (receipt != null && products.containsKey(productCode)) {
        Product product = products.get(productCode);
        product.setImportedQuantity(product.getImportedQuantity() + quantity);
        return true;
    } 
    return false;
}
    
    public String createExportReceipt() throws ProductNotFoundException {
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products available for export.");
        }
        
        String code = generateExportCode();
        ExportReceipt newExportReceipt = new ExportReceipt(code);
        exportReceipts.put(code, newExportReceipt);
        return code;
    }
    
    public boolean addItemToExportReceipt(String receiptCode, String productCode, int quantity) throws ProductNotFoundException {
        ExportReceipt receipt = exportReceipts.get(receiptCode);
        if (receipt != null && products.containsKey(productCode)) {
            Product product = products.get(productCode);
            if (product.getImportedQuantity() >= quantity) {
                product.setImportedQuantity(product.getImportedQuantity() - quantity);
                return true;
            }
        }
        return false;
    }

}
