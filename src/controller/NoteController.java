/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.event.ActionEvent;
import model.NoteModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import view.NotePanel;
/**
 *
 * @author Junayed
 */
public class NoteController {

    private List<NoteModel> notes;
    private NoteModel model;
    private NotePanel view;

    public NoteController(NoteModel model, NotePanel view) {
        this.model = model;
        this.view = view;
        notes = new ArrayList<>();
        
        
        view.getAddButton().addActionListener(e -> addNote());
        
    }

    public void addNote(NoteModel note) {
        notes.add(note);
    }

    public void removeNote(int id) {
        notes.removeIf(n -> n.getId() == id);
    }

    public List<NoteModel> getAllNotes() {
        return notes;
    }
    
    // adding notes to table
    private void addNote() {
        String title = view.getTitleField().getText().trim();
        String content = view.getContentArea().getText().trim();
        String category = (String) view.getCategoryBox().getSelectedItem();

        if (title.isEmpty() || content.isEmpty()) {
            JOptionPane.showMessageDialog(view,"Please fill all fields.",
                    "Missing Data", JOptionPane.WARNING_MESSAGE);
            return;
        }

//        int id = notes.getAllNotes().size() + 1;
        int id = 1;
        NoteModel note = new NoteModel(content, category, id, content);
//        noteManager.addNote(note);

        view.getTableModel().addRow(new Object[]{
            note.getId(),
            note.getTitle(),
            category,
            content
        });

        view.getTitleField().setText("");
        view.getContentArea().setText("");
    }

    
}

