/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Junayed
 */
public class TaskPanel extends JPanel{
    public TaskPanel(){
        
        // Layout for Pomodoro panel
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(200, 150, 100));
        
        
        
        // Title for panel
        JButton btn = new JButton("Start");
        
        // adding components in panel
        add(btn);
    }
}
