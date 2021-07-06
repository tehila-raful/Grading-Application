package gradingapp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;


public class ConsoleGradingApp {
	
	public static int menu() {
		String whatTheUserEntered = JOptionPane.showInputDialog("MENU\n"
				+ "\n1. Add Course \n2. Add Exam \n3. Get Class Average for an Exam \n4. Get Student's Average "
				+ "\n5. Get Course Average \n6. Curve Grades \n7.Display Student Grades \n8. Exit");
		Integer choice = Integer.parseInt(whatTheUserEntered);
		return choice;
	}
		
	public static ArrayList<Student> addStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		Integer numStudents = Integer.parseInt(JOptionPane.showInputDialog("How many students are in your class?"));
		for(int i = 0; i < numStudents; i++) {
			String name = JOptionPane.showInputDialog("Student " + (i+1) + "\nWhat is the first and last name?");
			Student aStudent = new Student(name.toUpperCase());
			students.add(aStudent);
		}
		return students;
	}
	
	public static void enterExam(Course aCourse) throws Exception {
		ArrayList<Exam> exams = new ArrayList<Exam>();
		ArrayList<Student> students = aCourse.getStudents();
		String date = JOptionPane.showInputDialog("Enter date of exam: (yyyy-mm-dd)");
		for(int i = 0; i < students.size(); i++) {
			double grade = Double.parseDouble(JOptionPane.showInputDialog("Enter grade for " + students.get(i)));
			Exam anExam = new Exam(students.get(i),aCourse.getTeacher(), aCourse.getSubject(), grade, date);
			exams.add(anExam);
		}
		aCourse.addExam(exams);
	}


}
