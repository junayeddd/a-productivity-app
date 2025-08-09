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
public class PomodoroTimerPanel extends JPanel{
    
    public PomodoroTimerPanel(){
        
        // Layout for Pomodoro panel
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(150, 200, 220));
        
        
        
        //Title for panel
        JLabel heading = new JLabel("Pomodoro Timer");
        
        
        
        // adding components in panel
        add(heading);
        
        
    }
    
    
}
