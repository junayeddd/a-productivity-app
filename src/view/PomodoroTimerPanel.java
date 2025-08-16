/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import javax.swing.*;
import java.awt.*;
import model.PomodoroModel;
/**
 *
 * @author Junayed
 */
public class PomodoroTimerPanel extends JPanel{
    
    private JLabel sessionTitle;
    private JLabel timeLabel;
    private JButton startButton, pauseButton, resetButton, skipButton;

    public PomodoroTimerPanel(){
        
        // Layout for Pomodoro panel
        setLayout(new BorderLayout());
        
        
        // Dividing this panel in three panel
        JPanel titlePanel = new JPanel();
        JPanel sessionPanel = new JPanel();
        JPanel btnPanel = new JPanel();
        
        
        // Title panel
        sessionTitle = new JLabel("O.O WORK SESSION: 1", JLabel.CENTER);
        
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 35, 35));

        sessionTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        sessionTitle.setForeground(new Color(0, 0, 100));
        titlePanel.setBackground(new Color(200, 220, 255));
        titlePanel.setPreferredSize(new Dimension(600,100));
        
        
        titlePanel.add(sessionTitle);
        
        
        
        // sessionPanel
        timeLabel = new JLabel("25:00", JLabel.CENTER);
        
        sessionPanel.setLayout(new BorderLayout());
        sessionPanel.setBackground(new Color(150, 200, 220));
        sessionPanel.setPreferredSize(new Dimension(600, 100));
        
        timeLabel.setFont(new Font("Monospaced", Font.BOLD, 98));
        timeLabel.setForeground(Color.WHITE);
        
        sessionPanel.add(timeLabel, BorderLayout.CENTER);
        
        
        // button Panel
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(new Color(200, 220, 255));
        btnPanel.setPreferredSize(new Dimension(600, 100));
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));

        startButton = new JButton("▶ START");
        pauseButton = new JButton("⏸ PAUSE");
        resetButton = new JButton("🔄 RESET");
        skipButton = new JButton("️⏯️ SKIP");

        
        // styiling buttons
        startButton.setMargin(new Insets(8, 20, 8, 20));
        startButton.setToolTipText("Start the Pomodoro Counter");
        startButton.setFocusable(false);
        startButton.setForeground(new Color(255, 255, 255));
        startButton.setBackground(new Color(0, 50, 100));
        startButton.setBorderPainted(false);
        startButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        
        pauseButton.setMargin(new Insets(8, 20, 8, 20));
        pauseButton.setToolTipText("Pause the Pomodoro Counter");
        pauseButton.setFocusable(false);
        pauseButton.setForeground(new Color(0, 50, 100));
        pauseButton.setContentAreaFilled(false);
        pauseButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        
        resetButton.setMargin(new Insets(8, 20, 8, 20));
        resetButton.setToolTipText("Reset the Pomodoro Counter");
        resetButton.setFocusable(false);
        resetButton.setForeground(new Color(255, 255, 255));
        resetButton.setBackground(new Color(255, 100, 120));
        resetButton.setBorderPainted(false);
        resetButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        
        skipButton.setMargin(new Insets(8, 20, 8, 20));
        skipButton.setToolTipText("Reset the Pomodoro Counter");
        skipButton.setFocusable(false);
        skipButton.setForeground(new Color(255, 255, 255));
        skipButton.setBackground(new Color(200, 100, 200));
        skipButton.setBorderPainted(false);
        skipButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        
        // Adding componets in btn panel
        btnPanel.add(startButton);
        btnPanel.add(pauseButton);
        btnPanel.add(resetButton);
        btnPanel.add(skipButton);
        
        
        
        // adding subPanels in PomodorTimerPanel
        add(titlePanel, BorderLayout.NORTH);
        add(sessionPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        
        
    }
    

    // getter functions for components
    public JButton getStartButton() {
        return startButton;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getSkipButton() {
        return skipButton;
    }
    

    public JLabel getSessionTitle() {
        return sessionTitle;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }
    
    
    
    
    
    
}
