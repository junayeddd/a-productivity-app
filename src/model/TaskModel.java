package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.io.*;

/**
 * TaskModel - Manages a collection of tasks and provides data operations
 * @author Asra
 */
public class TaskModel {
    private List<TaskModel> tasks;
    private String title;
    private String description;
    private String type;
    private int priority;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;
    
    // Constructor for the main model (collection manager)
    public TaskModel() {
        this.tasks = new ArrayList<>();
    }
    
    // Constructor for individual task
    public TaskModel(String title, String description, String type, int priority, String status) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.priority = priority;
        this.status = status;
        this.createdDate = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }
    
    // Alternative constructor without status (defaults to PENDING)
    public TaskModel(String title, String description, String type, int priority) {
        this(title, description, type, priority, "PENDING");
    }

    // Task management methods
    public void addTask(TaskModel task) throws TaskException {
        if (task == null) {
            throw new TaskException("Cannot add null task");
        }
        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            throw new TaskException("Task title cannot be empty");
        }
        tasks.add(task);
    }
    
    public void removeTask(TaskModel task) throws TaskException {
        if (task == null) {
            throw new TaskException("Cannot remove null task");
        }
        if (!tasks.remove(task)) {
            throw new TaskException("Task not found in list");
        }
    }
    
    public void removeTaskAt(int index) throws TaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskException("Invalid task index: " + index);
        }
        tasks.remove(index);
    }
    
    public List<TaskModel> getAllTasks() {
        return new ArrayList<>(tasks);
    }
    
    // filter method to work with both filtering and sorting
    public List<TaskModel> getFilteredTasks(String filter) {
        List<TaskModel> filtered = new ArrayList<>();
        
        for (TaskModel task : tasks) {
            switch (filter.toUpperCase()) {
                case "ALL TASKS":
                    filtered.add(task);
                    break;
                case "PENDING":
                    if ("PENDING".equals(task.getStatus())) {
                        filtered.add(task);
                    }
                    break;
                case "COMPLETED":
                    if ("COMPLETED".equals(task.getStatus())) {
                        filtered.add(task);
                    }
                    break;
                case "PRIORITY":
                    if ("PRIORITY".equals(task.getType())) {
                        filtered.add(task);
                    }
                    break;
                case "DEADLINE":
                    if ("DEADLINE".equals(task.getType())) {
                        filtered.add(task);
                    }
                    break;
            }
        }
        return filtered;
    }
    
    // sorting method to work with filtered tasks
    public List<TaskModel> getFilteredAndSortedTasks(String filter, String sortBy) {
        List<TaskModel> filtered = getFilteredTasks(filter);
        
        switch (sortBy.toUpperCase()) {
            case "BY DATE":
                filtered.sort(Comparator.comparing(TaskModel::getCreatedDate));
                break;
            case "BY PRIORITY":
                filtered.sort(Comparator.comparingInt(TaskModel::getPriority).reversed());
                break;
            case "BY TYPE":
                filtered.sort(Comparator.comparing(TaskModel::getType));
                break;
        }
        return filtered;
    }
    
    
    public List<TaskModel> getSortedTasks(String sortBy) {
        List<TaskModel> sorted = new ArrayList<>(tasks);
        
        switch (sortBy.toUpperCase()) {
            case "BY DATE":
                sorted.sort(Comparator.comparing(TaskModel::getCreatedDate));
                break;
            case "BY PRIORITY":
                sorted.sort(Comparator.comparingInt(TaskModel::getPriority).reversed());
                break;
            case "BY TYPE":
                sorted.sort(Comparator.comparing(TaskModel::getType));
                break;
        }
        return sorted;
    }
    
    public int getTotalTasks() {
        return tasks.size();
    }
    
    public int getCompletedTasks() {
        return (int) tasks.stream().filter(task -> "COMPLETED".equals(task.getStatus())).count();
    }
    
    public int getPendingTasks() {
        return getTotalTasks() - getCompletedTasks();
    }
    
    // File operations
    public void saveToFile(String filename) throws TaskException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (TaskModel task : tasks) {
                writer.println(task.toFileString());
            }
        } catch (IOException e) {
            throw new TaskException("Error saving tasks to file: " + e.getMessage());
        }
    }
    
    public void loadFromFile(String filename) throws TaskException {
        tasks.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                TaskModel task = fromFileString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new TaskException("File not found: " + filename);
        } catch (IOException e) {
            throw new TaskException("Error loading tasks from file: " + e.getMessage());
        }
    }
    
    // Helper methods for file operations
    private String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return String.join("|", 
            title != null ? title : "",
            description != null ? description : "",
            type != null ? type : "",
            String.valueOf(priority),
            status != null ? status : "",
            createdDate != null ? createdDate.format(formatter) : "",
            lastModified != null ? lastModified.format(formatter) : ""
        );
    }
    
    private TaskModel fromFileString(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length >= 5) {
                TaskModel task = new TaskModel(parts[0], parts[1], parts[2], 
                    Integer.parseInt(parts[3]), parts[4]);
                
                if (parts.length > 5 && !parts[5].isEmpty()) {
                    task.createdDate = LocalDateTime.parse(parts[5]);
                }
                if (parts.length > 6 && !parts[6].isEmpty()) {
                    task.lastModified = LocalDateTime.parse(parts[6]);
                }
                return task;
            }
        } catch (Exception e) {
            System.err.println("Error parsing task from file: " + e.getMessage());
        }
        return null;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.lastModified = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.lastModified = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        this.lastModified = LocalDateTime.now();
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
        this.lastModified = LocalDateTime.now();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.lastModified = LocalDateTime.now();
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public LocalDateTime getLastModified() {
        return lastModified;
    }
    
    @Override
    public String toString() {
        return title + " [" + status + "]";
    }
}