/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Junayed
 */

import model.CourseNoteModel;
import java.util.ArrayList;
import java.util.List;
import view.NotesView;

public class CourseNoteController {
    private List<CourseNoteModel> notes;
    private CourseNoteModel model;
    private NotesView view;

    public CourseNoteController(NotesView view) {
        this.view = view;
        notes = new ArrayList<>();
        
        
        
    }

    public void addNote(int id, String title, String content, String category) {
        CourseNoteModel note = new CourseNoteModel(id, title, content, category);
        notes.add(note);
    }

//    public void removeNote(int id) {
//        notes.removeIf(note -> note.getId() == id);
//    }

    public List<CourseNoteModel> getAllNotes() {
        return notes;
    }


}