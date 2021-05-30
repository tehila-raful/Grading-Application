/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradingapp;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private Button btnBack;

    @FXML
    void AddExamDate(ActionEvent event) throws Exception {

        if (examDateString.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Exam date must be provided");
            alert.showAndWait();
        } 
        else {
            try {
                LocalDate.parse(examDateString.getText());
                Course aCourse = courses.get(courseID);
                enterExam(aCourse);
            } catch (DateTimeParseException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Exam date invalid");
                examDateString.setText("");
                alert.showAndWait();
            }
        }
    }

    public void enterExam(Course aCourse) throws Exception {
        System.out.println(aCourse.toString());
        ArrayList<Exam> exams = new ArrayList<Exam>();
        ArrayList<Student> students = aCourse.getStudents();
        String date = examDateString.getText();

        double grade;
        for (int i = 0; i < students.size(); i++) {

            try {
                grade = Double.parseDouble(JOptionPane.showInputDialog("Enter grade for " + students.get(i)));
                Exam anExam = new Exam(students.get(i), aCourse.getTeacher(), aCourse.getSubject(), grade, date);
                exams.add(anExam);
            } catch (RuntimeException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No exam grade provided");
                alert.showAndWait();
            }
        }
        aCourse.addExam(exams);
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
