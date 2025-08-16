/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;

/**
 *
 * @author Junayed
 */
public class CourseNoteModel extends EntryModel{
    
    
    private String content;
    private String category;
    private LocalDate createdDate;


    public CourseNoteModel(int id, String title, String content, String category) {
        super(title);
        this.content = content;
        this.category = category;
        this.createdDate = LocalDate.now();
    }
    
    
    

    // Getter functions

    public String getContent() { return content; }
    public String getCategory() { return category; }
    public LocalDate getCreatedDate() { return createdDate; }

    // Setters

    public void setContent(String content) { this.content = content; }
    public void setCategory(String category) { this.category = category; }

   

    @Override
    public String displayInfo() {
        return getTitle() + " (" + category + "): " + content;
    }
}

