package ada.edu.LibraryMid.controller;

import org.springframework.http.ResponseEntity;

public interface CourseEnrollment {
    ResponseEntity SeeAllCourses();
    ResponseEntity enrollTo(String coursename,String instructor,String email);
    ResponseEntity DropClass(String coursename,String instructor,String email);
}
