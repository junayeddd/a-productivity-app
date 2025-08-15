/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.NoteController;
import javax.swing.*;
import java.awt.*;

import javax.swing.table.DefaultTableModel;
import model.NoteModel;

/**
 *
 * @author Junayed
 */
public class NotePanel extends JPanel{
/* divide the section in two panel one formPanel and another tablePnael*/
    
        private controller.NoteController noteManager;
        
        private DefaultTableModel tableModel; // Table panel initialization
        private JTable table;

        private JTextField titleField;
        private JTextArea contentArea;
        private JComboBox<String> categoryBox;
        
        private JButton addButton;
        private JButton deleteButton;
        private JButton saveButton;
        private JButton loadButton;

    public NotePanel() {
        // noteManager = new controller.NoteController();
        setLayout(new BorderLayout());

        // === Form Panel with grid layout 3 rows, 2 cols, and 5px gap===
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        titleField = new JTextField();
        contentArea = new JTextArea(3, 20);
        categoryBox = new JComboBox<>(new String[]{"Work", "Study", "Personal"}); //drop down list to select categories

        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Content:"));
        formPanel.add(new JScrollPane(contentArea));
        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryBox);

        add(formPanel, BorderLayout.NORTH);

        // Table Panel 
        String[] columns = {"ID", "Title", "Category", "Content"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Button Panel 
        JPanel buttonPanel = new JPanel();

        
        addButton = new JButton("Add Note");
        deleteButton = new JButton("Delete Note");
        saveButton = new JButton("Save Notes");
        loadButton = new JButton("Load Notes");



        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        add(buttonPanel, BorderLayout.SOUTH);
    
    }
    
    
    
    
    // get functions for buttons

    public JTextArea getContentArea() {
        return contentArea;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

//    public NoteController getNoteManager() {
//        return noteManager;
//    }

    public JTable getTable() {
        return table;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JComboBox<String> getCategoryBox() {
        return categoryBox;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
    
    
    
    
}
    
    

