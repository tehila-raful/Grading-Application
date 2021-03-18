package gradingApp;

import java.time.LocalDate;

public class Teacher implements Comparable<Teacher>{
	
	private Integer teacherID;
	private static Integer id = 2222;
	private String fName;
	private String lName;
	//private LocalDate dob;
	
	/*public Teacher(String fName, String lName, String dob) {
		this(fName, lName, LocalDate.parse(dob));
	}*/
	
	public Teacher(String fName, String lName) {
		this.fName = fName;
		this.lName = lName;
		teacherID = id++;
	}

	@Override
	public String toString() {
		return "Teacher [teacherID=" + teacherID + ", fName=" + fName + ", lName=" + lName + "]";
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

	public Integer getTeacherID() {
		return teacherID;
	}
	
	@Override
	public int compareTo(Teacher anotherTeacher) {
		return getTeacherID().compareTo(anotherTeacher.getTeacherID());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		if (teacherID == null) {
			if (other.teacherID != null)
				return false;
		} else if (!teacherID.equals(other.teacherID))
			return false;
		return true;
	}

}
