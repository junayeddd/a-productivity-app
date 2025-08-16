/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asra
 */
public class TaskException extends Exception {
    
    public TaskException(String message) {
        super(message);
    }
    
    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }
}