/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Junayed
 */
public class NoteModel extends EntryModel{
    
    private String content;
    private String category;
    
    // Constructor
    public NoteModel(String content, String category, int id, String title) {
        super(id, title);
        this.content = content;
        this.category = category;
    }
    
    
    //Overriding abtract function from Entry
    @Override
    public String displayInfo() {
        return getTitle() + " (" + category + "): " + content;
    } 
    
    

    
    
    
    
}
