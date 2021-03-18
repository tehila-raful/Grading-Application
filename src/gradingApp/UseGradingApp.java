package gradingApp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;


public class UseGradingApp {
	
	public static void main(String[] args) throws Exception {
		HashMap<Integer,Course> courses = new HashMap<Integer, Course>(); //key: courseID, value: course
		DecimalFormat df = new DecimalFormat("#.##");
		int choice = 0;
		do {
			choice = menu();
			switch (choice) {
			case 1:
				Course course = addCourse();
				JOptionPane.showMessageDialog(null, "Your course ID is: " + course.getCourseID());
				courses.put(course.getCourseID(), course);
				break;
			case 2:
				Integer id = Integer.parseInt(JOptionPane.showInputDialog("What is your course ID"));
				Course aCourse = courses.get(id);
				enterExam(aCourse);
				break;
			case 3:
				Integer thecourseid = Integer.parseInt(JOptionPane.showInputDialog("What is your course ID"));
				String date = JOptionPane.showInputDialog("What date is the exam?");
				Course theNewCourse = courses.get(thecourseid);
				double anAvg = theNewCourse.getClassAverageForTest(date);
				String formatanAvg = df.format(anAvg);
				JOptionPane.showMessageDialog(null, "The average for the exam is " + formatanAvg);
				break;
			case 4:
				Integer courseId = Integer.parseInt(JOptionPane.showInputDialog("What is your course ID"));
				Course studcourse = courses.get(courseId);
				String fname = JOptionPane.showInputDialog("What is the student's first name?");
				String lname = JOptionPane.showInputDialog("What is the student's last name?");
				Student stud = studcourse.findStudent(fname, lname);
				double studentavg = studcourse.getStudentAverage(stud);
				String formatstudentavg = df.format(studentavg);
				JOptionPane.showMessageDialog(null, "The average for " + fname + " " + lname + " is " + formatstudentavg);
				break;
			case 5:
				Integer courseid = Integer.parseInt(JOptionPane.showInputDialog("What is your course ID"));
				Course theCourse = courses.get(courseid);
				double avg = theCourse.getClassAverageForCourse();
				String formatavg = df.format(avg);
				JOptionPane.showMessageDialog(null, "The average for the course is " + formatavg);
				break;
			case 6:
				JOptionPane.showMessageDialog(null,"Thanks for using our grading system!");
				System.exit(0);
				break;
			}
		} while(choice >= 1 && choice <=6);	
	}
	
	
	

	public static int menu() {
		String whatTheUserEntered = JOptionPane.showInputDialog("MENU\n"
				+ "\n1. Add Course \n2. Add Exam \n3. Get Class Average for an Exam \n4. Get Student's Average \n5. Get Course Average \n6. Exit");
		Integer choice = Integer.parseInt(whatTheUserEntered);
		return choice;
	}
	
	public static Course addCourse() {
		String fname = JOptionPane.showInputDialog("What is your first name?");
		String lname = JOptionPane.showInputDialog("What is your last name?");
		Teacher teacher = new Teacher(fname, lname);
		String subject = JOptionPane.showInputDialog("What subject do you teach?");
		ArrayList<Student> students = addStudents();
		Course aCourse = new Course(teacher, subject, students);
		return aCourse;
	}
	
	public static ArrayList<Student> addStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		Integer numStudents = Integer.parseInt(JOptionPane.showInputDialog("How many students are in your class?"));
		for(int i = 0; i < numStudents; i++) {
			String fname = JOptionPane.showInputDialog("Student " + (i+1) + "\nWhat is the first name?");
			String lname = JOptionPane.showInputDialog("Student " + (i+1) + "\nWhat is the last name?");
			Student aStudent = new Student(fname,lname);
			students.add(aStudent);
		}
		return students;
	}
	
	public static void enterExam(Course aCourse) throws Exception {
		ArrayList<Exam> exams = new ArrayList<Exam>();
		ArrayList<Student> students = aCourse.getStudents();
		String date = JOptionPane.showInputDialog("Enter date of exam: ");
		for(int i = 0; i < students.size(); i++) {
			double grade = Double.parseDouble(JOptionPane.showInputDialog("Enter grade for " + students.get(i)));
			Exam anExam = new Exam(students.get(i),aCourse.getTeacher(), aCourse.getSubject(), grade, date);
			exams.add(anExam);
		}
		aCourse.addExam(exams);
	}


}
