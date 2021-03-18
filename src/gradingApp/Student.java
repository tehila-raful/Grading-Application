package gradingApp;

import java.time.LocalDate;

public class Student implements Comparable<Student> {
	
	private Integer studentID;
	private static Integer id = 3333;
	private String fName;
	private String lName;
	//private LocalDate dob;
	
	/*public Student(String fName, String lName, String dob) {
		this(fName, lName, LocalDate.parse(dob));
	}*/
	
	public Student(String fName, String lName) {
		this.fName = fName;
		this.lName = lName;
		studentID = id++;
	}
	
	public Integer getStudentID() {
		return studentID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (studentID == null) {
			if (other.studentID != null)
				return false;
		} else if (!studentID.equals(other.studentID))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return getfName() + " " + getlName() ;
	}
	
	@Override
	public int compareTo(Student anotherStudent) {
		return getStudentID().compareTo(anotherStudent.getStudentID());
	}

	
	

}
