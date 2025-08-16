package controller;

import model.*;
import view.TaskPanel;
import javax.swing.*;
import java.util.List;

/**
 * TaskModelController - Controls interaction between TaskModel and TaskPanel
 * @author Asra
 */
public class TaskModelController {
    
    private TaskModel model;
    private TaskPanel view;
    private final String DEFAULT_FILENAME = "tasks.txt";
    private String currentFilter = "ALL TASKS";
    private String currentSort = "BY DATE";

    public TaskModelController(TaskModel model, TaskPanel view) {
        this.model = model;
        this.view = view;
        
        setUpEventListeners();
        initializeView();
    }
    
    private void setUpEventListeners() {
        // Task management buttons
        view.getAddTaskButton().addActionListener(e -> addNewTask());
        view.getCompleteButton().addActionListener(e -> toggleTaskCompletion());
        view.getDeleteButton().addActionListener(e -> deleteSelectedTask());
        view.getEditButton().addActionListener(e -> editSelectedTask());
        
        // File operations
        view.getSaveButton().addActionListener(e -> saveTasksToFile());
        view.getLoadButton().addActionListener(e -> loadTasksFromFile());
        
        // Filter and sort operations
        view.getFilterCombo().addActionListener(e -> applyFilter());
        view.getSortCombo().addActionListener(e -> applySort());
        
        // Task list selection
        view.getTaskList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateControlButtons();
            }
        });
        
        // Double-click to toggle completion
        view.getTaskList().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    toggleTaskCompletion();
                }
            }
        });
    }
    
    private void initializeView() {
        updateControlButtons();
        updateTaskDisplay();
        view.updateStatusLabel("Task Manager ready - Add your first task!");
    }

    private void addNewTask() {
        try {
            // Get input values
            String title = view.getTaskTitleField().getText().trim();
            String description = view.getTaskDescArea().getText().trim();
            String taskType = (String) view.getTaskTypeCombo().getSelectedItem();
            int priority = (Integer) view.getPrioritySpinner().getValue();
            
            // Validate input
            if (title.isEmpty()) {
                view.showErrorMessage("Task title cannot be empty!", "Invalid Input");
                view.getTaskTitleField().requestFocus();
                return;
            }
            
            // Create task based on type
            TaskModel newTask = createTaskByType(taskType, title, description, priority);
            
            // Add task to model
            model.addTask(newTask);
            
            // Clear input fields and update UI
            view.clearInputFields();
            updateTaskDisplay();
            view.updateStatusLabel("Task added: " + title);
            
            // Select the newly added task
            List<TaskModel> displayTasks = model.getFilteredAndSortedTasks(currentFilter, currentSort);
            if (!displayTasks.isEmpty()) {
                // Find the index of the new task in the display list
                for (int i = 0; i < displayTasks.size(); i++) {
                    if (displayTasks.get(i).getTitle().equals(title)) {
                        view.setSelectedTaskIndex(i);
                        break;
                    }
                }
            }
            
        } catch (TaskException ex) {
            handleTaskException(ex);
        } catch (Exception ex) {
            view.showErrorMessage("Unexpected error while adding task: " + ex.getMessage(), "Error");
        }
    }
    
    private TaskModel createTaskByType(String taskType, String title, String description, int priority) {
        // Create different types of tasks based on the type
        return new TaskModel(title, description, taskType, priority, "PENDING");
    }

    private void toggleTaskCompletion() {
        TaskModel selectedTask = view.getSelectedTask();
        if (selectedTask == null) {
            view.showErrorMessage("Please select a task to toggle completion.", "No Task Selected");
            return;
        }
        
        try {
            String currentStatus = selectedTask.getStatus();
            String newStatus = "COMPLETED".equals(currentStatus) ? "PENDING" : "COMPLETED";
            selectedTask.setStatus(newStatus);
            
            updateTaskDisplay();
            view.updateStatusLabel("Task marked as " + newStatus.toLowerCase() + ": " + selectedTask.getTitle());
            
        } catch (Exception ex) {
            view.showErrorMessage("Error updating task status: " + ex.getMessage(), "Error");
        }
    }

    private void deleteSelectedTask() {
        TaskModel selectedTask = view.getSelectedTask();
        if (selectedTask == null) {
            view.showErrorMessage("Please select a task to delete.", "No Task Selected");
            return;
        }
        
        int result = JOptionPane.showConfirmDialog(
            view,
            "Are you sure you want to delete the task: " + selectedTask.getTitle() + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (result == JOptionPane.YES_OPTION) {
            try {
                model.removeTask(selectedTask);
                updateTaskDisplay();
                view.updateStatusLabel("Task deleted: " + selectedTask.getTitle());
                
            } catch (TaskException ex) {
                handleTaskException(ex);
            }
        }
    }

    private void editSelectedTask() {
        TaskModel selectedTask = view.getSelectedTask();
        if (selectedTask == null) {
            view.showErrorMessage("Please select a task to edit.", "No Task Selected");
            return;
        }
        
        // Show edit dialog
        TaskEditDialog editDialog = new TaskEditDialog(
            (JFrame) SwingUtilities.getWindowAncestor(view),
            selectedTask
        );
        
        if (editDialog.showDialog()) {
            // User clicked OK, update the task
            TaskModel updatedTask = editDialog.getUpdatedTask();
            selectedTask.setTitle(updatedTask.getTitle());
            selectedTask.setDescription(updatedTask.getDescription());
            selectedTask.setType(updatedTask.getType());
            selectedTask.setPriority(updatedTask.getPriority());
            
            updateTaskDisplay();
            view.updateStatusLabel("Task updated: " + selectedTask.getTitle());
        }
    }

    // Fixed method name (was saveTaskstoFile)
    private void saveTasksToFile() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new java.io.File(DEFAULT_FILENAME));
            fileChooser.setDialogTitle("Save Tasks");
            
            int result = fileChooser.showSaveDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = fileChooser.getSelectedFile().getAbsolutePath();
                model.saveToFile(filename);
                view.updateStatusLabel("Tasks saved to: " + filename);
                view.showInfoMessage("Tasks successfully saved!", "Save Complete");
            }
            
        } catch (TaskException ex) {
            handleTaskException(ex);
        } catch (Exception ex) {
            view.showErrorMessage("Error saving file: " + ex.getMessage(), "Save Error");
        }
    }

    private void loadTasksFromFile() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new java.io.File(DEFAULT_FILENAME));
            fileChooser.setDialogTitle("Load Tasks");
            
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = fileChooser.getSelectedFile().getAbsolutePath();
                
                int confirmResult = JOptionPane.showConfirmDialog(
                    view,
                    "This will replace all current tasks. Continue?",
                    "Confirm Load",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                
                if (confirmResult == JOptionPane.YES_OPTION) {
                    model.loadFromFile(filename);
                    // Reset filters to show all loaded tasks
                    currentFilter = "ALL TASKS";
                    currentSort = "BY DATE";
                    view.getFilterCombo().setSelectedItem(currentFilter);
                    view.getSortCombo().setSelectedItem(currentSort);
                    updateTaskDisplay();
                    view.updateStatusLabel("Tasks loaded from: " + filename);
                    view.showInfoMessage("Tasks successfully loaded!", "Load Complete");
                }
            }
            
        } catch (TaskException ex) {
            handleTaskException(ex);
        } catch (Exception ex) {
            view.showErrorMessage("Error loading file: " + ex.getMessage(), "Load Error");
        }
    }

    private void applyFilter() {
        currentFilter = (String) view.getFilterCombo().getSelectedItem();
        updateTaskDisplay();
        view.updateStatusLabel("Filter applied: " + currentFilter);
    }

    private void applySort() {
        currentSort = (String) view.getSortCombo().getSelectedItem();
        updateTaskDisplay();
        view.updateStatusLabel("Sort applied: " + currentSort);
    }
    
    private void updateControlButtons() {
        boolean hasSelection = view.getSelectedTask() != null;
        boolean hasTasks = model.getTotalTasks() > 0;
        
        view.getCompleteButton().setEnabled(hasSelection);
        view.getDeleteButton().setEnabled(hasSelection);
        view.getEditButton().setEnabled(hasSelection);
        view.getSaveButton().setEnabled(hasTasks);
    }

    // Fixed task display method to use the new filtering and sorting method
    private void updateTaskDisplay() {
        try {
            // Get filtered and sorted tasks using the new method
            List<TaskModel> displayTasks = model.getFilteredAndSortedTasks(currentFilter, currentSort);
            
            // Update view
            view.updateTaskList(displayTasks);
            view.updateTaskStats(model.getTotalTasks(), model.getCompletedTasks());
            updateControlButtons();
            
        } catch (Exception ex) {
            view.showErrorMessage("Error updating display: " + ex.getMessage(), "Display Error");
        }
    }
    
    private void handleTaskException(TaskException ex) {
        view.showErrorMessage(ex.getMessage(), "Task Error");
        view.updateStatusLabel("Error: " + ex.getMessage());
    }
    
    // Inner class for task editing dialog
    private static class TaskEditDialog {
        private JDialog dialog;
        private JTextField titleField;
        private JTextArea descArea;
        private JComboBox<String> typeCombo;
        private JSpinner prioritySpinner;
        private boolean okClicked = false;
        private TaskModel originalTask;
        
        public TaskEditDialog(JFrame parent, TaskModel task) {
            this.originalTask = task;
            createDialog(parent);
            populateFields();
        }
        
        private void createDialog(JFrame parent) {
            dialog = new JDialog(parent, "Edit Task", true);
            dialog.setLayout(new java.awt.BorderLayout());
            dialog.setSize(400, 350);
            dialog.setLocationRelativeTo(parent);
            
            // Create input panel
            JPanel inputPanel = new JPanel(new java.awt.GridBagLayout());
            java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
            gbc.insets = new java.awt.Insets(5, 5, 5, 5);
            
            // Title
            gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = java.awt.GridBagConstraints.WEST;
            inputPanel.add(new JLabel("Title:"), gbc);
            gbc.gridx = 1; gbc.fill = java.awt.GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
            titleField = new JTextField(20);
            inputPanel.add(titleField, gbc);
            
            // Description
            gbc.gridx = 0; gbc.gridy = 1; gbc.fill = java.awt.GridBagConstraints.NONE; gbc.weightx = 0;
            inputPanel.add(new JLabel("Description:"), gbc);
            gbc.gridx = 1; gbc.fill = java.awt.GridBagConstraints.BOTH; gbc.weightx = 1.0; gbc.weighty = 1.0;
            descArea = new JTextArea(4, 20);
            descArea.setLineWrap(true);
            descArea.setWrapStyleWord(true);
            inputPanel.add(new JScrollPane(descArea), gbc);
            
            // Type
            gbc.gridx = 0; gbc.gridy = 2; gbc.fill = java.awt.GridBagConstraints.NONE; gbc.weightx = 0; gbc.weighty = 0;
            inputPanel.add(new JLabel("Type:"), gbc);
            gbc.gridx = 1; gbc.fill = java.awt.GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
            typeCombo = new JComboBox<>(new String[]{"REGULAR", "PRIORITY", "DEADLINE"});
            inputPanel.add(typeCombo, gbc);
            
            // Priority
            gbc.gridx = 0; gbc.gridy = 3; gbc.fill = java.awt.GridBagConstraints.NONE; gbc.weightx = 0;
            inputPanel.add(new JLabel("Priority:"), gbc);
            gbc.gridx = 1; gbc.fill = java.awt.GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
            prioritySpinner = new JSpinner(new SpinnerNumberModel(3, 1, 5, 1));
            inputPanel.add(prioritySpinner, gbc);
            
            // Button panel
            JPanel buttonPanel = new JPanel(new java.awt.FlowLayout());
            JButton okButton = new JButton("OK");
            JButton cancelButton = new JButton("Cancel");
            
            // Style buttons
            okButton.setPreferredSize(new java.awt.Dimension(80, 30));
            cancelButton.setPreferredSize(new java.awt.Dimension(80, 30));
            
            okButton.addActionListener(e -> {
                if (validateInput()) {
                    okClicked = true;
                    dialog.dispose();
                }
            });
            
            cancelButton.addActionListener(e -> dialog.dispose());
            
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
            
            dialog.add(inputPanel, java.awt.BorderLayout.CENTER);
            dialog.add(buttonPanel, java.awt.BorderLayout.SOUTH);
            
            // Set default button
            dialog.getRootPane().setDefaultButton(okButton);
        }
        
        private void populateFields() {
            titleField.setText(originalTask.getTitle());
            descArea.setText(originalTask.getDescription());
            typeCombo.setSelectedItem(originalTask.getType());
            prioritySpinner.setValue(originalTask.getPriority());
        }
        
        private boolean validateInput() {
            if (titleField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Title cannot be empty!", "Invalid Input", 
                    JOptionPane.ERROR_MESSAGE);
                titleField.requestFocus();
                return false;
            }
            return true;
        }
        
        public boolean showDialog() {
            dialog.setVisible(true);
            return okClicked;
        }
        
        public TaskModel getUpdatedTask() {
            return new TaskModel(
                titleField.getText().trim(),
                descArea.getText().trim(),
                (String) typeCombo.getSelectedItem(),
                (Integer) prioritySpinner.getValue(),
                originalTask.getStatus()
            );
        }
    }
}