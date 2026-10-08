package app;

import service.LibrarySystem;
import ui.MainFrame;

public class Main {

    public static void main(String[] args) {
    	
        LibrarySystem system = new LibrarySystem();
        new MainFrame(system);

    }

}