package ada.edu.LibraryMid.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class CourseModel implements Serializable {
    private String coursename;
    private String instructor;
}
