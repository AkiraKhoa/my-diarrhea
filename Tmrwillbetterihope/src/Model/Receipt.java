package Model;
import java.util.*;
import java.text.SimpleDateFormat;

public class Receipt {
    private String receiptsCode; // a self-incrementing 7-digit number
    private Date date; // time to create the import/export slip
    private Map<String, Integer> receiptsItem; // Product code and Quantity
    
    public Receipt(String receiptsCode) {
        this.receiptsCode = receiptsCode;
        this.date = new Date(); // Initialize with the current system time
        this.receiptsItem = new HashMap<>();
    }
    public boolean containsProduct(String productCode) {
        return receiptsItem.containsKey(productCode);
    }

    public String getReceiptsCode() {
        return receiptsCode;
    }

    public void setReceiptsCode(String receiptsCode) {
        this.receiptsCode = receiptsCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    

    public void setReceiptsItem(Map<String, Integer> receiptsItem) {
        this.receiptsItem = receiptsItem;
    }

    public Map<String, Integer> getReceiptsItem() {
        return receiptsItem;
    }

    
    public void addProductToReceipt(String productCode, int quantity) {
        if (receiptsItem.containsKey(productCode)) {
            int existingQuantity = receiptsItem.get(productCode);
            receiptsItem.put(productCode, existingQuantity + quantity);
        } else {
            receiptsItem.put(productCode, quantity);
        }
    }
    
    public String toString(String specialProductCode) {
        System.out.println("Receipt Code: " + receiptsCode);
        System.out.println("Creation Time: " + date);
        System.out.println("Products:");
        for (Map.Entry<String, Integer> entry : receiptsItem.entrySet()) {
            if (entry.getKey().equals(specialProductCode)) {
                System.out.println("======" + entry.getKey() + ": " + entry.getValue() + "======");
            } else {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
        
        return ""; // We're directly printing in the toString, so it returns an empty string.
    }


    // Additional methods can be added here
}
