package gradingapp;

import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable {

    private Integer studentID;
    private static Integer id = 3333;
    private String name;

    public Student(String name) {
        this.name = name;
        studentID = id++;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String fName) {
        this.name = fName;
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
        Student other = (Student) obj;
        if (studentID == null) {
            if (other.studentID != null) {
                return false;
            }
        } else if (!studentID.equals(other.studentID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Student anotherStudent) {
        return getStudentID().compareTo(anotherStudent.getStudentID());
    }

}
