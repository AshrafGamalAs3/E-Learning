package com.example.e_learning;

public class Courses {

  String courseName,courseId,instructorId;
    int attandGrade,gradeQuze,projectGrade;

    public Courses(String courseName, String courseId, String instructorId, int attandGrade, int gradeQuze, int projectGrade) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.instructorId = instructorId;
        this.attandGrade = attandGrade;
        this.gradeQuze = gradeQuze;
        this.projectGrade = projectGrade;
    }

    public Courses() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getAttandGrade() {
        return attandGrade;
    }

    public void setAttandGrade(int attandGrade) {
        this.attandGrade = attandGrade;
    }

    public int getGradeQuze() {
        return gradeQuze;
    }

    public void setGradeQuze(int gradeQuze) {
        this.gradeQuze = gradeQuze;
    }

    public int getProjectGrade() {
        return projectGrade;
    }

    public void setProjectGrade(int projectGrade) {
        this.projectGrade = projectGrade;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }
}
