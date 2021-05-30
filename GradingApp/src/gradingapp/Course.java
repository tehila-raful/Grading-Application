package gradingapp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Course implements Serializable {
    private static final long serialVersionUID = 5L;
    private Integer courseID;
    private Teacher teacher;
    private String subject;
    private ArrayList<Student> students;
    private HashMap<Integer, ArrayList<Exam>> examsByStudent; // studentid, arraylist of an individual student's exams
    private HashMap<LocalDate, ArrayList<Exam>> examsByTestDate; // exam date, arraylist of all the exams on that day

    public Course(Teacher teacher, String subject, ArrayList<Student> students, Integer courseID) 
    {
        this.courseID = courseID++;
        this.teacher = teacher;
        this.subject = subject;
        this.students = students;
        examsByStudent = new HashMap<Integer, ArrayList<Exam>>();
        examsByTestDate = new HashMap<LocalDate, ArrayList<Exam>>();

        // loop through Students
        for (Student s : students) {
                Integer id = s.getStudentID();
                examsByStudent.put(id, new ArrayList<Exam>());
        }
    }
    
    public Integer getCourseID() {
        return courseID;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getSubject() {
        return subject;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
    
    public String toString(Course aCourse){
        return courseID + " " + teacher; 
    }
    
    public ArrayList<String> getGradesByExamDate(String date){
        ArrayList<String> grades = new ArrayList<String>();

        ArrayList<Exam> exams = examsByTestDate.get(LocalDate.parse(date));
            for(int i = 0; i < exams.size(); i++) {
                grades.add(exams.get(i).getStudent() + " " + exams.get(i).getGrade() + "\n");
            }
        return grades; 
    }

    public String displayGrades(String date) {
        StringBuilder sb = new StringBuilder();
        if(!examsByTestDate.containsKey(LocalDate.parse(date))) 
        {
            throw new InvalidInputException("Invalid test date");
        }
        ArrayList<Exam> exams = examsByTestDate.get(LocalDate.parse(date));
        sb.append("Exam Date " + date + "\n");
        for(int i = 0; i < exams.size(); i++) {
                sb.append(exams.get(i).getStudent() + " " + exams.get(i).getGrade() + "\n");
        }
        return sb.toString();
    }

    public void curveExamGrades(String date, int numPoints) {
        if(!examsByTestDate.containsKey(LocalDate.parse(date))) {
            throw new InvalidInputException("Invalid test date");
        }
        ArrayList<Exam> exams = examsByTestDate.get(LocalDate.parse(date));

        for(int i = 0; i < exams.size(); i++) {
            exams.get(i).setGrade(exams.get(i).getGrade() + numPoints);
        }
    }

    public Student findStudent(String name) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getName().equals(name)) {
                    return students.get(i);
            }
        }
        throw new InvalidInputException("student not found");
    }

    public void addExam(ArrayList<Exam> exams) {
        // add the whole classes exam by date
        examsByTestDate.put(exams.get(0).getTestDate(), exams);

        // add another exam by each student
        for (Exam e : exams) {
            Integer id = e.getStudent().getStudentID();
            ArrayList<Exam> studentExams = examsByStudent.get(id);
            studentExams.add(e);
            examsByStudent.put(id, studentExams);
        }
    }

    public double getStudentAverage(Student student) {
        ArrayList<Exam> studsExam = examsByStudent.get(student.getStudentID());
        double total = 0;
        for(Exam e : studsExam) {
                total += e.getGrade();
        }
        return total/studsExam.size();
    }

    public double getClassAverageForTest(String date) {
        return getClassAverageForTest(LocalDate.parse(date));
    }

    public double getClassAverageForTest(LocalDate date) {
        if(!examsByTestDate.containsKey(date)) {
            throw new InvalidInputException("Invalid test date");
        }
        ArrayList<Exam> exams = examsByTestDate.get(date);
        double total = 0;
        for(int i = 0; i < exams.size(); i++) {
            total += exams.get(i).getGrade();
        }
        double avg = total / exams.size();
        return avg;		
    }

    public double getClassAverageForCourse() {
        Set<LocalDate> allExamDates = examsByTestDate.keySet();
        Iterator <LocalDate> iter = allExamDates.iterator();
        double total = 0;
        int count = 0;
        while(iter.hasNext()) {
            LocalDate date = iter.next();
            double testAvg = getClassAverageForTest(date);
            total += testAvg;
            count++;
        }
        return total/count;
    }
}
