/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
//import controller.NoteController;
import javax.swing.*;
import java.awt.*;
import model.PomodoroModel;
import controller.PomodoroController;
import controller.TaskModelController;
import model.TaskModel;
//import model.NoteModel;

/**
 *
 * @author Junayed
 */
public class MainUI extends JFrame{
    
    private JTabbedPane tabs;
    private TaskPanel taskPanel;
    private PomodoroTimerPanel timerPanel;
    private NotesView notesView;
    
    public MainUI(){
        
        // Initializing the window
        setSize(1400, 900);
        setTitle("A Productivity App");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        
        
        // Initializing the Tabs
        tabs = new JTabbedPane();
        taskPanel = new TaskPanel();
        timerPanel = new PomodoroTimerPanel();
        notesView = new NotesView();

        
        // Connecting tabs with panels
        tabs.add("Pomodoro", timerPanel);
        tabs.add("Tasks", taskPanel);
        tabs.add("CoursePanel", notesView);
        
        // calling Pomodoro constructor 
        PomodoroModel pomodoroModel = new PomodoroModel(timerPanel);
        new PomodoroController(pomodoroModel, timerPanel);
        
        // calling Todo task constructor
        TaskModel taskModel = new TaskModel();
        TaskModelController taskController = new TaskModelController(taskModel, taskPanel);
        
        
        
        
        
        
        
        
        //adding tabs to the Window
        add(tabs);
        
        
        
        
        
        // Turning off focus from tabs and buttons
        tabs.setFocusable(false);
        
        
        setVisible(true);
        
    }
    
}
