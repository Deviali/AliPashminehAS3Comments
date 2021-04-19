package ada.edu.LibraryMid.controller.Impl;

import ada.edu.LibraryMid.controller.CourseEnrollment;
import ada.edu.LibraryMid.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class")
public class CourseEnrollmentimpl implements CourseEnrollment {

    @Autowired
    private CourseService courseService;

    @Override
    @RequestMapping("/seeall")
    public ResponseEntity SeeAllCourses() {
        courseService.SeeAllCourses();
        return ResponseEntity.ok("Please check Console for metaData");
    }

    @Override
    @RequestMapping("/enroll")
    public ResponseEntity enrollTo(@RequestHeader("coursename") String coursename,
                                   @RequestHeader("instructor") String instructor,
                                   @RequestHeader("email") String email) {
        int result = courseService.enrollTo(coursename,instructor,email);
        if (result < -1){return ResponseEntity.ok("please check your spelling or the course does not exist");}
        if (result < 0){
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT); //Tea is good :D
        }
    }

    @Override
    @RequestMapping("/drop")
    public ResponseEntity DropClass(@RequestHeader("coursename") String coursename,
                                    @RequestHeader("instructor") String instructor,
                                    @RequestHeader("email") String email) {
        int result = courseService.DropClass(coursename,instructor,email);
        if (result < -1){return ResponseEntity.ok("please check your spelling or the course does not exist");}
        if (result < 0){
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT); //Tea is good :D
        }
    }
}
