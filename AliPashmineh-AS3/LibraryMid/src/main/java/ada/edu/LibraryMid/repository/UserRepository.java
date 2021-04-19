package ada.edu.LibraryMid.repository;

import ada.edu.LibraryMid.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long > {

    UserEntity findFirstByEmail(String email);

    UserEntity findFirstByEmailAndPassword(String email,String password);

}
