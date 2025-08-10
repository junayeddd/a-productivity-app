/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import view.PomodoroTimerPanel;

/**
 *
 * @author Junayed
 */
public class PomodoroModel {
//    private boolean isWorkSession = true;
    private int workDuration = 1*60;
    private int timeLeft = workDuration;
    private int shortBreakDuration = 1*60;
    private int longBreakDuration = 15*60;
    private int workSessionCounter = 1;
    private String sessionType = "O.O WORK";
    
    
    private PomodoroTimerPanel view;

    public PomodoroModel(PomodoroTimerPanel view) {
        this.view = view;
    }
    
    // Logic to update time
    public void updateTime(int totalSeconds){
        int min = totalSeconds/60;
        int sec = totalSeconds%60;
        view.getTimeLabel().setText(String.format("%02d:%02d", min, sec));
        
    }
    
    // Logic to update session type and number
    public void sessionUpdate(String sessionType, int sessionCounter){
        view.getSessionTitle().setText(sessionType + " SESSION: " + sessionCounter);
    }
    
    
    
    // Switiching between sessions
    public void resetWorkSession(){
        sessionType = "O.O WORK";
        timeLeft = workDuration;

    }
    public void startShortBreak(){
        sessionType = "OwO SHORT BREAK";
        timeLeft = shortBreakDuration;
    }
    public void startLongBreak(){
        sessionType = "UwU LONG BREAK";
        timeLeft = longBreakDuration;
    }
    
    
    
    
    // Timer countdown functionality
    public void countdown(){
        if(timeLeft>0){
            timeLeft--;
        }
        
    }
    public boolean timeEnds(){
        return timeLeft <= 0;
    }
    
    // end of a break increase session by one
    public void incrementWorkCount(){
        workSessionCounter++;
    }
    
    
    
   
    
    
    // getter and setter functions   

    public int getTimeLeft() {
        return timeLeft;
    }

    public String getSessionType() {
        return sessionType;
    }
    
    public int getWorkSessionCounter() {
        return workSessionCounter;
    }

    public void setWorkSessionCounter(int workSessionCounter) {
        this.workSessionCounter = workSessionCounter;
    }
    
    
    
}
