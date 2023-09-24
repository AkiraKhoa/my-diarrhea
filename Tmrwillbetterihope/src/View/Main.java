package View;

import Controller.ProductNotFoundException;
import View.MainView;

public class Main {

    public static void main(String[] args) throws ProductNotFoundException {
        // Initialize the main view
        MainView mainView = new MainView();

        // Start the UI loop
        mainView.showMenu();
    }
}
