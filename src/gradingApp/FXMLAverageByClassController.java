/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradingapp;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author tehil
 */
public class FXMLAverageByClassController implements Initializable {

    int courseID = FXMLGradingController.getID();
    HashMap<Integer, Course> courses = FXMLGradingController.getCourses();
    DecimalFormat df = new DecimalFormat("#.##");
    
    @FXML
    private TextField examDateString;

    @FXML
    private Button btnDate;
    
    @FXML
    private Label lblAverage;

    @FXML
    void SubmitDate(ActionEvent event) {
         //get date
        String date = examDateString.getText(); 
        if(date.equals("")){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Exam date must be provided");
             alert.showAndWait(); 
        }
        else{
            //get course 
            Course course = (Course) courses.get(courseID);
            try{
                 double anAvg = course.getClassAverageForTest(date);
                 String formatanAvg = df.format(anAvg);
                 lblAverage.setText("The class average for the exam is " + formatanAvg);
            }
            catch(InvalidInputException e){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setContentText("Exam Date Not Found");
                 alert.showAndWait(); 
            }
           
        }

    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}