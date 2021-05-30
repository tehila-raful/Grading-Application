/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradingapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author tehil
 */
public class FXMLGradingController implements Initializable {

    @FXML
    private Label lblTeacherHeader;

    @FXML
    private Label lblName;

    @FXML
    private TextField txtTeacherName;

    @FXML
    private Label lblSubject;

    @FXML
    private TextField txtSubject;

    @FXML
    private Button btnSubmitTeacher;

    @FXML
    private Label lblFileName;

    @FXML
    private TextField txtFileName;

    @FXML
    private Button btnBack;

    @FXML
    private TextField txtCourseID;

    @FXML
    private Button btnLogin;

    protected static HashMap<Integer, Course> courses = new HashMap<Integer, Course>(); //key: courseID, value: course
    protected static int courseID;

    @FXML
    void loginButtonClicked(ActionEvent event) throws IOException {
        try {
            courseID = Integer.parseInt(txtCourseID.getText());

            //read in all saved data 
            String filename = "data.ser";

            try {
                ObjectInputStream fileInput = new ObjectInputStream(new FileInputStream(filename));

                HashMap<Integer, Course> oldCourses = (HashMap<Integer, Course>) fileInput.readObject();

                for (Entry<Integer, Course> c : oldCourses.entrySet()) {
                    courses.put(c.getKey(), c.getValue());
                }

                fileInput.close();
            } 
            catch (Exception e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error Reading in Data\n" + e.getMessage());
                alert.showAndWait();
            }

            if (!courses.containsKey(courseID)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid CourseID");
                txtCourseID.setText("");
                alert.showAndWait();
            } 
            else 
            {
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("FXMLMenu.fxml"));
                Scene nextScene = new Scene(root);
                Stage myStage = (Stage) btnSubmitTeacher.getScene().getWindow();
                myStage.setTitle("Grading Menu");
                myStage.setScene(nextScene);
                myStage.show();
            }
        } 
        catch (NumberFormatException e) 
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid CourseID");
            txtCourseID.setText("");
            alert.showAndWait();
        }

    }

    @FXML
    void submitTeacherInfo(ActionEvent event) throws IOException {

        String name = txtTeacherName.getText();
        String subject = txtSubject.getText();

        //Create a new teacher with the given info
        Teacher teacher = new Teacher(name.toUpperCase());

        //read in students from file 
        try {
            ArrayList<Student> students = getStudentsFromFile();
            if (students == null) {
                txtFileName.clear();
            } 
            else {
                //get latest course ID and assign it
                File file = new File("lastID.txt");
                int id = 1111;
                if (file.exists()) {
                    Scanner fr = new Scanner(file);
                    while (fr.hasNextInt()) {
                        id = fr.nextInt();
                        System.out.println(id);
                    }
                    fr.close();
                }
                id = id + 1;
                courseID = id;
                System.out.println("The courseID is now " + courseID);

                Course course = new Course(teacher, subject, students, courseID);

                //add Course to collection 
                courses.put(courseID, course);

                //display the course id 
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Your Course ID is: " + course.getCourseID() + "\nKeep for future login");
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("FXMLMenu.fxml"));
                Scene nextScene = new Scene(root);
                Stage myStage = (Stage) btnSubmitTeacher.getScene().getWindow();
                myStage.setTitle("Grading Menu");
                myStage.setScene(nextScene);
                myStage.show();
            }
        } 
        catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("File not found");
            alert.showAndWait();
        }
    }

    public ArrayList<Student> getStudentsFromFile() throws FileNotFoundException {
        ArrayList<Student> students = new ArrayList<Student>();

        File file = new File(txtFileName.getText());
        Scanner reader = new Scanner(file);

        while (reader.hasNext()) {
            String name = reader.nextLine();
            Student aStudent = new Student(name.toUpperCase());
            students.add(aStudent);
        }
        return students;
    }

    public String getStudents(ArrayList<Student> students) {
        StringBuilder str = new StringBuilder();
        for (Student s : students) {
            str.append(s.toString() + "\n");
        }
        return str.toString();
    }

    public static int getID() {
        return courseID;
    }

    public static HashMap<Integer, Course> getCourses() {
        return courses;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
