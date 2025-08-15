/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import model.TaskModel;
import java.awt.*;
import javax.swing.*;
import java.util.List;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author Asra 
 */
public class TaskPanel extends JPanel{
    //UI components 
    private JLabel titlelabel,statslabel,statuslabel;
    
    
    //task input components
    private JTextField taskTitleField;
    private JTextArea taskDescriptionArea;
    private JComboBox<String> taskTypePriority;
    private JSpinner prioritySpinner;
    private JButton addTaskButton;
    
    //task display components
    private DefaultListModel<TaskModel>taskListModel;
    private JList<TaskModel> taskList;
    private JScrollPane taskScrollPanel;
    
    //control buttons
    private JButton completeButton,deleteButton,editButton;
    
    //filter and sort components
    private JComboBox<String> filter,sort;
    
    
    //file operation buttons 
    private JButton save,load;
   
    
    public TaskPanel(){
        //title and stats labelling
        titlelabel= new JLabel("📋 TASK MANAGER",JLabel.CENTER);
        statslabel= new JLabel("TASKS:0   COMPLETED:0   PENDING:0",JLabel.CENTER);
        statuslabel = new JLabel("Current Status");
        
        
        //task input components labelling
        taskTitleField = new JTextField(20);
        taskDescriptionArea = new JTextArea(3, 20);
        taskTypePriority = new JComboBox<>(new String[]{"REGULAR", "PRIORITY", "DEADLINE"});
        prioritySpinner = new JSpinner (new SpinnerNumberModel(3, 1, 5, 1));
        addTaskButton = new JButton("➕ ADD TASK");
        
        //task list
        taskListModel=new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskScrollPanel = new JScrollPane(taskList);
        
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
        
        
        
        setLayout(new BorderLayout(10,10));
        // Top panel - Title and stats
        JPanel topPanel = createTopPanel();
        
        // left panel - add new task/input task
        JPanel leftPanel = createLeftPanel();
        
        //center panel - filter, sort, task list
        JPanel centerPanel = createCenterPanel();
        
        // right panel - actions(complete, edit, delete)
        JPanel rightPanel = createRightPanel();
        
        //bottom panel - save and load
        JPanel bottomPanel = createBottomPanel();
        
        //add panels to main layout
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        
        
        
        
        
        
        
        
    }
    /*
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
        taskListModel=new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskScrollPanel = new JScrollPane(taskList);
        
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
    */
    
     /*
    private void LayoutComponents ()
    {
        setLayout(new BorderLayout(10,10));
        // Top panel - Title and stats
        JPanel topPanel = createTopPanel();
        
        // left panel - add new task/input task
        JPanel leftPanel = createLeftPanel();
        
        //center panel - filter, sort, task list
        JPanel centerPanel = createCenterPanel();
        
        // right panel - actions(complete, edit, delete)
        JPanel rightPanel = createRightPanel();
        
        //bottom panel - save and load
        JPanel bottomPanel = createBottomPanel();
        
        //add panels to main layout
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        
        //
        
    }
    */
    
    private JPanel createTopPanel()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(200,220,255));
        panel.setPreferredSize(new Dimension(800,80));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        panel.add(titlelabel,BorderLayout.NORTH );
        panel.add(statslabel,BorderLayout.CENTER );
        
        
        return panel;
    }
    
    
    private JPanel createLeftPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245,250,255));
        panel.setPreferredSize(new Dimension(280,400));
        panel.setBorder(new TitledBorder("ADD NEW TASK"));
        
        //title input
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(new Color(245,250,255));
        titlePanel.add(new JLabel("TITLE:"));
        titlePanel.add(taskTitleField);
        
        //description input
        
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBackground(new Color(245,250,255));
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        descriptionPanel.add(new JLabel("DESCRIPTION:"),BorderLayout.NORTH);
        taskDescriptionArea.setWrapStyleWord(true);
        taskDescriptionArea.setLineWrap(true);
        descriptionPanel.add(new JScrollPane(taskDescriptionArea), BorderLayout.CENTER);
        
        //type and priority
        
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.setBackground(new Color(245,250,255));
        typePanel.add(new JLabel("TYPE:"));
        typePanel.add(taskTypePriority);
        
        JPanel priorityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        priorityPanel.setBackground(new Color(245,250,255));
        priorityPanel.add(new JLabel("PRIORITY:"));
        priorityPanel.add(prioritySpinner);
        
        //Add button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(245,250,255));
        priorityPanel.add(addTaskButton);
        
        
     return panel;
    }
    
    private JPanel createCenterPanel()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255,255,255));
        panel.setBorder(new TitledBorder("TASKS"));
        
        //filter and sort panel
        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.setBackground(new Color(240,245,255));
        filterPanel.add(new JLabel("FILTER:"));
        filterPanel.add(filter);
        filterPanel.add(new JLabel("SORT"));
        filterPanel.add(sort);
        
        //task list setup
        taskScrollPanel.setPreferredSize(new Dimension(400,300));
        taskScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        panel.add(filterPanel, BorderLayout.NORTH);
        panel.add(taskScrollPanel, BorderLayout.CENTER);
        
        
        
    return panel;
    }
    
    
    private JPanel createRightPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245,250,255));
        panel.setPreferredSize(new Dimension(150,400));
        panel.setBorder(new TitledBorder("ACTIONS:"));
        
        //add spacing between buttons
        panel.add(Box.createVerticalStrut(10));
        panel.add(completeButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(deleteButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(editButton);
        panel.add(Box.createVerticalGlue());
        
        
        
     return panel;
    }
    
    
    private JPanel createBottomPanel()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(200,220,255));
        panel.setPreferredSize(new Dimension(800,50));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        //file operation buttons
        JPanel filePanel= new JPanel(new FlowLayout());
        filePanel.setBackground(new Color(200,220,255));
        filePanel.add(save);
        filePanel.add(load);
        
        panel.add(statuslabel,BorderLayout.WEST);
        panel.add(filePanel, BorderLayout.EAST);
        
        
        
        
     return panel;
    }
    
    
    
    
    private void styleComponents() {
        // Title styling (matching PomodoroTimerPanel style)
        titlelabel.setFont(new Font("Dialog", Font.BOLD, 20));
        titlelabel.setForeground(new Color(0, 0, 100));
        
        // Stats label styling
        statslabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        statslabel.setForeground(new Color(50, 50, 150));
        
        // Status label styling
        statuslabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        statuslabel.setForeground(new Color(100, 100, 100));
        
        // Button styling (matching Pomodoro buttons)
        styleButton(addTaskButton, new Color(0, 100, 50), Color.WHITE);
        styleButton(completeButton, new Color(0, 150, 0), Color.WHITE);
        styleButton(editButton, new Color(100, 100, 200), Color.WHITE);
        styleButton(deleteButton, new Color(255, 100, 120), Color.WHITE);
        styleButton(save, new Color(0, 50, 100), Color.WHITE);
        styleButton(load, new Color(50, 100, 150), Color.WHITE);
        
        // List styling
        taskList.setFont(new Font("SansSerif", Font.PLAIN, 12));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Input field styling
        taskTitleField.setFont(new Font("SansSerif", Font.PLAIN, 12));
        taskDescriptionArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        
        // Combo box styling
        taskTypePriority.setFont(new Font("SansSerif", Font.PLAIN, 12));
        filter.setFont(new Font("SansSerif", Font.PLAIN, 12));
        sort.setFont(new Font("SansSerif", Font.PLAIN, 12));
    }
    
    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setBorderPainted(false);
        button.setFocusable(false);
        button.setMargin(new Insets(5, 10, 5, 10));
        button.setPreferredSize(new Dimension(120, 30));
    }
    
    private void setupEventHandlers() {
        // Double-click on task list to toggle completion
        taskList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = taskList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        // This will be handled by the controller
                    }
                }
            }
        });
    }
    
    // Public methods for updating UI (called by Controller and Model)
    public void updateTaskList(List<TaskModel> tasks) {
        taskListModel.clear();
        for (TaskModel task : tasks) {
            taskListModel.addElement(task);
        }
    }
    
    public void updateTaskStats(int total, int completed) {
        int pending = total - completed;
        statslabel.setText(String.format("TASKS: %d | COMPLETED: %d | PENDING: %d", total, completed, pending));
    }
    
    public void updateStatusLabel(String message) {
        statuslabel.setText(message);
    }
    
    public void clearInputFields() {
        taskTitleField.setText("");
        taskDescriptionArea.setText("");
        taskTypePriority.setSelectedIndex(0);
        prioritySpinner.setValue(3);
    }
    
    public void setInputFieldsEnabled(boolean enabled) {
        taskTitleField.setEnabled(enabled);
        taskDescriptionArea.setEnabled(enabled);
        taskTypePriority.setEnabled(enabled);
        prioritySpinner.setEnabled(enabled);
        addTaskButton.setEnabled(enabled);
    }
    
    // Getter methods for Controller access (Encapsulation)
    public JButton getAddTaskButton() { return addTaskButton; }
    public JButton getCompleteButton() { return completeButton; }
    public JButton getDeleteButton() { return deleteButton; }
    public JButton getEditButton() { return editButton; }
    public JButton getSaveButton() { return save; }
    public JButton getLoadButton() { return load; }
    
    public JTextField getTaskTitleField() { return taskTitleField; }
    public JTextArea getTaskDescArea() { return taskDescriptionArea; }
    public JComboBox<String> getTaskTypeCombo() { return taskTypePriority; }
    public JSpinner getPrioritySpinner() { return prioritySpinner; }
    
    public JList<TaskModel> getTaskList() { return taskList; }
    public JComboBox<String> getFilterCombo() { return filter; }
    public JComboBox<String> getSortCombo() { return sort; }
    
    // Method to get selected task
    public TaskModel getSelectedTask() {
        return taskList.getSelectedValue();
    }
    
    public int getSelectedTaskIndex() {
        return taskList.getSelectedIndex();
    }
    
    // Method to select a task by index
    public void setSelectedTaskIndex(int index) {
        if (index >= 0 && index < taskListModel.getSize()) {
            taskList.setSelectedIndex(index);
        }
    }
    
    // Method to show error messages
    public void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    // Method to show info messages  
    public void showInfoMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
