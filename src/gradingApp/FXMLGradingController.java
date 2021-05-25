/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradingapp;

import static gradingapp.UseGradingApp.addStudents;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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


    protected static HashMap<Integer,Course> courses = new HashMap<Integer, Course>(); //key: courseID, value: course
    protected static int courseID; 

    @FXML
    void submitTeacherInfo(ActionEvent event) throws IOException {
        String name = txtTeacherName.getText();
        String subject = txtSubject.getText();
        
        //Create a new teacher with the given info
        Teacher teacher = new Teacher(name.toUpperCase());
        
        //read in students from file 
	ArrayList<Student> students = getStudentsFromFile();
        if(students == null){
            txtFileName.clear();
        }
        else
        {
            //Create a new Course with teacher and students
           Course course = new Course(teacher, subject, students);
           courseID = course.getCourseID();

           //Inform teacher of courseID
          //JOptionPane.showMessageDialog(null, "Your course ID is: " + course.getCourseID()
                                   //    + " \nStudents: \n" + getStudents(students));

           //add Course to collection 
           courses.put(course.getCourseID(), course);

           //Display all given info to Teacher in new window 

           FXMLLoader loader = new FXMLLoader();
           Parent root = loader.load(getClass().getResource("FXMLMenu.fxml"));
           Scene nextScene = new Scene(root); 
           Stage myStage = (Stage) btnSubmitTeacher.getScene().getWindow(); 
           //myStage.setUserData(courses);
           myStage.setScene(nextScene);
           myStage.show();
        }
        
       
    }
    
    public ArrayList<Student> getStudentsFromFile(){
         ArrayList<Student> students = new ArrayList<Student>();
         try{
            File file = new File(txtFileName.getText());
            Scanner reader = new Scanner(file);
           
            while(reader.hasNext()){
                String name = reader.nextLine();
		Student aStudent = new Student(name.toUpperCase());
		students.add(aStudent);
            }
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Sorry File Not Found");
        }
        return students; 
    }
    public String getStudents(ArrayList<Student> students){
        StringBuilder str = new StringBuilder();
        for(Student s: students){
            str.append(s.toString() + "\n");
        }
        return str.toString();
    }
    
    public static int getID(){
        return courseID; 
    }
    
    public static HashMap<Integer, Course> getCourses(){
        return courses; 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JOptionPane.showMessageDialog(null, "Welcome to our Grading App.\n Click OK to begin.");
    }    
    
}
