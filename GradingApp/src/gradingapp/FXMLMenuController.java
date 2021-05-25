/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradingapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import gradingapp.FXMLGradingController;
import static gradingapp.FXMLGradingController.courses;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author tehil
 */
public class FXMLMenuController implements Initializable {
    
     int courseID = FXMLGradingController.getID();
     HashMap<Integer, Course> courses = FXMLGradingController.getCourses();

    @FXML
    private ListView<String> listStudents;

    @FXML
    void AverageforClassClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("FXMLAverageByClass.fxml"));
        Scene nextScene = new Scene(root); 
        Stage myStage = new Stage();
        //myStage.setUserData(courses);
        myStage.setScene(nextScene);
        myStage.show();
    }

    
    @FXML
    void AverageforStudentClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("FXMLAverageByStudent.fxml"));
        Scene nextScene = new Scene(root); 
        Stage myStage = new Stage();
        //myStage.setUserData(courses);
        myStage.setScene(nextScene);
        myStage.show();
    }

    @FXML
    void ExitClicked(ActionEvent event) {
             System.exit(0);
    }

    @FXML
    void addExamClicked(ActionEvent event) throws Exception {
         //call up new window to add an exam   
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("FXMLAddExam.fxml"));
        Scene nextScene = new Scene(root); 
        Stage myStage = new Stage();
        //myStage.setUserData(courses);
        myStage.setScene(nextScene);
        myStage.show();
          
    //addExamVBox.setVisible(true);
       
       /* ArrayList<Student> students = aCourse.getStudents();
        //ArrayList<TextField> grades = new ArrayList<TextField>(students.size());
        Label lb = new Label();
        lb.setText("Grades");
        gradesBox.getChildren().add(lb);
        //populate arrayList with textfields 
        for(int i =0; i< students.size(); i ++){
            //grades.add(new TextField());
            gradesBox.getChildren().add(new TextField());
        } */      
    }
    
   /* @FXML
    void AddExamDate(ActionEvent event) throws Exception {
         Course aCourse = courses.get(courseID); 
        enterExam(aCourse);
    }*/

    @FXML
    void viewGradesClicked(ActionEvent event) throws IOException {
        //call up new window to display grades   
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("FXMLViewGrades.fxml"));
        Scene nextScene = new Scene(root); 
        Stage myStage = new Stage();
        //myStage.setUserData(courses);
        myStage.setScene(nextScene);
        myStage.show();
    }

    @FXML
    void viewStudentsClicked(ActionEvent event) {
      //Node node = (Node) event.getSource();
      //Stage stage = (Stage) node.getScene().getWindow();
      //HashMap courses = (HashMap) stage.getUserData();

       listStudents.getItems().clear();
       
      Course course = (Course) courses.get(courseID);
      ArrayList<Student> st = course.getStudents(); 
     
      for(Student s : st){
          System.out.println(s.getName());
          listStudents.getItems().add(s.getName());
      }
    }
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
    }    
    
}
