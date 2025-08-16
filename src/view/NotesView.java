/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.UIManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junayed
 */

import model.CourseNoteModel;
import javax.swing.table.DefaultTableModel;



class CoursePanel extends JPanel
{
    private String courseCode;
    private int courseNum;
    private boolean isHovered = false;
    
    //COLOR PALETTE
    private static final Color CARD_BASE = new Color(0xffb2ba);
    private static final Color CARD_HOVER = new Color(0xf35c76);
    private static final Color ACCENT_COLOR = new Color(0xb02947);
    private static final Color TEXT_PRIMARY = new Color(0x67001f);
    private static final Color TEXT_SECONDARY = new Color(0x581b26);

    public CoursePanel(String courseCode, int courseNum)
    {
        this.courseCode = courseCode;
        this.courseNum = courseNum;

        setPreferredSize(new Dimension(200, 200));
        setBackground(CARD_BASE);
        setLayout(new BorderLayout());
        
        // Course code and number
        String courseName = courseCode + courseNum;
        JLabel courseLabel = new JLabel(courseName);
        courseLabel.setForeground(TEXT_PRIMARY);
        courseLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        courseLabel.setHorizontalAlignment(SwingConstants.LEADING);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Course Notes");
        subtitleLabel.setForeground(TEXT_SECONDARY);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Layout
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(190, 0, 5, 130);
        centerPanel.add(courseLabel, gbc);
        
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 150);
        centerPanel.add(subtitleLabel, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Hover effects
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                setForeground(CARD_HOVER);
                setBackground(CARD_HOVER);                
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(ACCENT_COLOR, 2),
                    BorderFactory.createEmptyBorder(19, 19, 19, 19)
                ));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                setBackground(CARD_BASE);
                setBorder(null);
                repaint();
            }
        });
    }

    public String getCourseCode() { return courseCode; }
    public int getCourseNum() { return courseNum; }
}

class CourseFrame extends JPanel
{
    private JPanel courseListPanel;
    private ArrayList<CoursePanel> courseCards = new ArrayList<>();
    private JPanel headerPanel;
    private NotesPanel notesPanel; // Reference to switch to notes view

    
    // Modern color scheme
    private static final Color BACKGROUND = new Color(0x1c1011);
    private static final Color HEADER_BG = new Color(0x170b0c);
    private static final Color TEXT_PRIMARY = new Color(0xffb2ba);
    private static final Color ACCENT_COLOR = new Color(0xb02947);
    private static final Color BUTTON_COLOR = new Color(0xf35c76);

    public CourseFrame()
    {
        setBackground(BACKGROUND);
        setLayout(new BorderLayout());

        // Modern header
        createHeader();

        // Main content area
        createMainContent();
    }

    private void createHeader()
    {
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(HEADER_BG);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(226, 232, 240)),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        // Title
        JLabel titleLabel = new JLabel("My Courses");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(TEXT_PRIMARY);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Manage your course notes and materials");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(TEXT_PRIMARY);

        // Add Course button
        JButton addCourseBtn = createButton("Add", BUTTON_COLOR, TEXT_PRIMARY); //createButton has been created below.It's a method that returns a button object
        addCourseBtn.setPreferredSize(new Dimension(140, 45));

        // Header layout
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        titlePanel.add(titleLabel, gbc);
        
        gbc.gridy = 1;
        titlePanel.add(subtitleLabel, gbc);

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(addCourseBtn, BorderLayout.EAST);
        
        add(headerPanel, BorderLayout.NORTH);
    }

    private void createMainContent()
    {
        // Grid panel for course cards with modern spacing
        courseListPanel = new JPanel(new GridLayout(0, 4, 25, 25));
        courseListPanel.setBackground(BACKGROUND);
        courseListPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Scrollable grid with modern scrollbars
        JScrollPane scrollPane = new JScrollPane(courseListPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(15, 7));

        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton createButton(String text, Color bgColor, Color textColor)
    {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        button.setFocusPainted(false);
        
        // Hover effect
        button.addMouseListener(new MouseAdapter()
        {
            Color originalColor = bgColor;
            
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
        
        return button;
    }

    public void addCourseCard(CoursePanel card)
    {
        courseCards.add(card);
        courseListPanel.add(card);
        
        // Add click listener to open notes - now switches panel instead of opening new window
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String courseName = card.getCourseCode() + " " + card.getCourseNum();
                NotesPanel notesFrame = new NotesPanel(courseName);
                notesFrame.setVisible(true);
                
                // Add some sample notes for demonstration
                notesFrame.addNoteItem("Introduction to " + courseName, "Basic concepts and overview", "Today");
            }
        });
        
        courseListPanel.revalidate();
        courseListPanel.repaint();
    }

    public ArrayList<CoursePanel> getCourseCards() {
        return courseCards;
    }
}

// Changed from JFrame to JPanel --
class NotesPanel extends JFrame {
    private JPanel notesPanel;
    private List<NotesPanel> notes;
    private static final Color BACKGROUND = new Color(0x1c1011);
    private static final Color CARD_BG = new Color(0x8C1007);
    private static final Color TEXT_PRIMARY =  Color.WHITE;
    private static final Color TEXT_SECONDARY = new Color(0x000000);
    private static final Color ACCENT_COLOR = new Color(0xb02947);
    private String courseName;
    private int selectedNoteIndex = -1;
    private JButton addNoteBtn;
    private JButton deleteNoteBtn;
    private JButton saveNoteBtn;


    public NotesPanel(String courseName) {
        setTitle("Notes - " + courseName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setBackground(BACKGROUND);
        setLayout(new BorderLayout());
        
        

        // Modern header
        createNotesHeader(courseName);
        
        // Notes content area
        createNotesContent();
        
        //Notes footer Area
        createNotesFooter();
    }

    private void createNotesHeader(String courseName) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0x170b0c)),
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));

        JLabel titleLabel = new JLabel("Notes for " + courseName);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(TEXT_PRIMARY);



        headerPanel.add(titleLabel, BorderLayout.WEST);

        
        add(headerPanel, BorderLayout.NORTH);
    }
    private void createNotesFooter() {
            JPanel footerPanel = new JPanel(new BorderLayout());
            footerPanel.setBackground(BACKGROUND);
            footerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0x170b0c)),
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));

            addNoteBtn = createButton("ADD NOTE", ACCENT_COLOR, TEXT_PRIMARY);
            addNoteBtn.setPreferredSize(new Dimension(120, 40));
            addNoteBtn.addActionListener(e -> showAddNoteDialog());



            

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            buttonPanel.setOpaque(false);
            buttonPanel.add(addNoteBtn);


        footerPanel.add(buttonPanel,BorderLayout.EAST);
        
        add(footerPanel, BorderLayout.SOUTH);
    }
    

    private void createNotesContent() {
        notesPanel = new JPanel();
        notesPanel.setLayout(new BoxLayout(notesPanel, BoxLayout.Y_AXIS));
        notesPanel.setBackground(BACKGROUND);
        notesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(notesPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(12, 0));

        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton createButton(String text, Color bgColor, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new MouseAdapter() {
            Color originalColor = bgColor;
            
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
        
        return button;
    }
    
    
    // dialog for inputing new note
    private void showAddNoteDialog() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        JTextField titleField = new JTextField();
        titleField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JTextArea contentArea = new JTextArea(8, 30);
        contentArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane contentScroll = new JScrollPane(contentArea);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Title:"), BorderLayout.WEST);
        topPanel.add(titleField, BorderLayout.CENTER);
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new JLabel("Content:"), BorderLayout.NORTH);
        centerPanel.add(contentScroll, BorderLayout.CENTER);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Note", 
                                                  JOptionPane.OK_CANCEL_OPTION, 
                                                  JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText().trim();
            String content = contentArea.getText().trim();
            
            if (!title.isEmpty() && !content.isEmpty()) {
                addNoteItem(title, content, "Just now");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in both title and content!", 
                                            "Missing Information", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    


    
    
    
    public void addNoteItem(String title, String content, String date ) {
        JPanel noteCard = new JPanel(new BorderLayout());
        noteCard.setBackground(CARD_BG);
        noteCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        noteCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150)); // Hardcoded the height

        // Title and date panel and btn panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(TEXT_PRIMARY);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel dateLabel = new JLabel(date);
        dateLabel.setForeground(TEXT_SECONDARY);
        dateLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(dateLabel, BorderLayout.EAST);

        // Preview text
        JLabel previewLabel = new JLabel("<html><p style='margin-top: 8px;'>" + content + "</p></html>");
        previewLabel.setForeground(Color.WHITE);
        previewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        noteCard.add(headerPanel, BorderLayout.NORTH);
        noteCard.add(previewLabel, BorderLayout.CENTER);
        noteCard.add(footerPanel, BorderLayout.SOUTH);
        
        //Delete button initializing
        deleteNoteBtn = createButton("DELETE NOTE", ACCENT_COLOR, TEXT_PRIMARY);
        deleteNoteBtn.setPreferredSize(new Dimension(120, 40));
        // delete button function logic
        deleteNoteBtn.addActionListener(e -> {
            notesPanel.remove(noteCard);   
            notesPanel.revalidate();   
            notesPanel.repaint();    
        });
        
        // Save button initializing
        saveNoteBtn = createButton("SAVE NOTE", ACCENT_COLOR, TEXT_PRIMARY);
        saveNoteBtn.setPreferredSize(new Dimension(120, 40));
        
        // save button function logic
        saveNoteBtn.addActionListener(e -> {
            FileWriter fw = null;
                try {
                    fw = new FileWriter("notes.txt", true);
                    fw.write(title + "\n" + content + "\n\n");
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(NotesPanel.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        fw.close();
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                }
            });
        
        
        // delete and save btn
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.add(saveNoteBtn);
        footerPanel.add(deleteNoteBtn);
        

        // Hover effect
        noteCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                noteCard.setBackground(CARD_BG.darker());
                noteCard.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                noteCard.setBackground(CARD_BG);
                noteCard.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        notesPanel.add(noteCard);
        notesPanel.add(Box.createVerticalStrut(15));

        notesPanel.revalidate();
        notesPanel.repaint();
    }


    public String getCourseName() {
        return courseName;
    }
}

   

    


// This class now returns a JPanel instead of creating a JFrame
public class NotesView extends JPanel {
    private CourseFrame coursePanel;

    public NotesView() {
        setLayout(new BorderLayout());
        
        coursePanel = new CourseFrame();

        // Add sample courses
        coursePanel.addCourseCard(new CoursePanel("CSE", 215));
        coursePanel.addCourseCard(new CoursePanel("PHY", 107));
        coursePanel.addCourseCard(new CoursePanel("MAT", 125));
        coursePanel.addCourseCard(new CoursePanel("ENG", 103));
        coursePanel.addCourseCard(new CoursePanel("CHE", 201));
        
        coursePanel.setVisible(true);

        add(coursePanel, BorderLayout.CENTER);
    }
    
    // Method to get the main panel for adding to tabbed pane
    public JPanel getMainPanel() {
        return this;
    }
}
