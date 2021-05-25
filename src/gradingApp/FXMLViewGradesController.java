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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author tehil
 */
public class FXMLViewGradesController implements Initializable {

    int courseID = FXMLGradingController.getID();
    HashMap<Integer, Course> courses = FXMLGradingController.getCourses();
    
    @FXML
    private TextField txtDate;

    @FXML
    private Button btnDate;

    @FXML
    private ListView<String> listGrades;

    @FXML
    void SubmitDateClicked(ActionEvent event) {

        //get date
        String date = txtDate.getText(); 
        if(date == null){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Exam date must be provided");
             alert.showAndWait(); 
        }
        else{
            //get course 
            Course course = (Course) courses.get(courseID);

            //get ArrayList of grades 
            ArrayList<String> grades = course.getGradesByExamDate(date);

            //populate ListView 
            for(String s : grades){
              System.out.println(s);
              listGrades.getItems().add(s);
            }
        }
        
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
