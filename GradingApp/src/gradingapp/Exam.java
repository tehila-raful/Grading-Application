package gradingapp;

import java.io.Serializable;
import java.time.*;

public class Exam implements Serializable {

    private LocalDate testDate;
    private Student student;
    private Teacher teacher;
    private String subject;
    private double grade;

    public Exam(Student student, Teacher teacher, String subject, double grade, String testDate) throws Exception {
        this(student, teacher, subject, grade, LocalDate.parse(testDate));
    }

    public Exam(Student student, Teacher teacher, String subject, double grade, LocalDate testDate) throws Exception {
        this.student = student;
        this.teacher = teacher;
        this.subject = subject;
        this.testDate = testDate;
        if (grade >= 0) {
            this.grade = grade;
        } else {
            throw new InvalidInputException("Invalid grade");
        }
    }
    
    public LocalDate getTestDate() {
        return testDate;
    }

    public Student getStudent() {
        return student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getSubject() {
        return subject;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade >= 0) {
            this.grade = grade;
        } else {
            throw new InvalidInputException("Invalid grade");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Exam other = (Exam) obj;
        if (student == null) {
            if (other.student != null) {
                return false;
            }
        } else if (!student.equals(other.student)) {
            return false;
        }
        if (subject == null) {
            if (other.subject != null) {
                return false;
            }
        } else if (!subject.equals(other.subject)) {
            return false;
        }
        if (testDate == null) {
            if (other.testDate != null) {
                return false;
            }
        } else if (!testDate.equals(other.testDate)) {
            return false;
        }
        return true;
    }
}
