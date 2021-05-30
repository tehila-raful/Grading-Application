/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradingapp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private Button btnBack;

    @FXML
    void SubmitDateClicked(ActionEvent event) {

        //get date
        String date = txtDate.getText();
        if (date == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Exam date must be provided");
            alert.showAndWait();
        } 
        else {
            //get course 
            Course course = (Course) courses.get(courseID);
            try {
                //get ArrayList of grades 
                ArrayList<String> grades = course.getGradesByExamDate(date);

                //populate ListView 
                for (String s : grades) {
                    System.out.println(s);
                    listGrades.getItems().add(s);
                }
            }
            catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Exam date not found");
                alert.showAndWait();
            }
        }
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
