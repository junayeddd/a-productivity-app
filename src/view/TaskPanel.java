/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
//import model.TaskModel;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Junayed
 */
public class TaskPanel extends JPanel{

        
        //UI components 
    private JLabel titlelabel;
    private JLabel statslabel;
   
    
    //task input components
    private JTextField taskTitleField;
    private JTextArea taskDescriptionArea;
    private JComboBox<String> taskTypePriority;
    private JSpinner prioritySpinner;
    private JButton addTaskButton;
    
    //task display components
//    private DefaultListModel<TaskModel>taskListModel;
//    private JList<TaskModel> taskList;
    private JScrollPane taskScrollPanel;
    
    //control buttons
    private JButton completeButton;
    private JButton deleteButton;
    private JButton editButton;
    
    //filter and sort components
    private JComboBox<String> filter;
    private JComboBox<String> sort;
    
    //file operation buttons 
    private JButton save;
    private JButton load;
    
    public TaskPanel(){
        
        
    }
    
    private void initializeComponents()
    {
        //title and stats labelling
        titlelabel= new JLabel("📋 TASK MANAGER",JLabel.CENTER);
        statslabel= new JLabel("TASKS:0   COMPLETED:0   PENDING:0",JLabel.CENTER);
        
        //task input components labelling
        taskTitleField = new JTextField(20);
        taskDescriptionArea = new JTextArea(3, 20);
        taskTypePriority = new JComboBox<>(new String[]{"REGULAR", "PRIORITY", "DEADLINE"});
        prioritySpinner = new JSpinner (new SpinnerNumberModel(3, 1, 5, 1));
        addTaskButton = new JButton("➕ ADD TASK");
        
        //task list
//        taskListModel=new DefaultListModel<>();
//        taskList = new JList<>(taskListModel);
//        taskScrollPanel = new JScrollPane(taskList);
        
        //control buttons 
        completeButton = new JButton("✓ COMPLETE");
        deleteButton = new JButton("🗑 DELETE");
        editButton = new JButton("✏ EDIT");
        
        //filter and sort buttons
        filter = new JComboBox<>(new String[]{"ALL TASKS", "PENDING", "COMPLETED", "PRIORRITY", "DEADLINE"});
        sort = new JComboBox<>(new String[]{"BY DATE", "BY PRIORITY", "BY TYPE"});
        
        //file operations 
        save = new JButton("💾 SAVE");
        load = new JButton("📁 LOAD");
        
        
        
        
    }
    
    private void LayoutComponents ()
    {
        setLayout(new BorderLayout(10,10));
        // Top panel - Title and stats
        JPanel topPanel = new JPanel();
        
        // left panel - add new task/input task
        JPanel leftPanel = new JPanel();
        
        //center panel - filter, sort, task list
        JPanel centerPanel = new JPanel();
        
        // right panel - actions(complete, edit, delete)
        JPanel rightPanel = new JPanel();
        
        //bottom panel - save and load
        JPanel bottomPanel = new JPanel();
        
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        
        
        
    }
    

    
    
}
