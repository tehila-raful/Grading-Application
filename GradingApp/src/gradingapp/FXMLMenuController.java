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
import javafx.scene.control.ListView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;


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
    private Button btnLogOut;

    @FXML
    private MenuBar menuBar;

    private void saveData() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Saving Data...\nThank you for using our Grading App");
        alert.showAndWait();

        //save last courseID
        Integer lastID = courseID;
        for (Integer s : courses.keySet()) {
            if (s > lastID) {
                lastID = s;
            }
        }
        System.out.println("LastID " + lastID);
        String idFile = "lastID.txt";
        File file = new File(idFile);
        try {
            FileWriter fw = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(fw);
            //fw.write(String.valueOf(lastID));
            pw.print(lastID++);
            pw.close();
            fw.close();
        }
        catch (IOException ex) {
            Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //save courses
        String filename = "data.ser";
        try {
            ObjectOutputStream fileOutput = new ObjectOutputStream(new FileOutputStream(filename));
            fileOutput.writeObject(courses);
            fileOutput.close();
        } 
        catch (IOException e) {
            System.out.println("couldn't find file, can't store data, contact IT " + e.getMessage());

        }
    }

    @FXML
    void btnLogOutClicked(ActionEvent event) throws IOException {
     
        saveData();

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("FXMLGrading.fxml"));
        Scene nextScene = new Scene(root);
        Stage myStage = (Stage) btnLogOut.getScene().getWindow();
        myStage.setTitle("Login Window");
        myStage.setScene(nextScene);
        myStage.show();
    }

    @FXML
    void AverageforClassClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("FXMLAverageByClass.fxml"));
        Scene nextScene = new Scene(root);
        Stage myStage = (Stage) menuBar.getScene().getWindow();
        myStage.setTitle("Class Average");
        myStage.setScene(nextScene);
        myStage.show();
    }

    @FXML
    void AverageforStudentClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("FXMLAverageByStudent.fxml"));
        Scene nextScene = new Scene(root);
        Stage myStage = (Stage) menuBar.getScene().getWindow();
        myStage.setTitle("Student Average");
        myStage.setScene(nextScene);
        myStage.show();
    }

    @FXML
    void ExitClicked(ActionEvent event) {
        saveData();
        System.exit(0);
    }

    @FXML
    void addExamClicked(ActionEvent event) throws Exception {
        //call up new window to add an exam   
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("FXMLAddExam.fxml"));
        Scene nextScene = new Scene(root);
        Stage myStage = (Stage) menuBar.getScene().getWindow();
        myStage.setScene(nextScene);
        myStage.setTitle("Add Exam");
        myStage.show();
    }

    @FXML
    void viewGradesClicked(ActionEvent event) throws IOException {
        //call up new window to display grades   
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("FXMLViewGrades.fxml"));
        Scene nextScene = new Scene(root);
        Stage myStage = (Stage) menuBar.getScene().getWindow();
        myStage.setScene(nextScene);
        myStage.setTitle("Student Grades");
        myStage.show();
    }

    @FXML
    void viewStudentsClicked(ActionEvent event) {
        listStudents.getItems().clear();

        Course course = (Course) courses.get(courseID);
        ArrayList<Student> st = course.getStudents();

        for (Student s : st) {
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
