package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportReceipt extends WarehouseItem implements Storable<ImportReceipt> {
    // Additional import-specific attributes and methods here
    private String source;

    public ImportReceipt(){
    }
    public ImportReceipt(String importExportCode, Date timestamp, Product product, int quantity, String source) {
        super(importExportCode, timestamp, product, quantity);
        this.source = source;
    }

    // Getter and setter for source
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    // Additional methods specific to import receipts
    // ...

    @Override
    public String toString() {
        // Customize the toString method to display import receipt information
        return super.toString() + "\nSource: " + source;
    }

    @Override
    public List<ImportReceipt> loadAll(String filePath) throws IOException, ClassNotFoundException {
        List<ImportReceipt> importReceiptList = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    ImportReceipt importReceipt = (ImportReceipt) objectInputStream.readObject();
                    importReceiptList.add(importReceipt);
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
        }
        return importReceiptList;
    }

    @Override
    public void saveAll(List<ImportReceipt> items, String filePath) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (ImportReceipt importReceipt : items) {
                objectOutputStream.writeObject(importReceipt);
            }
        }
    }
}
