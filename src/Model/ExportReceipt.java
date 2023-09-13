package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExportReceipt extends WarehouseItem implements Storable<ExportReceipt> {
    // Additional export-specific attributes and methods here
    private String destination;

    public ExportReceipt() {
    }
    
    public ExportReceipt(String importExportCode, Date timestamp, Product product, int quantity, String destination) {
        super(importExportCode, timestamp, product, quantity);
        this.destination = destination;
    }

    // Getter and setter for destination
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    // Additional methods specific to export receipts
    // ...

    @Override
    public String toString() {
        // Customize the toString method to display export receipt information
        return super.toString() + "\nDestination: " + destination;
    }

    @Override
    public List<ExportReceipt> loadAll(String filePath) throws IOException, ClassNotFoundException {
        List<ExportReceipt> exportReceiptList = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    ExportReceipt exportReceipt = (ExportReceipt) objectInputStream.readObject();
                    exportReceiptList.add(exportReceipt);
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
        }
        return exportReceiptList;
    }

    @Override
    public void saveAll(List<ExportReceipt> items, String filePath) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (ExportReceipt exportReceipt : items) {
                objectOutputStream.writeObject(exportReceipt);
            }
        }
    }
}
