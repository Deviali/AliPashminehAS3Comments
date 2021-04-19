package ada.edu.LibraryMid.service;

public interface CourseService {
   void SeeAllCourses();
   int enrollTo(String coursename, String instructor, String email);
   int DropClass(String coursename,String instructor,String email);
}
