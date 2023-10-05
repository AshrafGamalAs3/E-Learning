package com.example.e_learning;

public class Material {
    String Uri,courseId,pdfName;

    public Material() {
    }

    public Material(String uri, String courseId) {
        Uri = uri;
        this.courseId = courseId;
    }

    public Material(String uri, String courseId, String pdfName) {
        Uri = uri;
        this.courseId = courseId;
        this.pdfName = pdfName;
    }

    public Material(String uri) {
        Uri = uri;
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
