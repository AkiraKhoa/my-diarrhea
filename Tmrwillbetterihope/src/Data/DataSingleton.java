package Data;

import Model.ExportReceipt;
import Model.ImportReceipt;
import Model.Product;
import java.util.HashMap;
import java.util.Map;

public class DataSingleton {
    private static DataSingleton instance;
    private Map<String, Product> products = new HashMap<>();
    private Map<String, ImportReceipt> importReceipts = new HashMap<>();
    private Map<String, ExportReceipt> exportReceipts = new HashMap<>();

    private DataSingleton() {}

    public static DataSingleton getInstance() {
        if (instance == null) {
            instance = new DataSingleton();
        }
        return instance;
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public Map<String, ImportReceipt> getImportReceipts() {
        return importReceipts;
    }

    public Map<String, ExportReceipt> getExportReceipts() {
        return exportReceipts;
    }
}
