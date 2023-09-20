package View;
import java.util.Scanner;

public class MainView {
    private final Scanner scanner;
    private final ProductView productView;
    private final ReceiptView receiptView;
    private final FileView fileView;

    public MainView() {
        this.scanner = new Scanner(System.in);
        this.productView = new ProductView();
        this.receiptView = new ReceiptView();
        this.fileView = new FileView();
    }

    public void showMainMenu() {
        System.out.println("=====================================================");
        System.out.println("           Welcome to the Warehouse System           ");
        System.out.println("=====================================================");
        System.out.println("|                                                  |");
        System.out.println("|                  Main Menu                       |");
        System.out.println("|                                                  |");
        System.out.println("|  1. Manage Products                              |");
        System.out.println("|  2. Manage Receipts                              |");
        System.out.println("|  3. File Operations                              |");
        System.out.println("|  4. Exit                                         |");
        System.out.println("|                                                  |");
        System.out.println("=====================================================");
    }

    public void navigateToChoice(int choice) {
        switch (choice) {
            case 1:
                productView.showProductMenu();
                break;
            case 2:
                receiptView.showReceiptMenu();
                break;
            case 3:
                fileView.showFileMenu();
                break;
            case 4:
                System.out.println("Exiting the system. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}

