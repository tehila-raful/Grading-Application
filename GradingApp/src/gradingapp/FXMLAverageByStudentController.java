/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradingapp;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tehil
 */
public class FXMLAverageByStudentController implements Initializable {

    int courseID = FXMLGradingController.getID();
    HashMap<Integer, Course> courses = FXMLGradingController.getCourses();
    DecimalFormat df = new DecimalFormat("#.##");

    @FXML
    private ComboBox<String> students;

    @FXML
    private Button btnSubmitStudent;

    @FXML
    private Label lblAverageResults;

    @FXML
    private Button btnBack;

    @FXML
    void SubmitStudentClicked(ActionEvent event) {
        Course course = (Course) courses.get(courseID);
        String name = students.getValue();
        Student stud = course.findStudent(name);
        double studentavg = course.getStudentAverage(stud);
        String formatstudentavg = df.format(studentavg);
        lblAverageResults.setText("The average for " + name + " is " + formatstudentavg);
    }

    @FXML
    void btnBackClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("FXMLMenu.fxml"));
        Scene nextScene = new Scene(root);
        Stage myStage = (Stage) btnBack.getScene().getWindow();
        myStage.setScene(nextScene);
        myStage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Course course = (Course) courses.get(courseID);
        ArrayList<Student> st = course.getStudents();

        for (Student s : st) {
            System.out.println(s.getName());
            students.getItems().add(s.getName());
        }
    }
}
