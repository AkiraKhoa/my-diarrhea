package Model;
import java.util.*;
import java.text.SimpleDateFormat;

public class Receipt {
    private String code; // a self-incrementing 7-digit number
    private Date date; // time to create the import/export slip
    private Map<String, Integer> products; // Product code and Quantity
    
    public Receipt(String code) {
        this.code = code;
        this.date = new Date(); // Initialize with the current system time
        this.products = new HashMap<>();
    }
    public boolean containsProduct(String productCode) {
        return products.containsKey(productCode);
    }

    public String getCode() {
        return code;
    }

    public Date getDate() {
        return date;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    // Additional methods can be added here
}
