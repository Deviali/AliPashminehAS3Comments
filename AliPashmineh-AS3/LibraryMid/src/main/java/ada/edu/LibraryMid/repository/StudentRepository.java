package ada.edu.LibraryMid.repository;

import ada.edu.LibraryMid.model.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity,Long> {
}
