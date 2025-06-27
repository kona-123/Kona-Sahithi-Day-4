package ElevateLabs;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NotesManager {
    private static final String FILE_NAME = "notes.txt";
    private static ArrayList<String> notes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadNotes();

        while (true) {
            System.out.println("\n--- Notes Manager ---");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Save Notes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    saveNotes();
                    break;
                case 4:
                    System.out.println("Exiting and saving...");
                    saveNotes();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addNote() {
        System.out.print("Enter note: ");
        String note = scanner.nextLine();
        notes.add(note);
        System.out.println("Note added.");
    }

    private static void viewNotes() {
        System.out.println("\n--- Your Notes ---");
        if (notes.isEmpty()) {
            System.out.println("No notes yet.");
        } else {
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
            }
        }
    }

    private static void saveNotes() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (String note : notes) {
                writer.write(note + "\n");
            }
            System.out.println("Notes saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving notes: " + e.getMessage());
        }
    }

    private static void loadNotes() {
        try (FileReader reader = new FileReader(FILE_NAME);
             Scanner fileScanner = new Scanner(reader)) {
            while (fileScanner.hasNextLine()) {
                String note = fileScanner.nextLine();
                notes.add(note);
            }
            System.out.println("Notes loaded from file.");
        } catch (IOException e) {
            System.out.println("No existing notes found. Starting fresh.");
        }
    }
}

