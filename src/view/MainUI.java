/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Junayed
 */
public class MainUI extends JFrame{
    
    public MainUI(){
        
        // Initializing the window
        setSize(800, 600);
        setTitle("A Productivity App");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        // Initializing the Tabs
        JTabbedPane tabs = new JTabbedPane();
        
        
        // Connecting tabs with panels
        tabs.add("Pomodoro", new PomodoroTimerPanel());
        tabs.add("Tasks", new TaskPanel());
        
        
        
        
        
        //adding tabs to the Window
        add(tabs);
        
        
        
        
        
        // Turning off focus from tabs and buttons
        tabs.setFocusable(false);
        
        
        setVisible(true);
        
    }
    
}
