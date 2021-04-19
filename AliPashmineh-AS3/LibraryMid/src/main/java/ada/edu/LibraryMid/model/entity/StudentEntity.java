package ada.edu.LibraryMid.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "enrolled_students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long  id  ;
    String  emailofstudent ;
    String  nameofcourse  ;
}
