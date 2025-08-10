/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import javax.swing.*;
import java.awt.*;
import model.PomodoroModel;
import view.PomodoroTimerPanel;
/**
 *
 * @author Junayed
 */
public class PomodoroController {
    private PomodoroModel model;
    private PomodoroTimerPanel view;
    private Timer swingTimer;
    boolean flag = true;

    public PomodoroController(PomodoroModel model, PomodoroTimerPanel view) {
        this.model = model;
        this.view = view;
        
        // Disabling pause button at start
        view.getPauseButton().setEnabled(false);
        
        // for every 1 sec call this function to update timer
        swingTimer = new Timer(1000, e -> updateTimer());
        
        // adding event listener to button 
        view.getStartButton().addActionListener(e -> startTimer());
        view.getPauseButton().addActionListener(e -> pauseTimer());
        view.getResetButton().addActionListener(e -> resetTimer());
        
        
        
    }
   
    
    // Fucntions for button events
    private void startTimer(){
        swingTimer.start();
        view.getStartButton().setEnabled(false); // disable the start btn at start
        view.getPauseButton().setEnabled(true); // enable pause button after start
    }
    
    private void pauseTimer(){
        
        if(!flag){
            swingTimer.start();
            view.getPauseButton().setText("⏸ PAUSE");
            flag = true;

        }
        else{
            swingTimer.stop();
            view.getPauseButton().setText("▶ RESUME");
            flag = false;
        }
    }
    
    private void resetTimer(){
        swingTimer.stop();
        model.resetWorkSession();
        model.setWorkSessionCounter(1);
        model.updateTime(model.getTimeLeft());
        model.sessionUpdate(model.getSessionType(), model.getWorkSessionCounter());
        view.getStartButton().setEnabled(true);
        view.getPauseButton().setEnabled(false);
        view.getPauseButton().setText("⏸ PAUSE");
        
    }
    
    private void updateTimer(){
        model.countdown();
        model.updateTime(model.getTimeLeft());
        if(model.timeEnds()){
            switchSession();
        }
        
    }
    
    private void switchSession(){
        swingTimer.stop();
        
        if(model.getSessionType().equals("O.O WORK")){
            
            
            if(model.getWorkSessionCounter() % 4 == 0){
                model.startLongBreak();
            }
            else{
                model.startShortBreak();
            }
        }
        else if(model.getSessionType().equals("OwO SHORT BREAK") || model.getSessionType().equals("UwU LONG BREAK")){
            model.incrementWorkCount();
            model.resetWorkSession();
        }
        
        model.sessionUpdate(model.getSessionType(), model.getWorkSessionCounter());
        model.updateTime(model.getTimeLeft());
        swingTimer.start();
    }
    
    
    
}
