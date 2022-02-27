package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new WhatsNextApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: profile not found.");
        }
    }
}
