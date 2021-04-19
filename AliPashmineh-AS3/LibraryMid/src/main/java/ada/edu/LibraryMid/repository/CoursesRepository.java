package ada.edu.LibraryMid.repository;

import ada.edu.LibraryMid.model.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

public interface CoursesRepository extends CrudRepository<CourseEntity,Long> {
    CourseEntity findFirstByCoursename(String coursename);
    CourseEntity findFirstByInstructor(String instructor);
    CourseEntity findFirstByCoursenameAndInstructor(String coursename,String instructor);
}
