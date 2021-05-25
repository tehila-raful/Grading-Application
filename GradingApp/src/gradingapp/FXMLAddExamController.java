/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradingapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author tehil
 */
public class FXMLAddExamController implements Initializable {

    int courseID = FXMLGradingController.getID();
    HashMap<Integer, Course> courses = FXMLGradingController.getCourses();
    
    @FXML
    private TextField examDateString;

    @FXML
    private Button btnaddDate;

    @FXML
    void AddExamDate(ActionEvent event) throws Exception {

        if(examDateString.getText().equals("")){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Exam date must be provided");
             alert.showAndWait(); 
        }
        else{
             Course aCourse = courses.get(courseID); 
             enterExam(aCourse);
        }
       
    }
    
     public void enterExam(Course aCourse) throws Exception 
    {
        ArrayList<Exam> exams = new ArrayList<Exam>();
        ArrayList<Student> students = aCourse.getStudents();
        String date = examDateString.getText();
                //JOptionPane.showInputDialog("Enter date of exam: (yyyy-mm-dd)");
        
        //LocalDate date = examDate.getValue();
        double grade; 
        for(int i = 0; i < students.size(); i++) {
         
           try{
                grade = Double.parseDouble(JOptionPane.showInputDialog("Enter grade for " + students.get(i)));          
                Exam anExam = new Exam(students.get(i),aCourse.getTeacher(), aCourse.getSubject(), grade, date);
                exams.add(anExam);
           }
           catch(RuntimeException e){
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setContentText("No exam grade provided");
               alert.showAndWait(); 
           }
          
        }
        aCourse.addExam(exams);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
