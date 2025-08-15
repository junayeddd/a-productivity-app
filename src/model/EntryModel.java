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

public abstract class EntryModel {
    private int id;
    private String title;
    private LocalDate createdDate; // for TaskModel
    
    // Constructor
    public EntryModel(int id, String title) {
        this.id = id;
        this.title = title;
        this.createdDate = LocalDate.now();
    }
    
    
     // getter and setter functions
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

   
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    
    // initializing abstract function
    public abstract String displayInfo();
    
    
    
}
