package swingGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Main {

	private static Map<String, Map<String, String>> studentGrades = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createGUI);
    }

    private static void createGUI() {
        JFrame frame = new JFrame("Student Grade Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        
        JLabel studentLabel = new JLabel("Student ID:");
        JTextField studentField = new JTextField();
        
        JLabel subjectLabel = new JLabel("Subject:");
        JTextField subjectField = new JTextField();
        
        JLabel gradeLabel = new JLabel("Grade:");
        JTextField gradeField = new JTextField();
        
        JButton addButton = new JButton("Add Grade");
        JButton viewButton = new JButton("View Grades");
        JTextArea outputArea = new JTextArea(5, 20);
        outputArea.setEditable(false);
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = studentField.getText().trim();
                String subject = subjectField.getText().trim();
                String grade = gradeField.getText().trim();
                
                if (!studentID.isEmpty() && !subject.isEmpty() && !grade.isEmpty()) {
                    studentGrades.putIfAbsent(studentID, new HashMap<>());
                    studentGrades.get(studentID).put(subject, grade);
                    outputArea.setText("Grade added successfully!");
                } else {
                    outputArea.setText("Please enter all fields!");
                }
            }
        });
        
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = studentField.getText().trim();
                if (studentGrades.containsKey(studentID)) {
                    StringBuilder grades = new StringBuilder("Grades for Student " + studentID + "\n");
                    for (Map.Entry<String, String> entry : studentGrades.get(studentID).entrySet()) {
                        grades.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                    }
                    outputArea.setText(grades.toString());
                } else {
                    outputArea.setText("No records found for Student ID " + studentID);
                }
            }
        });
        
        panel.add(studentLabel);
        panel.add(studentField);
        panel.add(subjectLabel);
        panel.add(subjectField);
        panel.add(gradeLabel);
        panel.add(gradeField);
        panel.add(addButton);
        panel.add(viewButton);
        
        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
}

