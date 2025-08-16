/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Junayed
 */
import model.EntryModel;
import java.io.*;
import java.util.List;

public class FileHandler {
    public static void writeToFile(String filename, List<EntryModel> entries) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (EntryModel entry : entries) {
                writer.println(entry.displayInfo());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Temporary output
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}