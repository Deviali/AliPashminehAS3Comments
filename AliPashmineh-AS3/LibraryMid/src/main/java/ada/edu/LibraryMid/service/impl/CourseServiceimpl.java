package ada.edu.LibraryMid.service.impl;

import ada.edu.LibraryMid.model.entity.CourseEntity;
import ada.edu.LibraryMid.model.entity.UserEntity;
import ada.edu.LibraryMid.repository.CoursesRepository;
import ada.edu.LibraryMid.repository.StudentRepository;
import ada.edu.LibraryMid.repository.UserRepository;
import ada.edu.LibraryMid.service.CourseService;
import ada.edu.LibraryMid.util.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Formatter;

@Service
public class CourseServiceimpl implements CourseService {

    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    private Connection conn;

    @Override
    public void SeeAllCourses() {
        conn = DBUtils.getConnection();
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM courses ";
        try {
            preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();

            String colname1 = meta.getColumnName(1);
            String colname2 = meta.getColumnName(2);
            String colname3 = meta.getColumnName(3);
            Formatter fmt1 = new Formatter();
            fmt1.format("%-31s%s", colname1, colname2);
            System.out.print(fmt1+"                              ");
            Formatter fmt2 = new Formatter();
            fmt2.format("%-31s%s", colname3, "");
            System.out.print(fmt2+"");

            while (rs.next()) {

                Formatter fmt3 = new Formatter();
                fmt3.format("%-21s", rs.getString(1));
                System.out.print(fmt3 + "         ");
                Formatter fmt4 = new Formatter();
                fmt4.format("%-21s", rs.getString(2));
                System.out.print(fmt4 + "         ");
                Formatter fmt5 = new Formatter();
                fmt5.format("%-21s", rs.getString(3));
                System.out.print(fmt5 + "         ");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int enrollTo(String coursename, String instructor, String email) {
        UserEntity user;
        CourseEntity course;
        user = userRepository.findFirstByEmail(email);
        if (user != null && user.getId() > 0) {
            course = coursesRepository.findFirstByCoursenameAndInstructor(coursename,instructor);
            if(course != null){
                conn= DBUtils.getConnection();
                PreparedStatement preparedStatement= null;
                String query = "INSERT INTO enrolled_students(emailofstudent,nameofcourse) VALUES (?,?)";
                try {

                    preparedStatement = conn.prepareStatement(query);

                    preparedStatement.setString(1,email);
                    preparedStatement.setString(2,coursename);

                    preparedStatement.executeUpdate();
                    //success
                    return 1;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }



            }//course or instructor doesnt exist
            return -2;

        }//not registered
        return -1;
    }

    @Override
    public int DropClass(String coursename, String instructor, String email) {
        UserEntity user;
        CourseEntity course;
        user = userRepository.findFirstByEmail(email);
        if (user != null && user.getId() > 0) {
            course = coursesRepository.findFirstByCoursenameAndInstructor(coursename,instructor);
            if(course != null){
                    conn= DBUtils.getConnection();
                    PreparedStatement preparedStatement= null;
                    String query = "DELETE FROM enrolled_students WHERE emailofstudent = ? && nameofcourse = ?";
                    try {

                        preparedStatement = conn.prepareStatement(query);

                        preparedStatement.setString(1,email);
                        preparedStatement.setString(2,coursename);

                        preparedStatement.executeUpdate();
                        //success
                        return 1;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }



            }//course or instructor doesnt exist
            return -2;

        }//not registered
         return -1;
    }
}
