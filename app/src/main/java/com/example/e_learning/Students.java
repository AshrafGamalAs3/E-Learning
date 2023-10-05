package com.example.e_learning;

public class Students {
   String studentId,courseId,studentName,courseName;
   int attandGrade,gradeQuze,projectGrade;




    public Students() {
    }

    public Students(String studentId, String courseId, String studentName, String courseName, int attandGrade, int gradeQuze, int projectGrade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.attandGrade = attandGrade;
        this.gradeQuze = gradeQuze;
        this.projectGrade = projectGrade;
    }
    public Students(String studentId, String courseId, String studentName, int gradeQuze) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.gradeQuze = gradeQuze;
    }
    public int getAttandGrade() {
        return attandGrade;
    }
      public int getGradeQuze() {
        return gradeQuze;
    }
    public int getProjectGrade() {
        return projectGrade;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseName() {
        return courseName;
    }
}
