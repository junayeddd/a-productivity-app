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
    
    private JTabbedPane tabs;
    private TaskPanel taskPanel;
    private NotePanel notePanel;
    private PomodoroTimerPanel timerPanel;
    private StatsPanel statsPanel;
    
    public MainUI(){
        
        // Initializing the window
        setSize(1000, 600);
        setTitle("A Productivity App");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        // Initializing the Tabs
        tabs = new JTabbedPane();
        taskPanel = new TaskPanel();
        notePanel = new NotePanel();
        timerPanel = new PomodoroTimerPanel();
        statsPanel = new StatsPanel();
        
        // Connecting tabs with panels
        tabs.add("Pomodoro", timerPanel);
        tabs.add("Tasks", taskPanel);
        tabs.add("Notes", notePanel);
        tabs.add("Stats", statsPanel);
        
        
        
        
        
        //adding tabs to the Window
        add(tabs);
        
        
        
        
        
        // Turning off focus from tabs and buttons
        tabs.setFocusable(false);
        
        
        setVisible(true);
        
    }
    
}
