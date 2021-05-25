package gradingapp;

import java.time.LocalDate;

public class Teacher implements Comparable<Teacher>{
	
	private Integer teacherID;
	private static Integer id = 2222;
	private String name;
	//private String lName;
	//private LocalDate dob;
	
	/*public Teacher(String fName, String lName, String dob) {
		this(fName, lName, LocalDate.parse(dob));
	}*/
	
	public Teacher(String name) {
		this.name = name;
		teacherID = id++;
	}

	@Override
	public String toString() {
		return "Teacher [teacherID=" + teacherID + ", Name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
