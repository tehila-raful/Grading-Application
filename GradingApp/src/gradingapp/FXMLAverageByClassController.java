/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradingapp;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private Button btnBack;

    @FXML
    void SubmitDate(ActionEvent event) {
        //get date
        String date = examDateString.getText();
        if (date.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Exam date must be provided");
            alert.showAndWait();
        } 
        else {
            //get course 
            Course course = (Course) courses.get(courseID);
            try {
                double anAvg = course.getClassAverageForTest(date);
                String formatanAvg = df.format(anAvg);
                lblAverage.setText("The class average for the exam is " + formatanAvg);
            } 
            catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Exam Date Not Found");
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
